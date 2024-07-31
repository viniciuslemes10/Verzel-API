package com.br.veiculos.verzel.services;

import com.amazonaws.services.s3.AmazonS3;
import com.br.veiculos.verzel.model.Veiculos;
import com.br.veiculos.verzel.records.VeiculosDTO;
import com.br.veiculos.verzel.repository.VeiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class VeiculosService {
    @Autowired
    private VeiculosRepository repository;

    @Autowired
    private AmazonS3 amazonS3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public Veiculos create(VeiculosDTO data) {
        String generatedImage = null;
        if(data.foto() != null) {
            generatedImage = this.uploadImage(data.foto());
        }
        var veiculo = new Veiculos(data);
        veiculo.setFoto(generatedImage);
        repository.save(veiculo);
        return veiculo;
    }

    private String uploadImage(MultipartFile foto) {
        String filename = UUID.randomUUID() + "-" + foto.getOriginalFilename();

        try {
            File file = this.converterMultiPartForFile(foto);
            amazonS3Client.putObject(bucketName, filename, file);
            file.delete();
            return amazonS3Client.getUrl(bucketName, filename).toString();
        } catch (IOException ex) {
            System.out.println("Erro ao carrgar a imagem no bucket s3: " + ex.getMessage());
            return null;
        }
    }

    private File converterMultiPartForFile(MultipartFile foto) throws IOException {
        File fileConvert = new File(Objects.requireNonNull(foto.getOriginalFilename()));
        FileOutputStream out = new FileOutputStream(fileConvert);
        out.write(foto.getBytes());
        out.close();
        return fileConvert;
    }
}
