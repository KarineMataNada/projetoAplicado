package com.serratec.trabalhoAplicado.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.serratec.trabalhoAplicado.dto.LoginResponse;
import com.serratec.trabalhoAplicado.exception.ResourceBadRequestException;
import com.serratec.trabalhoAplicado.exception.ResourceNotFoundException;
import com.serratec.trabalhoAplicado.model.Endereco;
import com.serratec.trabalhoAplicado.model.Usuario;
import com.serratec.trabalhoAplicado.repository.EnderecoRepository;
import com.serratec.trabalhoAplicado.repository.UsuarioRepository;
import com.serratec.trabalhoAplicado.security.JWTService;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

@Service
public class UsuarioService {
	
	private static final String headerPrefix = "Bearer ";

	@Autowired
	private UsuarioRepository repositorioUsuario;
	
	@Autowired
	private EnderecoRepository repositorioEndereco;
	
	@Autowired
	private JWTService jwtService;
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEnconder;
	
    @Autowired
	private CepService serviceCep;
	

	public List<Usuario> obterTodos() {
		return repositorioUsuario.findAll();
	}
	
	
	public Optional<Usuario> obterPorId(Long id) {
		 Optional<Usuario> usuario = repositorioUsuario.findById(id);	 
		
		 if(usuario.isEmpty()) {
			throw new ResourceNotFoundException("Usuario não encontrado!");
		}
		 
		 return usuario;
	}
	
	

	public List<Usuario> obterPorNome(String nome) {
		List<Usuario> usuario = repositorioUsuario.findAllByNomeContainingIgnoreCase(nome);	
		
		if(usuario.isEmpty()) {
			throw new ResourceNotFoundException("Usuario não encontrado!");
		}
		
		return usuario;
	}
	
	public Usuario adicionar(Usuario usuario) {
		usuario.setId(null);
		validarCPF(usuario.getCpf());
		
		if(repositorioUsuario.findByUsername(usuario.getUsername()).isPresent()) {
			
	    }
		Endereco endereco = serviceCep.obterEnderecoPorCep(usuario.getEndereco().getCep());
		endereco.setComplemento(usuario.getEndereco().getComplemento());
		endereco.setNumero(usuario.getEndereco().getNumero());
		
		if(endereco.getBairro() == "") {
			endereco.setBairro(usuario.getEndereco().getBairro());
        }
		
		if(endereco.getLogradouro() == "") {
			endereco.setLogradouro(usuario.getEndereco().getLogradouro());
		}
		usuario.setEndereco(endereco);
		
		
		String senha = passwordEnconder.encode(usuario.getSenha());
		usuario.setSenha(senha);
		
		this.repositorioEndereco.save(endereco);
		
		
		return repositorioUsuario.save(usuario);
		
	}
	
	 public Usuario atualizar(Usuario usuario, Long id) {
		 Optional<Usuario> usuarioAtualizado = repositorioUsuario.findById(id);
		 
			if(usuarioAtualizado.isEmpty()) {
				throw new ResourceNotFoundException("Usuario não encontrado por id");
			}
			validarCPF(usuario.getCpf());
			
			Endereco endereco = serviceCep.obterEnderecoPorCep(usuario.getEndereco().getCep());
			endereco.setComplemento(usuario.getEndereco().getComplemento());
			endereco.setNumero(usuario.getEndereco().getNumero());
			
			if(endereco.getBairro() == "") {
				endereco.setBairro(usuario.getEndereco().getBairro());
	        }
			
			if(endereco.getLogradouro() == "") {
				endereco.setLogradouro(usuario.getEndereco().getLogradouro());
			}
			usuario.setEndereco(endereco);

			String senha = passwordEnconder.encode(usuario.getSenha());
			usuario.setSenha(senha);
			
			usuario.setId(id);		
			return repositorioUsuario.save(usuario);
			
		}


	public void deletar(Long id) {
	    Optional<Usuario> deletarUsuario = repositorioUsuario.findById(id);

	    if(deletarUsuario.isEmpty()) {
			throw new ResourceNotFoundException("Usuario não encontrado por id");
		}

		repositorioUsuario.deleteById(id);	 
	}
	
	public void validarCPF(String cpf) {
		try {
			CPFValidator cpfValidado = new CPFValidator();
			cpfValidado.assertValid(cpf);

		} catch (InvalidStateException e) {

			throw new ResourceBadRequestException("CPF invalido!");

		}
	}
	
	public LoginResponse logar(String username, String senha) {
		
		Authentication autenticacao = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, senha, Collections.emptyList()));
		
		SecurityContextHolder.getContext().setAuthentication(autenticacao);
		
		String token = headerPrefix + jwtService.gerarToken(autenticacao);
		
		var usuario = repositorioUsuario.findByUsername(username);
		
		return new LoginResponse(token, usuario.get());
	}
	
}




