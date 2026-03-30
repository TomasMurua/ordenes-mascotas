package com.duoc.ordenesmascotas.model;

import java.util.List;

public class Orden {

    private Long id;
    private String nombreCliente;
    private List<String> productos;
    private String fechaOrden;
    private String estado;
    private double total;

    public Orden() {
    }

    public Orden(Long id, String nombreCliente, List<String> productos, String fechaOrden, String estado, double total) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.productos = productos;
        this.fechaOrden = fechaOrden;
        this.estado = estado;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
