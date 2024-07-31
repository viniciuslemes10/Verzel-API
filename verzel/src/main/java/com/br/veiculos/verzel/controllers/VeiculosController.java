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
@RequestMapping("api/veiculos/v1")
public class VeiculosController {

    @Autowired
    private VeiculosService service;

    @PostMapping(consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VeiculosDetalhamentoDTO> create(
            @RequestParam("nome") String nome,
            @RequestParam("marca") String marca,
            @RequestParam("modelo") String modelo,
            @RequestParam("valor") BigDecimal valor,
            @RequestParam("foto") MultipartFile foto
    ) {
        VeiculosDTO data = new VeiculosDTO(nome, marca, modelo, valor, foto);
        var veiculoSaved = service.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(new VeiculosDetalhamentoDTO(veiculoSaved));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VeiculosDetalhamentoDTO> getVeiculosById(@PathVariable("id") Long id) {
        var veiculoFound = service.findById(id);
        return ResponseEntity.ok(new VeiculosDetalhamentoDTO(veiculoFound));
    }
}
