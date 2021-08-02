package com.serratec.trabalhoAplicado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.trabalhoAplicado.exception.ResourceNotFoundException;
import com.serratec.trabalhoAplicado.model.Perfil;
import com.serratec.trabalhoAplicado.repository.PerfilRepository;


@Service
public class PerfilService {
	

	@Autowired
	private PerfilRepository repositorioPerfil;
	

	public List<Perfil> obterTodos() {
		return repositorioPerfil.findAll();
	}
	
	
	public Optional<Perfil> obterPorId(Long id) {
		 Optional<Perfil> perfil = repositorioPerfil.findById(id);	 
		
		 if(perfil.isEmpty()) {
			throw new ResourceNotFoundException("Perfil não encontrado por id!");
		}
		 return perfil;
	}
	
	

	public List<Perfil> obterPorNome(String nome) {
		List<Perfil> perfil = repositorioPerfil.findAllByNomeContainingIgnoreCase(nome);	
		
		if(perfil.isEmpty()) {
			throw new ResourceNotFoundException("Perfil não encontrado !");
		}
		return perfil;
	}
	
	public Perfil adicionar(Perfil perfil) {
		perfil.setId(null);
		
		return repositorioPerfil.save(perfil);
		
	}
	
	 public Perfil atualizar(Perfil perfil, Long id) {
		 Optional<Perfil> perfilAtualizado = repositorioPerfil.findById(id);
		 
			if(perfilAtualizado.isEmpty()) {
				throw new ResourceNotFoundException("Perfil não encontrado!");
			}
	    perfil.setId(id);		
		return repositorioPerfil.save(perfil);
		
	}


	public void deletar(Long id) {
	    Optional<Perfil> deletarPerfil = repositorioPerfil.findById(id);
   
	    if(deletarPerfil.isEmpty()) {
			throw new ResourceNotFoundException("Perfil não encontrado!");
		}
		repositorioPerfil.deleteById(id);	 
}
	
	
}