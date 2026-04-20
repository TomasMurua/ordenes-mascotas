package com.duoc.ordenesmascotas.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class OrdenRequestDto {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(min = 3, max = 120)
    private String nombreCliente;

    @NotNull(message = "Debe incluir productos")
    @Size(min = 1, message = "La orden debe tener al menos un producto")
    private List<@NotBlank @Size(max = 200) String> productos;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fechaOrden;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "pendiente|enviada|entregada|cancelada",
             message = "Estado debe ser: pendiente, enviada, entregada o cancelada")
    private String estado;

    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser positivo")
    private double total;

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public List<String> getProductos() { return productos; }
    public void setProductos(List<String> productos) { this.productos = productos; }

    public LocalDate getFechaOrden() { return fechaOrden; }
    public void setFechaOrden(LocalDate fechaOrden) { this.fechaOrden = fechaOrden; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
