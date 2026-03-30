package com.duoc.ordenesmascotas.service;

import com.duoc.ordenesmascotas.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private List<Producto> productos = new ArrayList<>();

    public ProductoService() {
        productos.add(new Producto(1L, "Alimento Premium Perro 15kg", "Alimentacion", 32990));
        productos.add(new Producto(2L, "Juguete Hueso de Goma", "Juguetes", 5990));
        productos.add(new Producto(3L, "Collar Ajustable Mediano", "Accesorios", 8990));
        productos.add(new Producto(4L, "Cama Rectangular Grande", "Descanso", 24990));
        productos.add(new Producto(5L, "Shampoo Antipulgas 500ml", "Higiene", 7490));
    }

    public List<Producto> getAll() {
        return productos;
    }

    public Producto getById(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
