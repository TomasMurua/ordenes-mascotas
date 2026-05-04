package com.duoc.ordenesmascotas.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.List;

public class OrdenResponseDto extends RepresentationModel<OrdenResponseDto> {

    private Long id;
    private String nombreCliente;
    private List<String> productos;
    private LocalDate fechaOrden;
    private String estado;
    private double total;

    public OrdenResponseDto() {}

    public OrdenResponseDto(Long id, String nombreCliente, List<String> productos,
                            LocalDate fechaOrden, String estado, double total) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.productos = productos;
        this.fechaOrden = fechaOrden;
        this.estado = estado;
        this.total = total;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
