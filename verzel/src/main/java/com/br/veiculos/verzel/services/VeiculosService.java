package com.br.veiculos.verzel.services;

import com.br.veiculos.verzel.exceptions.PhotoNotProvidedException;
import com.br.veiculos.verzel.exceptions.VeiculoNotFoundException;
import com.br.veiculos.verzel.model.Veiculos;
import com.br.veiculos.verzel.records.VeiculosDTO;
import com.br.veiculos.verzel.records.VeiculosDetalhamentoDTO;
import com.br.veiculos.verzel.repository.VeiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculosService {
    @Autowired
    private VeiculosRepository repository;

    @Autowired
    private S3Service s3Service;

    public Veiculos create(VeiculosDTO data) {
        String generatedImage = null;

        if(data.foto().isEmpty()) {
            throw new PhotoNotProvidedException("Foto do veículo não foi fornecida!");
        } else {
            generatedImage = s3Service.uploadImage(data.foto());
        }

        var veiculo = new Veiculos(data);
        veiculo.setFoto(generatedImage);
        repository.save(veiculo);
        return veiculo;
    }

    public Veiculos findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException("Veículo não encontrado"));
    }

    public List<VeiculosDetalhamentoDTO> getAllVeciculos(Pageable pageable) {
        var veiculos = repository.findAll(pageable);
        return veiculos.stream().map(VeiculosDetalhamentoDTO::new).toList();
    }

    public void delete(Long id) {
        var veiculo = repository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException("Veículo não encontrado"));
        String filename = this.formatPhotoName(veiculo.getFoto());
        s3Service.deleteImage(filename);
        repository.delete(veiculo);
    }

    private String formatPhotoName(String foto) {
        int lastSlashIndex = foto.lastIndexOf("/");
        return foto.substring(lastSlashIndex + 1);
    }
}
