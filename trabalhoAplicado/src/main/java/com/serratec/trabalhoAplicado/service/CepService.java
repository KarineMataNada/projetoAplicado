package com.serratec.trabalhoAplicado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.serratec.trabalhoAplicado.model.Endereco;


import reactor.core.publisher.Mono;

@Service
public class CepService {
	    @Autowired
	    private WebClient cepWebClient;


	    public Endereco obterEnderecoPorCep(String cep) {

	        try {

	            Mono<Endereco> monoEndereco = this.cepWebClient
	                .method(HttpMethod.GET) 
	                .uri("/{cep}/json", cep)
	                .retrieve() 
	                .bodyToMono(Endereco.class); 

	            var endereco = monoEndereco.block(); 

	            return endereco;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return new Endereco();
	        }

	    }

}

