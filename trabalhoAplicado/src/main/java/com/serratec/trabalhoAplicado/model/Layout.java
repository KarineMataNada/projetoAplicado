package com.serratec.trabalhoAplicado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "recibo")
@SequenceGenerator(name = "generator_recibo", sequenceName = "sequence_recibo", initialValue = 1, allocationSize = 1)
public class Layout {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_recibo")
	private Long id;


	private Boolean secretaria;
	
	private Boolean data;
	
	private Boolean formaPagamento;
	
	private Boolean valor;
	
	
	public Layout(Long id, Boolean secretaria, Boolean data, Boolean formaPagamento, Boolean valor) {
		this.id = id;
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