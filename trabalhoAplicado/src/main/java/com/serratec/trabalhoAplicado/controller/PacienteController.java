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

import com.serratec.trabalhoAplicado.model.Paciente;
import com.serratec.trabalhoAplicado.service.PacienteService;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	
	@Autowired
	private PacienteService servicoPaciente;
		
	
	@GetMapping
	public List<Paciente> obterTodos() {
		return servicoPaciente.obterTodos();
	}
	
	@GetMapping("/{id}")
	public Optional<Paciente> obterPorId(@PathVariable("id")Long id){
		return servicoPaciente.obterPorId(id);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Paciente> obterPorNome(@PathVariable("nome")String nome){
		return servicoPaciente.obterPorNome(nome);
	}
	
	@PostMapping
	public Paciente adicionar(@RequestBody @Valid Paciente paciente) {		
		return servicoPaciente.adicionar(paciente);
	}
	
	@PutMapping("/{id}")
	public Paciente atualizar(@PathVariable(value = "id")Long id, @RequestBody Paciente paciente){
		return servicoPaciente.atualizar(paciente, id);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoPaciente.deletar(id);
	}
		
}
