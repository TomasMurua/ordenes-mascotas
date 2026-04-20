package com.duoc.ordenesmascotas.config;

import com.duoc.ordenesmascotas.model.Orden;
import com.duoc.ordenesmascotas.model.Producto;
import com.duoc.ordenesmascotas.repository.OrdenRepository;
import com.duoc.ordenesmascotas.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final ProductoRepository productoRepo;
    private final OrdenRepository ordenRepo;

    public DataInitializer(ProductoRepository productoRepo, OrdenRepository ordenRepo) {
        this.productoRepo = productoRepo;
        this.ordenRepo = ordenRepo;
    }

    @Override
    public void run(String... args) {
        if (productoRepo.count() > 0) {
            log.info("Base de datos ya tiene datos, se omite carga inicial");
            return;
        }
        log.info("Cargando datos iniciales...");

        productoRepo.save(new Producto("Alimento Premium Perro 15kg", "Alimentacion", 32990));
        productoRepo.save(new Producto("Juguete Hueso de Goma", "Juguetes", 5990));
        productoRepo.save(new Producto("Collar Ajustable Mediano", "Accesorios", 8990));
        productoRepo.save(new Producto("Cama Rectangular Grande", "Descanso", 24990));
        productoRepo.save(new Producto("Shampoo Antipulgas 500ml", "Higiene", 7490));

        ordenRepo.save(new Orden("Maria Lopez",
                new java.util.ArrayList<>(List.of("Alimento Premium Perro 15kg", "Juguete Hueso de Goma")),
                LocalDate.of(2026, 3, 25), "pendiente", 38980));
        ordenRepo.save(new Orden("Carlos Munoz",
                new java.util.ArrayList<>(List.of("Collar Ajustable Mediano", "Shampoo Antipulgas 500ml")),
                LocalDate.of(2026, 3, 22), "enviada", 16480));
        ordenRepo.save(new Orden("Ana Torres",
                new java.util.ArrayList<>(List.of("Cama Rectangular Grande")),
                LocalDate.of(2026, 3, 18), "entregada", 24990));

        log.info("Datos iniciales cargados: {} productos, {} ordenes",
                productoRepo.count(), ordenRepo.count());
    }
}
