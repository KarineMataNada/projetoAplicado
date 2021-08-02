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

import com.serratec.trabalhoAplicado.model.enuns.Acoes;

@Entity
@Table(name = "reciboHistorico")
@SequenceGenerator(name = "generator_historicoRecibo", sequenceName = "sequence_historicoRecibo", initialValue = 1, allocationSize = 1)
public class HistoricoRecibo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_historicoRecibo")
	private Long id;
	
	private Acoes acoes;
	
	private LocalDate data;
	
	@ManyToOne
	@JoinColumn(name = "recibo_id")
	private Recibo recibo;
	
	public HistoricoRecibo() {};

	public HistoricoRecibo(Acoes acoes, LocalDate data) {
		this.acoes = acoes;
		this.data = data;
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
