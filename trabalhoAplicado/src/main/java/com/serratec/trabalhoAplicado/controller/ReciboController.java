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

import com.serratec.trabalhoAplicado.dto.ReciboDto;
import com.serratec.trabalhoAplicado.model.Recibo;
import com.serratec.trabalhoAplicado.service.ReciboService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/recibos")
public class ReciboController {

	
	@Autowired
	private ReciboService servicoRecibo;
		
	
	@GetMapping
	public List<ReciboDto> obterTodos() {
		return servicoRecibo.obterTodos();
	}
	
	@GetMapping("/finalizados")
	public List<ReciboDto> obterTodosFinalizados() {
		return servicoRecibo.obterTodosFinalizados(null);
	}
	
	@GetMapping("/{id}")
	public ReciboDto obterPorId(@PathVariable("id")Long id){
		return servicoRecibo.obterPorId(id);
	}
	
	@GetMapping("info/{id}")
	public Optional<Recibo> obterPorIdInfo(@PathVariable("id")Long id){
		return servicoRecibo.obterPorIdInfo(id);
	}
	
	
	@PostMapping
	public Recibo adicionar(@RequestBody @Valid Recibo recibo) {		
		return servicoRecibo.adicionar(recibo);
	}
	
	@PutMapping("info/{id}")
	public Recibo atualizar(@PathVariable(value = "id")Long id, @RequestBody Recibo recibo){
		return servicoRecibo.atualizar(recibo, id);
	}
	
	@DeleteMapping("info/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoRecibo.deletar(id);
	}
		
}
