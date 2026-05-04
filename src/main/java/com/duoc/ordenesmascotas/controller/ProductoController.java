package com.duoc.ordenesmascotas.controller;

import com.duoc.ordenesmascotas.dto.ProductoRequestDto;
import com.duoc.ordenesmascotas.dto.ProductoResponseDto;
import com.duoc.ordenesmascotas.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public CollectionModel<ProductoResponseDto> listar() {
        List<ProductoResponseDto> productos = productoService.getAll();
        productos.forEach(this::agregarLinks);
        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoController.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ProductoResponseDto obtener(@PathVariable Long id) {
        return agregarLinks(productoService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDto crear(@Valid @RequestBody ProductoRequestDto dto) {
        return agregarLinks(productoService.create(dto));
    }

    @PutMapping("/{id}")
    public ProductoResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDto dto) {
        return agregarLinks(productoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ProductoResponseDto agregarLinks(ProductoResponseDto p) {
        p.add(linkTo(methodOn(ProductoController.class).obtener(p.getId())).withSelfRel());
        p.add(linkTo(methodOn(ProductoController.class).listar()).withRel("productos"));
        return p;
    }
}
