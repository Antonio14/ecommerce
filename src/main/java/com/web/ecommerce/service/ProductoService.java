package com.web.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.web.ecommerce.model.DetalleProducto;
import com.web.ecommerce.model.Producto;

public interface ProductoService {
	public Producto save(Producto producto);

	public Optional<Producto> get(Integer id);

	public void update(Producto producto);

	public void delete(Integer id);

	public List<Producto> findAll();
	
	List<DetalleProducto> findByDetalleProducto (Producto Producto);

}
