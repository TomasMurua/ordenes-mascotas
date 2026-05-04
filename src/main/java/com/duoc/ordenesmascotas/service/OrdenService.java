package com.duoc.ordenesmascotas.service;

import com.duoc.ordenesmascotas.dto.OrdenRequestDto;
import com.duoc.ordenesmascotas.dto.OrdenResponseDto;
import com.duoc.ordenesmascotas.exception.ResourceNotFoundException;
import com.duoc.ordenesmascotas.model.Orden;
import com.duoc.ordenesmascotas.repository.OrdenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenService {

    private static final Logger log = LoggerFactory.getLogger(OrdenService.class);

    private final OrdenRepository repository;

    public OrdenService(OrdenRepository repository) {
        this.repository = repository;
    }

    public List<OrdenResponseDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public OrdenResponseDto getById(Long id) {
        Orden o = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada: id=" + id));
        return toDto(o);
    }

    public List<OrdenResponseDto> getByEstado(String estado) {
        return repository.findByEstadoIgnoreCase(estado).stream().map(this::toDto).toList();
    }

    public OrdenResponseDto create(OrdenRequestDto dto) {
        log.info("Creando orden cliente={}", dto.getNombreCliente());
        Orden o = new Orden(dto.getNombreCliente(),
                new ArrayList<>(dto.getProductos()),
                dto.getFechaOrden(),
                dto.getEstado(),
                dto.getTotal());
        return toDto(repository.save(o));
    }

    public OrdenResponseDto update(Long id, OrdenRequestDto dto) {
        Orden o = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada: id=" + id));
        o.setNombreCliente(dto.getNombreCliente());
        o.setProductos(new ArrayList<>(dto.getProductos()));
        o.setFechaOrden(dto.getFechaOrden());
        o.setEstado(dto.getEstado());
        o.setTotal(dto.getTotal());
        log.info("Actualizando orden id={}", id);
        return toDto(repository.save(o));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Orden no encontrada: id=" + id);
        }
        log.info("Eliminando orden id={}", id);
        repository.deleteById(id);
    }

    private OrdenResponseDto toDto(Orden o) {
        return new OrdenResponseDto(o.getId(), o.getNombreCliente(),
                new ArrayList<>(o.getProductos()),
                o.getFechaOrden(), o.getEstado(), o.getTotal());
    }
}
