package com.serratec.trabalhoAplicado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name = "perfil")
@JsonInclude(Include.NON_NULL)
public class Perfil implements GrantedAuthority{

	
	private static final long serialVersionUID = 8740023126299534443L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;

    private String nome;
    
    public Perfil() {};

	public Perfil(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return nome;
	}

    
}