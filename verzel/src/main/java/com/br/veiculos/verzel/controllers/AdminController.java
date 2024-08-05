package com.br.veiculos.verzel.controllers;

import com.br.veiculos.verzel.records.VeiculosDTO;
import com.br.veiculos.verzel.records.VeiculosDetalhamentoDTO;
import com.br.veiculos.verzel.services.VeiculosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/veiculos/admin/v1")
@Tag(name = "Admin", description = "Endpoints exclusivos para usuários com permissão de ADMIN")
public class AdminController {

    @Autowired
    private VeiculosService service;

    @PostMapping(consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criando um Veículo", description = "Add novo Veículo passado em multipart/form-data, representando um veículo",
            tags = {"Admin"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(
                                    mediaType = "multipart/form-data",
                                    schema = @Schema(
                                            implementation = VeiculosDetalhamentoDTO.class
                                    )
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Authorization", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
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
    @Operation(summary = "Atualizando um Veículo", description = "Atualizando um Veículo passado em multipart/form-data",
            tags = {"Admin"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = "multipart/form-data",
                                    schema = @Schema(
                                            implementation = VeiculosDetalhamentoDTO.class
                                    )
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Authorization", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
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
    @Operation(summary = "Deletando Veículo", description = "Deletando Veículo",
            tags = {"Admin"},
            responses = {
                    @ApiResponse(description = "Deleted", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Authorization", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
