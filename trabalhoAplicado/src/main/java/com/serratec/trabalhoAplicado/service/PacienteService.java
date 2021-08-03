package com.serratec.trabalhoAplicado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.trabalhoAplicado.exception.ResourceNotFoundException;
import com.serratec.trabalhoAplicado.model.Endereco;
import com.serratec.trabalhoAplicado.model.Paciente;
import com.serratec.trabalhoAplicado.repository.EnderecoRepository;
import com.serratec.trabalhoAplicado.repository.PacienteRepository;




@Service
public class PacienteService {
	

	@Autowired
	private PacienteRepository repositorioPaciente;
	
    @Autowired
	private CepService serviceCep;

	@Autowired
	private EnderecoRepository repositorioEndereco;		
	

	public List<Paciente> obterTodos() {
		return repositorioPaciente.findAll();
	}
	
	
	public Optional<Paciente> obterPorId(Long id) {
		 Optional<Paciente> paciente = repositorioPaciente.findById(id);	 
		
		 if(paciente.isEmpty()) {
			throw new ResourceNotFoundException("Paciente não encontrado por id!");
		}
		 return paciente;
	}
	
	

	public List<Paciente> obterPorNome(String nome) {
		List<Paciente> paciente = repositorioPaciente.findAllByNomeContainingIgnoreCase(nome);	
		
		if(paciente.isEmpty()) {
			throw new ResourceNotFoundException("Paciente não encontrado!");
		}
		
		return paciente;
	}
	
	
	public Paciente adicionar(Paciente paciente) {
		paciente.setId(null);
		
		
		Endereco endereco = serviceCep.obterEnderecoPorCep(paciente.getEndereco().getCep());
		endereco.setComplemento(paciente.getEndereco().getComplemento());
		endereco.setNumero(paciente.getEndereco().getNumero());
		
		if(endereco.getBairro() == "") {
			endereco.setBairro(paciente.getEndereco().getBairro());
        }
		
		if(endereco.getLogradouro() == "") {
			endereco.setLogradouro(paciente.getEndereco().getLogradouro());
		}
		paciente.setEndereco(endereco);
		

		this.repositorioEndereco.save(endereco);
		
		
		return repositorioPaciente.save(paciente);
		
	}
	
	 public Paciente atualizar(Paciente paciente, Long id) {
		 Optional<Paciente> pacienteAtualizado = repositorioPaciente.findById(id);
		 
			if(pacienteAtualizado.isEmpty()) {
				throw new ResourceNotFoundException("Paciente não encontrado!");
			}
			
			Endereco endereco = serviceCep.obterEnderecoPorCep(paciente.getEndereco().getCep());
			endereco.setComplemento(paciente.getEndereco().getComplemento());
			endereco.setNumero(paciente.getEndereco().getNumero());
			
			if(endereco.getBairro() == "") {
				endereco.setBairro(paciente.getEndereco().getBairro());
	        }
			
			if(endereco.getLogradouro() == "") {
				endereco.setLogradouro(paciente.getEndereco().getLogradouro());
			}
			paciente.setEndereco(endereco);
			
		paciente.setId(id);		
		return repositorioPaciente.save(paciente);
		
	}


	public void deletar(Long id) {
	    Optional<Paciente> deletarPaciente = repositorioPaciente.findById(id);
   
	    if(deletarPaciente.isEmpty()) {
			throw new ResourceNotFoundException("Paciente não encontrado!");
		}
		repositorioPaciente.deleteById(id);	 
}
	

	
}


