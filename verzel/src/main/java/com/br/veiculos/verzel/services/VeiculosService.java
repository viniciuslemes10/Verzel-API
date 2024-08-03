package com.br.veiculos.verzel.services;

import com.br.veiculos.verzel.exceptions.PhotoNotProvidedException;
import com.br.veiculos.verzel.exceptions.VeiculoNotFoundException;
import com.br.veiculos.verzel.model.Veiculos;
import com.br.veiculos.verzel.records.VeiculosDTO;
import com.br.veiculos.verzel.records.VeiculosDetalhamentoDTO;
import com.br.veiculos.verzel.repository.VeiculosRepository;
import com.br.veiculos.verzel.utils.ValueUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculosService {
    @Autowired
    private VeiculosRepository repository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ValueUpdater updater;


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

    public Page<VeiculosDetalhamentoDTO> getAllVeciculos(Pageable pageable) {
        var veiculosPage = repository.findAll(pageable);
        return veiculosPage.map(VeiculosDetalhamentoDTO::new);
    }

    public void delete(Long id) {
        var veiculo = repository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException("Veículo não encontrado"));
        String filename = s3Service.formatPhotoName(veiculo.getFoto());
        s3Service.deleteImage(filename);
        repository.delete(veiculo);
    }

    public Veiculos updateVeiculo(Long id, VeiculosDTO data) {
        var veiculo = repository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException("Veículo não encontrado"));

        updater.updateIfNotNullOrEmpty(data.nome(), veiculo::setNome);
        updater.updateIfNotNullOrEmpty(data.marca(), veiculo::setMarca);
        updater.updateIfNotNullOrEmpty(data.modelo(), veiculo::setModelo);
        if(data.foto() != null && !data.foto().isEmpty()) {
            String photoUpdate = s3Service.updateVehiclePhoto(veiculo, data.foto());
            veiculo.setFoto(photoUpdate);
        }
        updater.updateIfNotNullOrEmpty(data.valor(), veiculo::setValor);

        return repository.save(veiculo);
    }
}
