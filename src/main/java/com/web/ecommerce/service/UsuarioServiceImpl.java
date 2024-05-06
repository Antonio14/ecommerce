package com.web.ecommerce.service;



import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ecommerce.controller.HomeController;
import com.web.ecommerce.model.Usuario;
import com.web.ecommerce.repository.UsuarioRepository;




@Service
public class UsuarioServiceImpl implements IUsuarioService {

	private final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> findById(Integer id) {
		LOGGER.info("Sesion del usuario: {}", id);
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
//		return usuarioRepository.findByEmail(email);
		return null;
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
}
