package com.serratec.trabalhoAplicado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name = "layout")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "generator_layout", sequenceName = "sequence_layout", initialValue = 1, allocationSize = 1)
public class Layout {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_layout")
	private Long id;

	@NotNull
	private String nome;

	@NotNull
	private Boolean secretaria;
	
	@NotNull
	private Boolean data;
	
	@NotNull
	private Boolean formaPagamento;
	
	@NotNull
	private Boolean ativo; 
	
	
	public Layout() {}


	public Layout(Long id, @NotNull String nome, @NotNull Boolean secretaria, @NotNull Boolean data,
			@NotNull Boolean formaPagamento, @NotNull Boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.secretaria = secretaria;
		this.data = data;
		this.formaPagamento = formaPagamento;
		this.ativo = ativo;
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

	public Boolean getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Boolean secretaria) {
		this.secretaria = secretaria;
	}

	public Boolean getData() {
		return data;
	}

	public void setData(Boolean data) {
		this.data = data;
	}

	public Boolean getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(Boolean formaPagamento) {
		this.formaPagamento = formaPagamento;
	}


	public Boolean getAtivo() {
		return ativo;
	}


	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}