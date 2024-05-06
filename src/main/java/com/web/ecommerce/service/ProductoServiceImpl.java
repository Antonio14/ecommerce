package com.web.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ecommerce.controller.HomeController;
import com.web.ecommerce.model.DetalleProducto;
import com.web.ecommerce.model.Producto;
import com.web.ecommerce.repository.ProductoRepository;


@Service
public class ProductoServiceImpl implements ProductoService{
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoService.class);
	
	@Autowired
	private ProductoRepository productoRepository;
	
	
	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return productoRepository.save(producto);
	}

	@Override
	public Optional<Producto> get(Integer id) {
		// TODO Auto-generated method stub
		return productoRepository.findById(id);
	}

	@Override
	public void update(Producto producto) {
		// TODO Auto-generated method stub
		productoRepository.save(producto);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		productoRepository.deleteById(id);
	}

	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Override
	public List<DetalleProducto> findByDetalleProducto(Producto producto) {
		// TODO Auto-generated method stub
		LOGGER.info("Buscando al detalle de producto: {}", producto.getDetalleProductos());
		return productoRepository.findByDetalleProducto(producto);
//		return null;
	}
	



}
