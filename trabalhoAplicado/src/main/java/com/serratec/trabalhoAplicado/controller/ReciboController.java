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


import com.serratec.trabalhoAplicado.model.Recibo;
import com.serratec.trabalhoAplicado.service.ReciboService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/recibos")
public class ReciboController {

	
	@Autowired
	private ReciboService servicoRecibo;
		
	
	@GetMapping
	public List<Recibo> obterTodos() {
		return servicoRecibo.obterTodos();
	}
	
	@GetMapping("/finalizados")
	public List<Recibo> obterTodosFinalizados() {
		return servicoRecibo.obterTodosFinalizados(null);
	}
	
	@GetMapping("/{id}")
	public Optional<Recibo> obterPorId(@PathVariable("id")Long id){
		return servicoRecibo.obterPorId(id);
	}
	
	
	@PostMapping
	public Recibo adicionar(@RequestBody @Valid Recibo recibo) {		
		return servicoRecibo.adicionar(recibo);
	}
	
	@PutMapping("/{id}")
	public Recibo atualizar(@PathVariable(value = "id")Long id, @RequestBody Recibo recibo){
		return servicoRecibo.atualizar(recibo, id);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Recibo recibo, Long id) {
		servicoRecibo.deletar(recibo, id);
	}
		
}
