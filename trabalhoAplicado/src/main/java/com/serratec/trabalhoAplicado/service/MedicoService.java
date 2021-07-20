package com.serratec.trabalhoAplicado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.trabalhoAplicado.model.Medico;
import com.serratec.trabalhoAplicado.repository.MedicoRepository;



@Service
public class MedicoService {


	@Autowired
	private MedicoRepository repositorioMedico;
	

	public List<Medico> obterTodos() {
		return repositorioMedico.findAll();
	}
	
	
	public Optional<Medico> obterPorId(Long id) {
		 Optional<Medico> medico = repositorioMedico.findById(id);	 
		
		 return medico;
	}
	
	

	public List<Medico> obterPorNome(String nome) {
		List<Medico> medico = repositorioMedico.findByNomeContaining(nome);	
		
		return medico;
	}
	
	public Medico adicionar(Medico medico) {
		medico.setId(null);
		
		return repositorioMedico.save(medico);
		
	}
	
	 public Medico atualizar(Medico medico, Long id) {
		 Optional<Medico> medicoAtualizado = repositorioMedico.findById(id);
		 
		 medico.setId(id);		
		return repositorioMedico.save(medico);
		
	}


	public void deletar(Long id) {
	    Optional<Medico> deletarMedico = repositorioMedico.findById(id);

	    
		repositorioMedico.deleteById(id);	 
}
}
