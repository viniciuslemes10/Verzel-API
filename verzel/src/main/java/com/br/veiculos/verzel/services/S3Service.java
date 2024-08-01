package com.br.veiculos.verzel.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.br.veiculos.verzel.model.Veiculos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private S3Client s3Client;

    @Autowired
    public S3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String uploadImage(MultipartFile image) {
        String filename = UUID.randomUUID() + "-" + image.getOriginalFilename();

        try {
            File file = this.convertMultiPartForFile(image);
            amazonS3.putObject(bucketName, filename, file);
            file.delete();
            return amazonS3.getUrl(bucketName, filename).toString();
        } catch (IOException ex) {
            System.out.println("Erro ao carrgar a imagem no bucket s3: " + ex.getMessage());
            return null;
        }
    }

    private File convertMultiPartForFile(MultipartFile image) throws IOException {
        File fileConvert = new File(Objects.requireNonNull(image.getOriginalFilename()));
        FileOutputStream out = new FileOutputStream(fileConvert);
        out.write(image.getBytes());
        out.close();
        return fileConvert;
    }

    public void deleteImage(String image) {
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, image);
        amazonS3.deleteObject(deleteObjectRequest);
    }

    public String updateVehiclePhoto(Veiculos veiculos, MultipartFile image) {
        String filename = this.formatPhotoName(veiculos.getFoto());
        this.deleteImage(filename);
        return this.uploadImage(image);
    }

    public String formatPhotoName(String image) {
        int lastSlashIndex = image.lastIndexOf("/");
        return image.substring(lastSlashIndex + 1);
    }
}
