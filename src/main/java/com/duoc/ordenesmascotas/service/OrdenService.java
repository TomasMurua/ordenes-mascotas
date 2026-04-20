package com.duoc.ordenesmascotas.service;

import com.duoc.ordenesmascotas.dto.OrdenRequestDto;
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

    public List<Orden> getAll() {
        return repository.findAll();
    }

    public Orden getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada: id=" + id));
    }

    public List<Orden> getByEstado(String estado) {
        return repository.findByEstadoIgnoreCase(estado);
    }

    public Orden create(OrdenRequestDto dto) {
        log.info("Creando orden cliente={}", dto.getNombreCliente());
        Orden o = new Orden(dto.getNombreCliente(),
                new ArrayList<>(dto.getProductos()),
                dto.getFechaOrden(),
                dto.getEstado(),
                dto.getTotal());
        return repository.save(o);
    }

    public Orden update(Long id, OrdenRequestDto dto) {
        Orden o = getById(id);
        o.setNombreCliente(dto.getNombreCliente());
        o.setProductos(new ArrayList<>(dto.getProductos()));
        o.setFechaOrden(dto.getFechaOrden());
        o.setEstado(dto.getEstado());
        o.setTotal(dto.getTotal());
        log.info("Actualizando orden id={}", id);
        return repository.save(o);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Orden no encontrada: id=" + id);
        }
        log.info("Eliminando orden id={}", id);
        repository.deleteById(id);
    }
}
