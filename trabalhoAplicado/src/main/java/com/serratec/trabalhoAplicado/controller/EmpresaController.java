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

import com.serratec.trabalhoAplicado.model.Empresa;
import com.serratec.trabalhoAplicado.service.EmpresaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

	
	@Autowired
	private EmpresaService servicoEmpresa;
		
	
	@GetMapping
	public List<Empresa> obterTodos() {
		return servicoEmpresa.obterTodos();
	}
	
	@GetMapping("/{id}")
	public Optional<Empresa> obterPorId(@PathVariable("id")Long id){
		return servicoEmpresa.obterPorId(id);
	}
	
	@PostMapping
	public Empresa adicionar(@RequestBody @Valid Empresa empresa) {		
		return servicoEmpresa.adicionar(empresa);
	}
	
	@PutMapping("/{id}")
	public Empresa atualizar(@PathVariable(value = "id")Long id, @RequestBody Empresa empresa){
		return servicoEmpresa.atualizar(empresa, id);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable(value = "id")Long id) {
		servicoEmpresa.deletar(id);
	}
		
}

