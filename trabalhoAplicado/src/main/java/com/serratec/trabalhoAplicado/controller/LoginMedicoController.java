package com.serratec.trabalhoAplicado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serratec.trabalhoAplicado.dto.LoginRequest;
import com.serratec.trabalhoAplicado.dto.LoginResponseMedico;
import com.serratec.trabalhoAplicado.service.MedicoService;

@RestController
@RequestMapping("/api/medicos/login")
public class LoginMedicoController {

	@Autowired
	private MedicoService servicoMedico;
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping
	public LoginResponseMedico login (@RequestBody LoginRequest request) {		
		return servicoMedico.logar(request.getUsername(), request.getSenha());
	}
		
}
