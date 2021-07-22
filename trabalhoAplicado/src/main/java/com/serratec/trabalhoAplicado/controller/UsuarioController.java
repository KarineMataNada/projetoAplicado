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

import com.serratec.trabalhoAplicado.model.Usuario;
import com.serratec.trabalhoAplicado.service.UsuarioService;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	
	@Autowired
	private UsuarioService servicoUsuario;
		
	
	@GetMapping
	public List<Usuario> obterTodos() {
		return servicoUsuario.obterTodos();
	}
	
	@GetMapping("/{id}")
	public Optional<Usuario> obterPorId(@PathVariable("id")Long id){
		return servicoUsuario.obterPorId(id);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Usuario> obterPorNome(@PathVariable("nome")String nome){
		return servicoUsuario.obterPorNome(nome);
	}
	
	@PostMapping
	public Usuario adicionar(@RequestBody @Valid Usuario usuario) {		
		return servicoUsuario.adicionar(usuario);
	}
	
	@PutMapping("/{id}")
	public Usuario atualizar(@PathVariable(value = "id")Long id, @RequestBody Usuario usuario){
		return servicoUsuario.atualizar(usuario, id);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoUsuario.deletar(id);
	}
		
}
