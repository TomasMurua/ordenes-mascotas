package com.duoc.ordenesmascotas.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDENES")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_cliente", nullable = false, length = 120)
    private String nombreCliente;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ORDEN_PRODUCTOS",
                     joinColumns = @JoinColumn(name = "orden_id"))
    @Column(name = "producto", length = 200)
    private List<String> productos = new ArrayList<>();

    @Column(name = "fecha_orden", nullable = false)
    private LocalDate fechaOrden;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(nullable = false)
    private double total;

    public Orden() {}

    public Orden(String nombreCliente, List<String> productos, LocalDate fechaOrden, String estado, double total) {
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
