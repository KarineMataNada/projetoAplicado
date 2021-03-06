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

import com.serratec.trabalhoAplicado.model.Medico;
import com.serratec.trabalhoAplicado.service.MedicoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

	
	@Autowired
	private MedicoService servicoMedico;
		
	
	@GetMapping
	public List<Medico> obterTodos() {
		return servicoMedico.obterTodos();
	}
	
	@GetMapping("/{id}")
	public Optional<Medico> obterPorId(@PathVariable("id")Long id){
		return servicoMedico.obterPorId(id);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Medico> obterPorNome(@PathVariable("nome")String nome){
		return servicoMedico.obterPorNome(nome);
	}
	
	@PostMapping
	public Medico adicionar(@RequestBody @Valid Medico medico) {		
		return servicoMedico.adicionar(medico);
	}
	
	@PutMapping("/{id}")
	public Medico atualizar(@PathVariable(value = "id")Long id, @RequestBody  Medico medico){
		return servicoMedico.atualizar(medico, id);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoMedico.deletar(id);
	}
}