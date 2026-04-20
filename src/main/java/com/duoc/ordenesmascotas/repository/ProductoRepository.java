package com.duoc.ordenesmascotas.repository;

import com.duoc.ordenesmascotas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
