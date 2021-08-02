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
@Table(name = "endereco")
@SequenceGenerator(name = "generator_endereco", sequenceName = "sequence_endereco", initialValue = 1, allocationSize = 1)
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_endereco")
	private Long id;
	
	@Column(length = 10)
	@NotNull
	private String cep;
	
	@Column(length = 60)
	private String logradouro;
	
	@Column(length = 60)
	private String bairro;
	
	@Column(length = 60)
	private String localidade;
	
	@Column(length = 10)
	private Integer numero;
	
	@Column(length = 255)
	private String complemento;
	
	@Column(length = 60)

	private String uf;
	
	
	public Endereco() {}
	
	

	public Endereco(Long id, @NotNull String cep, String logradouro, String bairro, String localidade, Integer numero,
			String complemento, String uf) {
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.numero = numero;
		this.complemento = complemento;
		this.uf = uf;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	

	
	
}
