package com.serratec.trabalhoAplicado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.trabalhoAplicado.model.Paciente;
import com.serratec.trabalhoAplicado.repository.PacienteRepository;




@Service
public class PacienteService {
	

	@Autowired
	private PacienteRepository repositorioPaciente;
	

	public List<Paciente> obterTodos() {
		return repositorioPaciente.findAll();
	}
	
	
	public Optional<Paciente> obterPorId(Long id) {
		 Optional<Paciente> paciente = repositorioPaciente.findById(id);	 
		
		 return paciente;
	}
	
	

	public List<Paciente> obterPorNome(String nome) {
		List<Paciente> paciente = repositorioPaciente.findByNomeContaining(nome);	
		
		return paciente;
	}
	
	public Paciente adicionar(Paciente paciente) {
		paciente.setId(null);
		
		return repositorioPaciente.save(paciente);
		
	}
	
	 public Paciente atualizar(Paciente paciente, Long id) {
		 Optional<Paciente> pacienteAtualizado = repositorioPaciente.findById(id);
		 
		paciente.setId(id);		
		return repositorioPaciente.save(paciente);
		
	}


	public void deletar(Long id) {
	    Optional<Paciente> deletarPaciente = repositorioPaciente.findById(id);
   
		repositorioPaciente.deleteById(id);	 
}
	

	
}


