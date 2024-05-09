package com.web.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.ecommerce.model.DetalleOrden;
import com.web.ecommerce.model.DetalleProducto;
import com.web.ecommerce.model.Orden;
import com.web.ecommerce.model.Producto;
import com.web.ecommerce.model.Usuario;
import com.web.ecommerce.service.IDetalleProductoService;
import com.web.ecommerce.service.IUsuarioService;
import com.web.ecommerce.service.ProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {

	private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);


	@Autowired
	private ProductoService productoService;
//
	@Autowired
	private IDetalleProductoService detalleProductoService;
//	
 	@Autowired
	private IUsuarioService usuarioService;
//	
//	
//	@Autowired
//	private IOrdenService ordenService;
//	
//	@Autowired
//	private IDetalleOrdenService detalleOrdenService;

	// para almacenar los detalles de la orden
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
//
//	// datos de la orden
	Orden orden = new Orden();

	@GetMapping("")
	public String home(Model model, HttpSession session) {

		
		LOGGER.info("Sesion del usuario: {}", session.getAttribute("idusuario"));
//		
		model.addAttribute("productos", productoService.findAll());
//		
//		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		return "usuario/home";

	}

	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		LOGGER.info("Id producto enviado como parámetro {}", id);
		
		List<DetalleProducto> detallesp = new ArrayList<>();
		
		Producto producto = new Producto();
		
//		Integer i = (int) (long) id;
		Optional<Producto> productoOptional = productoService.get(id);
		producto = productoOptional.get();
//
//		detallesp = productoService.findByDetalleProducto(producto);
		
		detallesp=producto.getDetalleProductos();
		model.addAttribute("productos", productoService.findAll()); // retorna la lista de autos detalles 
//		for (DetalleProducto detalleProducto : detallesp) {
//			LOGGER.info("Id producto detalle {}", detalleProducto.getProducto());
//		}
		model.addAttribute("productos", detallesp);
		
		model.addAttribute("producto", producto);
		

		return "usuario/productohome";//retorna carpeta usuario/productohome
	}
	
	
	// ver imagen detalle
	@GetMapping("productohomedetalle/{id}") 
	public String productoHomeDetalle(@PathVariable Integer id, Model model) {
		LOGGER.info("Buscando Detalle de un Producto {}", id);

		DetalleProducto detalleProducto = new DetalleProducto();
		Optional<DetalleProducto> productoOptionalDetalle = detalleProductoService.get(id);
		detalleProducto = productoOptionalDetalle.get();

		model.addAttribute("producto", detalleProducto);
		return "usuario/productohomedetalle";//retorna carpeta usuario/productohome
	}
//
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id,  @RequestParam Integer cantidad, Model model) {
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;

		Optional<Producto> optionalProducto = productoService.get(id);
		LOGGER.info("Producto añadido: {}", optionalProducto.get());
		LOGGER.info("Cantidad: {}", cantidad);
		producto = optionalProducto.get();

		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * cantidad);
		detalleOrden.setProducto(producto);
		
		//validar que le producto no se añada 2 veces
		Integer idProducto=producto.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
		
		if (!ingresado) {
			detalles.add(detalleOrden);
		}
		
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}
	

	@GetMapping("/getCart")
	public String getCart(Model model) {
		// HttpSession session
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		//sesion
		//model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "/usuario/carrito";
	}

//	// quitar un producto del carrito
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {

		// lista nueva de prodcutos
		List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

		for (DetalleOrden detalleOrden : detalles) {
			if (detalleOrden.getProducto().getId() != id) {
				ordenesNueva.add(detalleOrden);
			}
		}

		// poner la nueva lista con los productos restantes
		detalles = ordenesNueva;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}
//	
//	@GetMapping("/getCart")
//	public String getCart(Model model, HttpSession session) {
//		
//		model.addAttribute("cart", detalles);
//		model.addAttribute("orden", orden);
//		
//		//sesion
//		model.addAttribute("sesion", session.getAttribute("idusuario"));
//		return "/usuario/carrito";
//	}
//	
	@GetMapping("/order")
	public String order(Model model) {
	
		
		//, HttpSession session
		LOGGER.info("Buscando al usuario: {}");
//		Usuario usuario =usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		Usuario usuario =usuarioService.findById(Integer.valueOf(1)).get();
		
		LOGGER.info("Este es el objeto usuario {}", usuario.getId());
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);
//		
		return "usuario/resumenorden";
	}
//	
//	// guardar la orden
//	@GetMapping("/saveOrder")
//	public String saveOrder(HttpSession session ) {
//		Date fechaCreacion = new Date();
//		orden.setFechaCreacion(fechaCreacion);
//		orden.setNumero(ordenService.generarNumeroOrden());
//		
//		//usuario
//		Usuario usuario =usuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())  ).get();
//		
//		orden.setUsuario(usuario);
//		ordenService.save(orden);
//		
//		//guardar detalles
//		for (DetalleOrden dt:detalles) {
//			dt.setOrden(orden);
//			detalleOrdenService.save(dt);
//		}
//		
//		///limpiar lista y orden
//		orden = new Orden();
//		detalles.clear();
//		
//		return "redirect:/";
//	}
//	
//	@PostMapping("/search")
//	public String searchProduct(@RequestParam String nombre, Model model) {
//		log.info("Nombre del producto: {}", nombre);
//		List<Producto> productos= productoService.findAll().stream().filter( p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
//		model.addAttribute("productos", productos);		
//		return "usuario/home";
//	}

}
