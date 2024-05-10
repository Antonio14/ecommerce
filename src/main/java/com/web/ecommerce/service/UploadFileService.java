package com.web.ecommerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.ecommerce.controller.ProductoController;

@Service
public class UploadFileService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(UploadFileService.class);
	//private String folder="images//";
	//
	//
	String directorioTest = System.getProperty("user.dir")+"//app//";
	String directorioTest1 = System.getProperty("user.dir")+"//app//";
	
	//
	
	private String folder="//app//";
	//String directorio = System.getProperty("user.dir")+"//images";
	//private String folder=directorio+"//";// en este directorio guarda las imagenes dentro del proyecto 
	
	//private String folder = "src//main//resources//static//images"; // directorio donde guarda imagen
	
	
	public String saveImage(MultipartFile file) throws IOException {
		LOGGER.info("UploadFileService  folder step 1 {}");
		
		LOGGER.info("directorioTest  step 1 {}", directorioTest);
		
		LOGGER.info("directorioTest1  step 1 {}", directorioTest1);
		
		
		if (!file.isEmpty()) {
			byte [] bytes=file.getBytes();
			Path path =Paths.get(folder+file.getOriginalFilename()); // en este punto guarda o escribe dentro del proyecto 
			
			//Path path =Paths.get(folder+file.getOriginalFilename()); // en este punto guarda o escribe dentro del proyecto 
			LOGGER.info("folder  step 1 {}" , path.toString());
			Files.write(path, bytes);
			LOGGER.info("folder step  {}" , path.toString());
			LOGGER.info("UploadFileService ims step 1 {}" , path.toString());
			LOGGER.info("file.getOriginalFilename() ims step 1 {}" , file.getOriginalFilename());
			return file.getOriginalFilename(); // retorna el nombre para guarda en base de datos 
		}
		return "default.jpg";
	}
	
	public void deleteImage(String nombre) {
		String ruta="images//";
		File file= new File(ruta+nombre);
		file.delete();
	}

}
