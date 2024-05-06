package com.web.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ecommerce.model.DetalleProducto;
import com.web.ecommerce.model.Producto;
import com.web.ecommerce.repository.IDetalleProductoRepository;

@Service
public class DetalleProductoServiceImpl implements IDetalleProductoService	{

	@Autowired
	IDetalleProductoRepository detalleProductoRepository; 
	
	@Override
	public DetalleProducto save(DetalleProducto detalleProducto) {
		// TODO Auto-generated method stub
		return detalleProductoRepository.save(detalleProducto);
	}

	@Override
	public Optional<DetalleProducto> get(Integer id) {
		// TODO Auto-generated method stub
		return detalleProductoRepository.findById(id);
	}
	@Override
	public List<DetalleProducto> findByDetalleProducto(Producto producto) {
		return null;
				//detalleProductoRepository.findByDetalleProducto(producto);
	}




}
