package com.duoc.ordenesmascotas.service;

import com.duoc.ordenesmascotas.dto.ProductoRequestDto;
import com.duoc.ordenesmascotas.exception.ResourceNotFoundException;
import com.duoc.ordenesmascotas.model.Producto;
import com.duoc.ordenesmascotas.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> getAll() {
        return repository.findAll();
    }

    public Producto getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: id=" + id));
    }

    public Producto create(ProductoRequestDto dto) {
        log.info("Creando producto nombre={}", dto.getNombre());
        return repository.save(new Producto(dto.getNombre(), dto.getCategoria(), dto.getPrecio()));
    }

    public Producto update(Long id, ProductoRequestDto dto) {
        Producto p = getById(id);
        p.setNombre(dto.getNombre());
        p.setCategoria(dto.getCategoria());
        p.setPrecio(dto.getPrecio());
        log.info("Actualizando producto id={}", id);
        return repository.save(p);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado: id=" + id);
        }
        log.info("Eliminando producto id={}", id);
        repository.deleteById(id);
    }
}
