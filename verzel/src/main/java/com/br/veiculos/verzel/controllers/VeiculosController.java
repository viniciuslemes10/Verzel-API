package com.br.veiculos.verzel.controllers;

import com.br.veiculos.verzel.records.VeiculosDetalhamentoDTO;
import com.br.veiculos.verzel.services.VeiculosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/veiculos/public/v1")
public class VeiculosController {

    @Autowired
    private VeiculosService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VeiculosDetalhamentoDTO> getVeiculosById(@PathVariable("id") Long id) {
        var veiculoFound = service.findById(id);
        return ResponseEntity.ok(new VeiculosDetalhamentoDTO(veiculoFound));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VeiculosDetalhamentoDTO>> getAllVeiculos(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "desc") String direction
    ) {
        var sortDirection = "asc".equalsIgnoreCase(direction) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "valor"));
        var veiculos = service.getAllVeciculos(pageable);
        return ResponseEntity.ok(veiculos);
    }
}
