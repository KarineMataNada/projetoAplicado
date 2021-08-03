package com.serratec.trabalhoAplicado.model;



import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.serratec.trabalhoAplicado.model.enuns.Acoes;

@Entity
@Table(name = "reciboHistorico")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "generator_historicoRecibo", sequenceName = "sequence_historicoRecibo", initialValue = 1, allocationSize = 1)
public class HistoricoRecibo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_historicoRecibo")
	private Long id;
	
	private Acoes acoes;
	
	private LocalDate data;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "recibo_id")
	private Recibo recibo;
	
	public HistoricoRecibo() {};

	public HistoricoRecibo(Acoes acoes, LocalDate data, Recibo recibo) {
		
		this.acoes = acoes;
		this.data = data;
		this.recibo = recibo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Recibo getRecibo() {
		return recibo;
	}

	public void setRecibo(Recibo recibo) {
		this.recibo = recibo;
	}

	public Acoes getAcoes() {
		return acoes;
	}

	public void setAcoes(Acoes acoes) {
		this.acoes = acoes;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
}
