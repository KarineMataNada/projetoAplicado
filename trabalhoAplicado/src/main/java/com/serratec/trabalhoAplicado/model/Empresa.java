package com.serratec.trabalhoAplicado.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "empresa")
@SequenceGenerator(name = "generator_empresa", sequenceName = "sequence_empresa", initialValue = 1, allocationSize = 1)
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_empresa")
	private Long id;
	
	@NotNull
	private String nomeFantasia;
	
	@NotNull
	private String razaoSocial;
	
	@NotNull
	private String email;
	
	@NotNull
	private String cnpj;
	
	@NotNull
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "endereco_empresa_id")
	private Endereco endereco;
	
	
	private String logo;
	
	private String telefone;


	public Empresa() {}


	public Empresa(Long id, @NotNull String nomeFantasia, @NotNull String razaoSocial, @NotNull String email,
			@NotNull String cnpj, @NotNull Endereco endereco, String logo, String telefone) {
		super();
		this.id = id;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.email = email;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.logo = logo;
		this.telefone = telefone;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomeFantasia() {
		return nomeFantasia;
	}


	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}


	public String getRazaoSocial() {
		return razaoSocial;
	}


	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
	
}
