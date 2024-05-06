package com.web.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.ecommerce.model.DetalleProducto;
import com.web.ecommerce.model.Producto;


@Service
public interface IDetalleProductoService {
	
	DetalleProducto save (DetalleProducto detalleProducto);
	
	List<DetalleProducto> findByDetalleProducto (Producto producto);
	
	public Optional<DetalleProducto> get(Integer id);
}
