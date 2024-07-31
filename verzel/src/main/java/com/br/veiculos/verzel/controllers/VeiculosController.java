package com.br.veiculos.verzel.controllers;

import com.br.veiculos.verzel.records.VeiculosDTO;
import com.br.veiculos.verzel.records.VeiculosDetalhamentoDTO;
import com.br.veiculos.verzel.services.VeiculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/veiculos/v1")
public class VeiculosController {

    @Autowired
    private VeiculosService service;

    @PostMapping(consumes = "multipart/form-data")
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
}
