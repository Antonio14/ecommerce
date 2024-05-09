package com.web.ecommerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		String directorio = System.getProperty("user.dir")+"\\images";
		//registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
		registry.addResourceHandler("directorio/**").addResourceLocations("file:images/");
	}

}
