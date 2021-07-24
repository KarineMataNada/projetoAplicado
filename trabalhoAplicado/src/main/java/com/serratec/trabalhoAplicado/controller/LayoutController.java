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

import com.serratec.trabalhoAplicado.model.Layout;
import com.serratec.trabalhoAplicado.service.LayoutService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/layouts")
public class LayoutController {

	
	@Autowired
	private LayoutService servicoLayout;
		
	
	@GetMapping
	public List<Layout> obterTodos() {
		return servicoLayout.obterTodos();
	}
	
	@GetMapping("/{id}")
	public Optional<Layout> obterPorId(@PathVariable("id")Long id){
		return servicoLayout.obterPorId(id);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Layout> obterPorNome(@PathVariable("nome")String nome){
		return servicoLayout.obterPorNome(nome);
	}
	
	@PostMapping
	public Layout adicionar(@RequestBody @Valid Layout layout) {		
		return servicoLayout.adicionar(layout);
	}
	
	@PutMapping("/{id}")
	public Layout atualizar(@PathVariable(value = "id")Long id, @RequestBody Layout layout){
		return servicoLayout.atualizar(layout, id);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoLayout.deletar(id);
	}
		
}
