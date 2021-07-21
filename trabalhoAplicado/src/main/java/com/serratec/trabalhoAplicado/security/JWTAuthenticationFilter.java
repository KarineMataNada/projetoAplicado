package com.serratec.trabalhoAplicado.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtSevice;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String token = pegarToken(request);
		

		var id = jwtSevice.obterIdDoUsuario(token);
		
		
		if(id.isPresent()) {

			
			UserDetails usuario = customUserDetailsService.pegarUsuarioPorId(id.get());
			
			
			UsernamePasswordAuthenticationToken autenticacao = 
					new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
			
			
			autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	
			
			SecurityContextHolder.getContext().setAuthentication(autenticacao);
		}
		
		
		filterChain.doFilter(request, response);		
	}
	
	
	private String pegarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		
		if(StringUtils.hasText(token)) {
		
			return token.substring(7);
		}
		
		return null;
	}

}

