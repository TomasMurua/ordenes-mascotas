package com.duoc.ordenesmascotas.controller;

import com.duoc.ordenesmascotas.model.Orden;
import com.duoc.ordenesmascotas.service.OrdenService;
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
    public List<Orden> listarTodas() {
        return ordenService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerPorId(@PathVariable Long id) {
        Orden orden = ordenService.getById(id);
        if (orden == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orden);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Orden>> filtrarPorEstado(@PathVariable String estado) {
        List<Orden> ordenes = ordenService.getByEstado(estado);
        return ResponseEntity.ok(ordenes);
    }
}
