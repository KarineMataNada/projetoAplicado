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

import com.serratec.trabalhoAplicado.model.Secretaria;
import com.serratec.trabalhoAplicado.service.SecretariaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/secretarias")
public class SecretariaController {

	
	@Autowired
	private SecretariaService servicoSecretaria;
		
	
	@GetMapping
	public List<Secretaria> obterTodos() {
		return servicoSecretaria.obterTodos();
	}
	
	@GetMapping("/{id}")
	public Optional<Secretaria> obterPorId(@PathVariable("id")Long id){
		return servicoSecretaria.obterPorId(id);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Secretaria> obterPorNome(@PathVariable("nome")String nome){
		return servicoSecretaria.obterPorNome(nome);
	}
	
	@PostMapping
	public Secretaria adicionar(@RequestBody @Valid Secretaria secretaria) {		
		return servicoSecretaria.adicionar(secretaria);
	}
	
	@PutMapping("/{id}")
	public Secretaria atualizar(@PathVariable(value = "id")Long id, @RequestBody Secretaria secretaria){
		return servicoSecretaria.atualizar(secretaria, id);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoSecretaria.deletar(id);
	}
}