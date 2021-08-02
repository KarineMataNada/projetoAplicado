package com.serratec.trabalhoAplicado.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serratec.trabalhoAplicado.model.Perfil;
import com.serratec.trabalhoAplicado.service.PerfilService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/perfis")
public class PerfilController {

	
	@Autowired
	private PerfilService servicoPerfil;
		
	
	@GetMapping
	public List<Perfil> obterTodos() {
		return servicoPerfil.obterTodos();
	}
	
	@GetMapping("/{id}")
	public Optional<Perfil> obterPorId(@PathVariable("id")Long id){
		return servicoPerfil.obterPorId(id);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Perfil> obterPorNome(@PathVariable("nome")String nome){
		return servicoPerfil.obterPorNome(nome);
	}
	
	@PostMapping
	public Perfil adicionar(@RequestBody @Valid Perfil perfil) {		
		return servicoPerfil.adicionar(perfil);
	}
	
	@PutMapping("/{id}")
	public Perfil atualizar(@PathVariable(value = "id")Long id, @RequestBody Perfil perfil){
		return servicoPerfil.atualizar(perfil, id);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoPerfil.deletar(id);
	}
		
}
