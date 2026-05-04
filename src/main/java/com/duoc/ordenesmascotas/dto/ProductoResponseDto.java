package com.duoc.ordenesmascotas.dto;

import org.springframework.hateoas.RepresentationModel;

public class ProductoResponseDto extends RepresentationModel<ProductoResponseDto> {

    private Long id;
    private String nombre;
    private String categoria;
    private double precio;

    public ProductoResponseDto() {}

    public ProductoResponseDto(Long id, String nombre, String categoria, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}
