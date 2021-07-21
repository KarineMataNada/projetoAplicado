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

import com.serratec.trabalhoAplicado.repository.UsuarioRepository;
import com.serratec.trabalhoAplicado.security.JWTService;
import com.serratec.trabalhoAplicado.dto.LoginResponseMedico;
import com.serratec.trabalhoAplicado.model.Medico;
import com.serratec.trabalhoAplicado.repository.MedicoRepository;


@Service
public class MedicoService {

	private static final String headerPrefix = "Coffee ";

	@Autowired
	private MedicoRepository repositorioMedico;
	
	@Autowired
	private JWTService jwtService;
	
    @Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEnconder;
	

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
		
		String senha = passwordEnconder.encode(medico.getSenha());
		medico.setSenha(senha);
		
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
	
	public LoginResponseMedico logar(String username, String senha) {
		
		Authentication autenticacao = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, senha, Collections.emptyList()));
		
		SecurityContextHolder.getContext().setAuthentication(autenticacao);
		
		String token = headerPrefix + jwtService.gerarToken(autenticacao);
		
		var medico = repositorioMedico.findByUsername(username);
		
		return new LoginResponseMedico(token, medico.get());
	}
}
