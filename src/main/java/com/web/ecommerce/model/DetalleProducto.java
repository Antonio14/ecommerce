package com.web.ecommerce.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalleproducto")
public class DetalleProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String imagen;

	@ManyToOne 
	private Producto producto;
	
	public DetalleProducto() {
	}  

	public DetalleProducto(Integer id, String imagen) {
		super();
		this.id = id;
		this.imagen = imagen;

	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", imagen=" + imagen + "]";
	}
}
