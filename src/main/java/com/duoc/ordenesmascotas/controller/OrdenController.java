package com.duoc.ordenesmascotas.controller;

import com.duoc.ordenesmascotas.model.Orden;
import com.duoc.ordenesmascotas.service.OrdenService;
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
    public Orden obtenerPorId(@PathVariable Long id) {
        return ordenService.getById(id);
    }

    @GetMapping("/estado/{estado}")
    public List<Orden> filtrarPorEstado(@PathVariable String estado) {
        return ordenService.getByEstado(estado);
    }
}
