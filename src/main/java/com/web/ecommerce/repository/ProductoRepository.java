package com.web.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.ecommerce.model.DetalleProducto;
import com.web.ecommerce.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	List<DetalleProducto> findByDetalleProducto (Producto producto);
}
