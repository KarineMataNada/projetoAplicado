package com.serratec.trabalhoAplicado.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.serratec.trabalhoAplicado.exception.ResourceNotFoundException;
import com.serratec.trabalhoAplicado.model.Endereco;
import com.serratec.trabalhoAplicado.model.Medico;
import com.serratec.trabalhoAplicado.repository.EnderecoRepository;
import com.serratec.trabalhoAplicado.repository.MedicoRepository;


@Service
public class MedicoService {


	@Autowired
	private MedicoRepository repositorioMedico;
	
	@Autowired
	private PasswordEncoder passwordEnconder;
	
	@Autowired
	private EnderecoRepository repositorioEndereco;
	
    @Autowired
	private CepService serviceCep;
	

	public List<Medico> obterTodos() {
		return repositorioMedico.findAll();
	}
	
	
	public Optional<Medico> obterPorId(Long id) {
		 Optional<Medico> medico = repositorioMedico.findById(id);	 
		
		 if(medico.isEmpty()) {
			throw new ResourceNotFoundException("Paciente não encontrado por id!");
		}
		 return medico;
	}
	
	

	public List<Medico> obterPorNome(String nome) {
		List<Medico> medico = repositorioMedico.findAllByNomeContainingIgnoreCase(nome);	
		
		if(medico.isEmpty()) {
			throw new ResourceNotFoundException("Paciente não encontrado!");
		}  
		
		return medico;
	}
	
	public Medico adicionar(Medico medico) {
		medico.setId(null);
		
		Endereco endereco = serviceCep.obterEnderecoPorCep(medico.getEndereco().getCep());
		endereco.setComplemento(medico.getEndereco().getComplemento());
		endereco.setNumero(medico.getEndereco().getNumero());
		
		if(endereco.getBairro() == "") {
			endereco.setBairro(medico.getEndereco().getBairro());
        }
		
		if(endereco.getLogradouro() == "") {
			endereco.setLogradouro(medico.getEndereco().getLogradouro());
		}
		medico.setEndereco(endereco);
		
		
		String senha = passwordEnconder.encode(medico.getSenha());
		medico.setSenha(senha);
		
		this.repositorioEndereco.save(endereco);
		
		
		return repositorioMedico.save(medico);
		
		
	}
	
	 public Medico atualizar(Medico medico, Long id) {
		 Optional<Medico> medicoAtualizado = repositorioMedico.findById(id);
		 
			if(medicoAtualizado.isEmpty()) {
				throw new ResourceNotFoundException("Paciente não encontrado por id");
			}
			
			Endereco endereco = serviceCep.obterEnderecoPorCep(medico.getEndereco().getCep());
			endereco.setComplemento(medico.getEndereco().getComplemento());
			endereco.setNumero(medico.getEndereco().getNumero());
			
			if(endereco.getBairro() == "") {
				endereco.setBairro(medico.getEndereco().getBairro());
	        }
			
			if(endereco.getLogradouro() == "") {
				endereco.setLogradouro(medico.getEndereco().getLogradouro());
			}
			medico.setEndereco(endereco);

			
		 medico.setId(id);		
		return repositorioMedico.save(medico);
		
	}


	public void deletar(Long id) {
	    Optional<Medico> deletarMedico = repositorioMedico.findById(id);

	    if(deletarMedico.isEmpty()) {
			throw new ResourceNotFoundException("Usuario não encontrado por id");
		}

		repositorioMedico.deleteById(id);	 
}
	

}
