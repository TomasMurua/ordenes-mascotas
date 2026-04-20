package com.duoc.ordenesmascotas.dto;

import jakarta.validation.constraints.*;

public class ProductoRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 120)
    private String nombre;

    @NotBlank(message = "La categoria es obligatoria")
    @Size(max = 50)
    private String categoria;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser positivo")
    private double precio;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}
