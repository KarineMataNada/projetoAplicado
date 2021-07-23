package com.serratec.trabalhoAplicado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "paciente")
@SequenceGenerator(name = "generator_paciente", sequenceName = "sequence_paciente", initialValue = 1, allocationSize = 1)
public class Paciente {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_paciente")
	private Long id;
	
	@Column(length = 255, unique = true)
	@NotNull
	private String email; 
	
	@Column(length = 60, unique = true)
	@NotNull
	private String username;
	
	@Column(length = 60)
	@NotNull
	private String nome;
	
	@Column(length = 11, unique = true)
	@NotNull
	private String cpf;
	
	@Column(length = 60)
	@NotNull
	private String telefone;
	


	public Paciente() {}
	
	
	public Paciente(Long id, String email, String username, String nome, String cpf, String telefone) {
	
		this.id = id;
		this.email = email;
		this.username = username;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		
	}




	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
