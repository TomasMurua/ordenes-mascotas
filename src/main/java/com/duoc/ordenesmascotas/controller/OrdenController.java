package com.duoc.ordenesmascotas.controller;

import com.duoc.ordenesmascotas.dto.OrdenRequestDto;
import com.duoc.ordenesmascotas.model.Orden;
import com.duoc.ordenesmascotas.service.OrdenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping
    public List<Orden> listar() {
        return ordenService.getAll();
    }

    @GetMapping("/{id}")
    public Orden obtener(@PathVariable Long id) {
        return ordenService.getById(id);
    }

    @GetMapping("/estado/{estado}")
    public List<Orden> porEstado(@PathVariable String estado) {
        return ordenService.getByEstado(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Orden crear(@Valid @RequestBody OrdenRequestDto dto) {
        return ordenService.create(dto);
    }

    @PutMapping("/{id}")
    public Orden actualizar(@PathVariable Long id, @Valid @RequestBody OrdenRequestDto dto) {
        return ordenService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
