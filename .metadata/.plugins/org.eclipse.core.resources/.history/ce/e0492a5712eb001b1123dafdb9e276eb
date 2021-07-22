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
@Table(name = "procedimento")
@SequenceGenerator(name = "generator_procedimento", sequenceName = "sequence_procedimento", initialValue = 1, allocationSize = 1)
public class Procedimentos {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_procedimento")
	private Long id;

	@Column(length = 60)
	@NotNull
	private String nomeProcedimento;
	
	@Column(length = 255)
	@NotNull
	private String descricao;
	
	@Column(length = 60)
	@NotNull
	private Double valor;
	
	
	public Procedimentos() {}
	
	
	public Procedimentos(Long id, String nomeProcedimento, String descricao, Double valor) {
		
		this.id = id;
		this.nomeProcedimento = nomeProcedimento;
		this.descricao = descricao;
		this.valor = valor;
	}
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNomeProcedimento() {
		return nomeProcedimento;
	}
	
	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
