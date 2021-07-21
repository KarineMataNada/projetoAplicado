package com.serratec.trabalhoAplicado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serratec.trabalhoAplicado.dto.LoginRequest;
import com.serratec.trabalhoAplicado.dto.LoginResponse;
import com.serratec.trabalhoAplicado.service.UsuarioService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private UsuarioService servicoUsuario;
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping
	public LoginResponse login (@RequestBody LoginRequest request) {		
		return servicoUsuario.logar(request.getUsername(), request.getSenha());
	}
		
}
