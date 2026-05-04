package com.duoc.ordenesmascotas.service;

import com.duoc.ordenesmascotas.dto.ProductoRequestDto;
import com.duoc.ordenesmascotas.dto.ProductoResponseDto;
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

    public List<ProductoResponseDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public ProductoResponseDto getById(Long id) {
        Producto p = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: id=" + id));
        return toDto(p);
    }

    public ProductoResponseDto create(ProductoRequestDto dto) {
        log.info("Creando producto nombre={}", dto.getNombre());
        Producto saved = repository.save(new Producto(dto.getNombre(), dto.getCategoria(), dto.getPrecio()));
        return toDto(saved);
    }

    public ProductoResponseDto update(Long id, ProductoRequestDto dto) {
        Producto p = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: id=" + id));
        p.setNombre(dto.getNombre());
        p.setCategoria(dto.getCategoria());
        p.setPrecio(dto.getPrecio());
        log.info("Actualizando producto id={}", id);
        return toDto(repository.save(p));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado: id=" + id);
        }
        log.info("Eliminando producto id={}", id);
        repository.deleteById(id);
    }

    private ProductoResponseDto toDto(Producto p) {
        return new ProductoResponseDto(p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio());
    }
}
