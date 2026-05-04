package com.duoc.ordenesmascotas.controller;

import com.duoc.ordenesmascotas.dto.OrdenRequestDto;
import com.duoc.ordenesmascotas.dto.OrdenResponseDto;
import com.duoc.ordenesmascotas.service.OrdenService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping
    public CollectionModel<OrdenResponseDto> listar() {
        List<OrdenResponseDto> ordenes = ordenService.getAll();
        ordenes.forEach(this::agregarLinks);
        return CollectionModel.of(ordenes,
                linkTo(methodOn(OrdenController.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public OrdenResponseDto obtener(@PathVariable Long id) {
        return agregarLinks(ordenService.getById(id));
    }

    @GetMapping("/estado/{estado}")
    public CollectionModel<OrdenResponseDto> porEstado(@PathVariable String estado) {
        List<OrdenResponseDto> ordenes = ordenService.getByEstado(estado);
        ordenes.forEach(this::agregarLinks);
        return CollectionModel.of(ordenes,
                linkTo(methodOn(OrdenController.class).porEstado(estado)).withSelfRel(),
                linkTo(methodOn(OrdenController.class).listar()).withRel("ordenes"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdenResponseDto crear(@Valid @RequestBody OrdenRequestDto dto) {
        return agregarLinks(ordenService.create(dto));
    }

    @PutMapping("/{id}")
    public OrdenResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody OrdenRequestDto dto) {
        return agregarLinks(ordenService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private OrdenResponseDto agregarLinks(OrdenResponseDto o) {
        o.add(linkTo(methodOn(OrdenController.class).obtener(o.getId())).withSelfRel());
        o.add(linkTo(methodOn(OrdenController.class).listar()).withRel("ordenes"));
        return o;
    }
}
