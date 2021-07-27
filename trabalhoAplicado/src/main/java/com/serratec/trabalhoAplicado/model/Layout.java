package com.serratec.trabalhoAplicado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "layout")
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
	private Boolean valor;
	
	
	public Layout() {}

	public Layout(Long id, String nome, Boolean secretaria, Boolean data, Boolean formaPagamento, Boolean valor) {
		
		this.id = id;
		this.nome = nome;
		this.secretaria = secretaria;
		this.data = data;
		this.formaPagamento = formaPagamento;
		this.valor = valor;
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

	public Boolean getValor() {
		return valor;
	}

	public void setValor(Boolean valor) {
		this.valor = valor;
	}
	
}