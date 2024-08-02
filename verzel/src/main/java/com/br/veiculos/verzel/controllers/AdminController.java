package com.br.veiculos.verzel.controllers;

import com.br.veiculos.verzel.records.VeiculosDTO;
import com.br.veiculos.verzel.records.VeiculosDetalhamentoDTO;
import com.br.veiculos.verzel.services.VeiculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/veiculos/admin/v1")
public class AdminController {

    @Autowired
    private VeiculosService service;

    @PostMapping(consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VeiculosDetalhamentoDTO> create(
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "marca") String marca,
            @RequestParam(value = "modelo") String modelo,
            @RequestParam(value = "valor") BigDecimal valor,
            @RequestParam(value = "foto") MultipartFile foto
    ) {
        VeiculosDTO data = new VeiculosDTO(nome, marca, modelo, valor, foto);
        var veiculoSaved = service.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(new VeiculosDetalhamentoDTO(veiculoSaved));
    }

    @PutMapping(consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VeiculosDetalhamentoDTO> updateVeiculo(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "marca") String marca,
            @RequestParam(value = "modelo") String modelo,
            @RequestParam(value = "valor") BigDecimal valor,
            @RequestParam(value = "foto") MultipartFile foto
    ) {
        var data = new VeiculosDTO(nome, marca, modelo, valor, foto);
        var veiculoUpdate = service.updateVeiculo(id, data);
        return ResponseEntity.ok(new VeiculosDetalhamentoDTO(veiculoUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
