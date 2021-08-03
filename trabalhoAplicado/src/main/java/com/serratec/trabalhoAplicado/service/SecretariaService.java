package com.serratec.trabalhoAplicado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.serratec.trabalhoAplicado.exception.ResourceNotFoundException;
import com.serratec.trabalhoAplicado.model.Endereco;
import com.serratec.trabalhoAplicado.model.Secretaria;
import com.serratec.trabalhoAplicado.repository.EnderecoRepository;
import com.serratec.trabalhoAplicado.repository.SecretariaRepository;


@Service
public class SecretariaService {
	

	@Autowired
	private SecretariaRepository repositorioSecretaria;
	
	@Autowired
	private PasswordEncoder passwordEnconder;
	
    @Autowired
	private CepService serviceCep;
	
	@Autowired
	private EnderecoRepository repositorioEndereco;
	

	public List<Secretaria> obterTodos() {
		return repositorioSecretaria.findAll();
	}
	
	
	public Optional<Secretaria> obterPorId(Long id) {
		 Optional<Secretaria> secretaria = repositorioSecretaria.findById(id);	 
		
		 if(secretaria.isEmpty()) {
			throw new ResourceNotFoundException("Secretario(a) não encontrado por id");
		}
		 
		 return secretaria;
	}
	
	

	public List<Secretaria> obterPorNome(String nome) {
		List<Secretaria> secretaria = repositorioSecretaria.findAllByNomeContainingIgnoreCase(nome);	
		
		if(secretaria.isEmpty()) {
			throw new ResourceNotFoundException("Secretario(a) não encontrado!");
		}
		return secretaria;
	}
	
	public Secretaria adicionar(Secretaria secretaria) {
		secretaria.setId(null);
		
		Endereco endereco = serviceCep.obterEnderecoPorCep(secretaria.getEndereco().getCep());
		endereco.setComplemento(secretaria.getEndereco().getComplemento());
		endereco.setNumero(secretaria.getEndereco().getNumero());
		
		if(endereco.getBairro() == "") {
			endereco.setBairro(secretaria.getEndereco().getBairro());
        }
		
		if(endereco.getLogradouro() == "") {
			endereco.setLogradouro(secretaria.getEndereco().getLogradouro());
		}
		secretaria.setEndereco(endereco);
		
		
		String senha = passwordEnconder.encode(secretaria.getSenha());
		secretaria.setSenha(senha);
		
		this.repositorioEndereco.save(endereco);
		
		
		return repositorioSecretaria.save(secretaria);
		
	}
	
	 public Secretaria atualizar(Secretaria secretaria, Long id) {
		 Optional<Secretaria> secretariaAtualizado = repositorioSecretaria.findById(id);
		 
			if(secretariaAtualizado.isEmpty()) {
				throw new ResourceNotFoundException("Secretario(a) não encontrado por id");
			}
			
			Endereco endereco = serviceCep.obterEnderecoPorCep(secretaria.getEndereco().getCep());
			endereco.setComplemento(secretaria.getEndereco().getComplemento());
			endereco.setNumero(secretaria.getEndereco().getNumero());
			
			if(endereco.getBairro() == "") {
				endereco.setBairro(secretaria.getEndereco().getBairro());
	        }
			
			if(endereco.getLogradouro() == "") {
				endereco.setLogradouro(secretaria.getEndereco().getLogradouro());
			}
			secretaria.setEndereco(endereco);
			
		 secretaria.setId(id);		
		return repositorioSecretaria.save(secretaria);
		
	}


	public void deletar(Long id) {
	    Optional<Secretaria> deletarSecretaria = repositorioSecretaria.findById(id);

	    if(deletarSecretaria.isEmpty()) {
			throw new ResourceNotFoundException("Secretario(a) não encontrado por id");
		}

		repositorioSecretaria.deleteById(id);	 
}
	

	
}

