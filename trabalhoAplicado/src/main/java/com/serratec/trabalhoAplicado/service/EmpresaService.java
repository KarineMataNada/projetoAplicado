package com.serratec.trabalhoAplicado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.trabalhoAplicado.exception.ResourceNotFoundException;
import com.serratec.trabalhoAplicado.model.Empresa;
import com.serratec.trabalhoAplicado.model.Endereco;
import com.serratec.trabalhoAplicado.repository.EmpresaRepository;
import com.serratec.trabalhoAplicado.repository.EnderecoRepository;

@Service
public class EmpresaService {
	

	@Autowired
	private EmpresaRepository repositorioEmpresa;
	
	
    @Autowired
	private CepService serviceCep;
	
	@Autowired
	private EnderecoRepository repositorioEndereco;

	public List<Empresa> obterTodos() {
		return repositorioEmpresa.findAll();
	}
	
	
	public Optional<Empresa> obterPorId(Long id) {
		 Optional<Empresa> empresa = repositorioEmpresa.findById(id);	 
		
		 if(empresa.isEmpty()) {
			throw new ResourceNotFoundException("Empresa não encontrado por id!");
		}
		 
		 return empresa;
	}
	
	
	
	public Empresa adicionar(Empresa empresa) {
		empresa.setId(null);
		

		Endereco endereco = serviceCep.obterEnderecoPorCep(empresa.getEndereco().getCep());
		endereco.setComplemento(empresa.getEndereco().getComplemento());
		endereco.setNumero(empresa.getEndereco().getNumero());
		empresa.setEndereco(endereco);
		
		
		this.repositorioEndereco.save(endereco);
		
		
		return repositorioEmpresa.save(empresa);
		
	}
	
	 public Empresa atualizar(Empresa empresa, Long id) {
		 Optional<Empresa> empresaAtualizado = repositorioEmpresa.findById(id);
		 
			if(empresaAtualizado.isEmpty()) {
				throw new ResourceNotFoundException("Empresa não encontrado!");
			}
	    empresa.setId(id);		
		return repositorioEmpresa.save(empresa);
		
	}


	public void deletar(Long id) {
	    Optional<Empresa> deletarEmpresa = repositorioEmpresa.findById(id);
   
	    if(deletarEmpresa.isEmpty()) {
			throw new ResourceNotFoundException("Empresa não encontrado!");
		}
		repositorioEmpresa.deleteById(id);	 
}
	
	
}

