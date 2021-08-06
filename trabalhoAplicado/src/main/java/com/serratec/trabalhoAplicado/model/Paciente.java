package com.serratec.trabalhoAplicado.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "paciente")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "generator_paciente", sequenceName = "sequence_paciente", initialValue = 1, allocationSize = 1)
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_paciente")
	private Long id;

	@Column(length = 255, unique = true)
	@NotNull
	private String email;

	@Column(length = 60)
	@NotNull
	private String nome;

	@Column(length = 11, unique = true)
	@NotNull
	private String cpf;

	@Column(length = 60)
	@NotNull
	private String telefone;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_paciente_id")
	private Endereco endereco;

	@JsonIgnore
	@OneToMany(mappedBy = "paciente")
	private List<Recibo> recibo;

	private LocalDate dataNascimento;

	public Paciente() {
	}

	public Paciente(Long id, @NotNull String email, @NotNull String nome, @NotNull String cpf, @NotNull String telefone,
			Endereco endereco, List<Recibo> recibo, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.recibo = recibo;
		this.dataNascimento = dataNascimento;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
