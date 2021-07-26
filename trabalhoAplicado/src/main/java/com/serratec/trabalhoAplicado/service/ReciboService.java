package com.serratec.trabalhoAplicado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.trabalhoAplicado.exception.ResourceNotFoundException;
import com.serratec.trabalhoAplicado.model.Medico;
import com.serratec.trabalhoAplicado.model.Paciente;
import com.serratec.trabalhoAplicado.model.Recibo;
import com.serratec.trabalhoAplicado.model.Usuario;
import com.serratec.trabalhoAplicado.repository.MedicoRepository;
import com.serratec.trabalhoAplicado.repository.PacienteRepository;
import com.serratec.trabalhoAplicado.repository.ReciboRepository;



@Service
public class ReciboService {

	@Autowired
	private ReciboRepository repositorioRecibo;
	
	@Autowired
	private MedicoRepository repositorioMedico;
	
	@Autowired
	private PacienteRepository repositorioPaciente;
	
	
	public List<Recibo> obterTodos() {
		return repositorioRecibo.findAll();
	}
	
	public Optional<Recibo> obterPorId(Long id) {
		 Optional<Recibo> recibo = repositorioRecibo.findById(id);	 
		
		 if(recibo.isEmpty()) {
			throw new ResourceNotFoundException("Layout não encontrado por id!");
		}
		 return recibo;
	}
	
	

	public List<Medico> obterPorNomeMedico(String nome) {
		List<Medico> medico = repositorioMedico.findAllByNomeContainingIgnoreCase(nome);	
		
		if(medico.isEmpty()) {
			throw new ResourceNotFoundException("Medico não encontrado !");
		}
		return medico;
	}
	
	public List<Paciente> obterPorNomePaciente(String nome) {
		List<Paciente> paciente = repositorioPaciente.findAllByNomeContainingIgnoreCase(nome);	
		
		if(paciente.isEmpty()) {
			throw new ResourceNotFoundException("Paciente não encontrado !");
		}
		return paciente;
	}
	
	
	public void deletar(Long id) {
	    Optional<Recibo> deletarRecibo = repositorioRecibo.findById(id);
   
	    if(deletarRecibo.isEmpty()) {
			throw new ResourceNotFoundException("Layout não encontrado!");
		}
		repositorioRecibo.deleteById(id);	 
}
	
	
	
}
