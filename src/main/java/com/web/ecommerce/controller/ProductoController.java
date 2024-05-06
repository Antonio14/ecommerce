package com.web.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.web.ecommerce.model.DetalleProducto;
import com.web.ecommerce.model.Producto;
import com.web.ecommerce.model.Usuario;
import com.web.ecommerce.service.IDetalleProductoService;
import com.web.ecommerce.service.ProductoService;
import com.web.ecommerce.service.UploadFileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	ProductoService productoService;

	@Autowired
	private UploadFileService upload;

	// para guardar los detalles del producto

	@Autowired
	private IDetalleProductoService detalleProductoService;

	// para almacenar los detalles del producto
	List<DetalleProducto> detalles = new ArrayList<DetalleProducto>();

	// datos del producto
	Producto producto = new Producto();

	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "productos/show";
	}

	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}

	@PostMapping("/save")
	public String save(Producto producto, @RequestParam("img") MultipartFile file,
			@RequestParam("img1") MultipartFile file1, @RequestParam("img2") MultipartFile file2,
			@RequestParam("img3") MultipartFile file3, @RequestParam("img4") MultipartFile file4,
			@RequestParam("img5") MultipartFile file5, @RequestParam("img6") MultipartFile file6,
			@RequestParam("img7") MultipartFile file7, @RequestParam("img8") MultipartFile file8) throws IOException {
		
		LOGGER.info("Este es el objeto producto {}", producto);
		Usuario usuario = new Usuario(1, "", "", "", "", "", "", "");
		producto.setUsuario(usuario);

		// imagen
		if (producto.getId() == null) { // cuando se crea un producto
			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);
		} else {

		}

		LOGGER.info("Este es el objeto producto detalle {}", producto);
		productoService.save(producto);
		
		DetalleProducto detalleProducto = new DetalleProducto();
		String nombreImagenDet1 = upload.saveImage(file);
		String nombreImagenDet2 = upload.saveImage(file2);
		String nombreImagenDet3 = upload.saveImage(file3);
		String nombreImagenDet4 = upload.saveImage(file4);
		String nombreImagenDet5 = upload.saveImage(file5);
		String nombreImagenDet6 = upload.saveImage(file6);
		String nombreImagenDet7 = upload.saveImage(file7);
		String nombreImagenDet8 = upload.saveImage(file8);
		//1
		detalleProducto.setImagen(nombreImagenDet1);
		detalleProducto.setProducto(producto);
		detalleProductoService.save(detalleProducto);
		//2
		detalleProducto = new DetalleProducto();
		detalleProducto.setImagen(nombreImagenDet2);
		detalleProducto.setProducto(producto);
		detalleProductoService.save(detalleProducto);
		//3
		detalleProducto = new DetalleProducto();
		detalleProducto.setImagen(nombreImagenDet3);
		detalleProducto.setProducto(producto);
		detalleProductoService.save(detalleProducto);
		//4
		detalleProducto = new DetalleProducto();
		detalleProducto.setImagen(nombreImagenDet4);
		detalleProducto.setProducto(producto);
		detalleProductoService.save(detalleProducto);
		//5
		detalleProducto = new DetalleProducto();
		detalleProducto.setImagen(nombreImagenDet5);
		detalleProducto.setProducto(producto);
		detalleProductoService.save(detalleProducto);
		//6
		detalleProducto = new DetalleProducto();
		detalleProducto.setImagen(nombreImagenDet6);
		detalleProducto.setProducto(producto);
		detalleProductoService.save(detalleProducto);
		//7
		detalleProducto = new DetalleProducto();
		detalleProducto.setImagen(nombreImagenDet7);
		detalleProducto.setProducto(producto);
		detalleProductoService.save(detalleProducto);
		//8
		detalleProducto = new DetalleProducto();
		detalleProducto.setImagen(nombreImagenDet8);
		detalleProducto.setProducto(producto);
		detalleProductoService.save(detalleProducto);
		
		
		//List<DetalleProducto> detalleProductos = new ArrayList<>(); // se quiere agregar la lista de detalles de fotos
		//detalleProductos.add(detalleProducto);
		//producto.setDetalleProductos(detalleProductos);

		// guardar detalle del producto
		// guardar detalles
//		for (DetalleProducto dp : detalles) {
//			dp.setProducto(producto);
//			detalleProductoService.save(dp);
//		}

		
		return "redirect:/productos";
	}

	@PostMapping("/update")
	public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
		Producto p = new Producto();
		p = productoService.get(producto.getId()).get();

		if (file.isEmpty()) { // editamos el producto pero no cambiamos la imagem

			producto.setImagen(p.getImagen());
		} else {// cuando se edi ta tbn la imagen
				// eliminar cuando no sea la imagen por defecto
			if (!p.getImagen().equals("default.jpg")) {
				upload.deleteImage(p.getImagen());
			}
			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		producto.setUsuario(p.getUsuario());
		productoService.update(producto);
		return "redirect:/productos";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id);
		producto = optionalProducto.get();

		LOGGER.info("Producto buscado: {}", producto);
		model.addAttribute("producto", producto);

		return "productos/edit";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {

		Producto p = new Producto();
		p = productoService.get(id).get();

		// eliminar cuando no sea la imagen por defecto
		if (!p.getImagen().equals("default.jpg")) {
			upload.deleteImage(p.getImagen());
		}

		productoService.delete(id);
		return "redirect:/productos";
	}

}
