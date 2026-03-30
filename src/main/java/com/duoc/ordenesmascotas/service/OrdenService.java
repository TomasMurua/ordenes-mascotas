package com.duoc.ordenesmascotas.service;

import com.duoc.ordenesmascotas.model.Orden;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenService {

    private List<Orden> ordenes = new ArrayList<>();

    public OrdenService() {
        ordenes.add(new Orden(1L, "Maria Lopez",
                List.of("Alimento Premium Perro 15kg", "Juguete Hueso de Goma"),
                "2026-03-25", "pendiente", 38980));

        ordenes.add(new Orden(2L, "Carlos Muñoz",
                List.of("Collar Ajustable Mediano", "Shampoo Antipulgas 500ml"),
                "2026-03-22", "enviada", 16480));

        ordenes.add(new Orden(3L, "Ana Torres",
                List.of("Cama Rectangular Grande"),
                "2026-03-18", "entregada", 24990));
    }

    public List<Orden> getAll() {
        return ordenes;
    }

    public Orden getById(Long id) {
        return ordenes.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Orden> getByEstado(String estado) {
        return ordenes.stream()
                .filter(o -> o.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }
}
