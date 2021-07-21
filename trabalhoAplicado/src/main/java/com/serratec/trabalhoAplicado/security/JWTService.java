package com.serratec.trabalhoAplicado.security;


import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.serratec.trabalhoAplicado.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTService {

	//Chave secreta utilizada pelo JWT para codificar e decodificar o token.
	private static final String chavePrivadaJWT = "serratec"; 
	
	//Metodo que sabe gerar o token com base na autenticação.
	public String gerarToken(Authentication authentication) {
		
		//1 Dia em milliseconds.
		//Aqui pode variar de acordo com sua regra de negocio.
		int tempoExpiracao = 86400000;
		
		//Aqui to criando uma data de expiração com base no tempo de expiração.
		//Ele pega a data atual e soma mais 1 dia em milliseconds.
		Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao);
		
		//Pega o usuario atual da atenticação.
		Usuario usuario =  (Usuario) authentication.getPrincipal();
		
		//Aqui ele pega todos os dados e retorna um token bonito JWT.
		return Jwts.builder()
				.setSubject(usuario.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS512, chavePrivadaJWT)
				.compact();
	}
	
	//Retorna o id do usuario dono do token.
	public Optional<Long> obterIdDoUsuario(String token){
		try {
			//Retorna as permissões do token.
			Claims claims = parse(token).getBody();
			
			//Retorna o id de dentro do token se encontrar ou null.
			return Optional.ofNullable(Long.parseLong(claims.getSubject()));
						
		} catch (Exception e) {
			//Se não encontrar nada devolve um Optional null
			return Optional.empty();
		}
	}
	
	//Metodo que sabe descobri de dentro do token com base na chavePrivada
	//qual as permissões do usuario.
	private Jws<Claims> parse(String token){
		return Jwts.parser().setSigningKey(chavePrivadaJWT).parseClaimsJws(token);
	}
	
	
	
}
