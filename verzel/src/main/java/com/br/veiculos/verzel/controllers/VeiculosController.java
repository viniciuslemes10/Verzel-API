package com.br.veiculos.verzel.controllers;

import com.br.veiculos.verzel.records.UsuarioDetalhamentoDTO;
import com.br.veiculos.verzel.records.VeiculosDetalhamentoDTO;
import com.br.veiculos.verzel.services.VeiculosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Veículos")
public class VeiculosController {

    @Autowired
    private VeiculosService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/redefinir-senha")
    @Operation(
            summary = "Exibir Veículo",
            description = "Forneça o ID do veículo para exibir os detalhes correspondentes. Este endpoint retornará as informações completas do veículo associado ao ID fornecido.",
            tags = {"Veículos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
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
    public ResponseEntity<VeiculosDetalhamentoDTO> getVeiculosById(@PathVariable("id") Long id) {
        var veiculoFound = service.findById(id);
        return ResponseEntity.ok(new VeiculosDetalhamentoDTO(veiculoFound));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Listar Todos os Veículos",
            description = "Recupera uma lista de todos os veículos registrados. É possível paginar e ordenar os resultados especificando os parâmetros de paginação e direção de ordenação.",
            tags = {"Veículos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = VeiculosDetalhamentoDTO.class
                                            )
                                    )
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Authorization", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
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
