package com.serratec.trabalhoAplicado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.trabalhoAplicado.exception.ResourceNotFoundException;
import com.serratec.trabalhoAplicado.model.Layout;
import com.serratec.trabalhoAplicado.repository.LayoutRepository;



@Service
public class LayoutService {
	

	@Autowired
	private LayoutRepository repositorioLayout;
	

	public List<Layout> obterTodos() {
		return repositorioLayout.findAll();
	}
	
	
	public Optional<Layout> obterPorId(Long id) {
		 Optional<Layout> layout = repositorioLayout.findById(id);	 
		
		 if(layout.isEmpty()) {
			throw new ResourceNotFoundException("Layout não encontrado por id!");
		}
		 return layout;
	}
	
	

	public List<Layout> obterPorNome(String nome) {
		List<Layout> layout = repositorioLayout.findAllByNomeContainingIgnoreCase(nome);	
		
		if(layout.isEmpty()) {
			throw new ResourceNotFoundException("Layout não encontrado !");
		}
		return layout;
	}
	
	public Layout adicionar(Layout layout) {
		layout.setId(null);
		
		return repositorioLayout.save(layout);
		
	}
	
	 public Layout atualizar(Layout layout, Long id) {
		 Optional<Layout> layoutAtualizado = repositorioLayout.findById(id);
		 
			if(layoutAtualizado.isEmpty()) {
				throw new ResourceNotFoundException("Layout não encontrado!");
			}
	    layout.setId(id);		
		return repositorioLayout.save(layout);
		
	}


	public void deletar(Long id) {
	    Optional<Layout> deletarLayout = repositorioLayout.findById(id);
   
	    if(deletarLayout.isEmpty()) {
			throw new ResourceNotFoundException("Layout não encontrado!");
		}
		repositorioLayout.deleteById(id);	 
}
	
	
}
