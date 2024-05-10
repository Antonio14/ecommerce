package com.web.ecommerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//registry.addResourceHandler("/images/**").addResourceLocations("file:images/"); // funciona 
		//registry.addResourceHandler("directorio/**").addResourceLocations("file:images/");
		
		//registry.addResourceHandler("/images/**").addResourceLocations("file:C://app//images//");
		
		registry.addResourceHandler("/app/**").addResourceLocations("file:"+System.getProperty("user.dir")+"/");
		
		// nombre del recurso 								//donde se encuentra ese recurso
		//registry.addResourceHandler("/images/**").addResourceLocations("file:src/main/resources/static/images/");
		//C:\images
	}

}
//private String folder="C://app//images//";