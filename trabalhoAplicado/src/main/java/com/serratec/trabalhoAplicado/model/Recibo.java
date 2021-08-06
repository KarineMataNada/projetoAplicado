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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.serratec.trabalhoAplicado.model.enuns.FormaPagamento;

@Entity
@Table(name = "recibo")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "generator_recibo", sequenceName = "sequence_recibo", initialValue = 1, allocationSize = 1)
public class Recibo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_recibo")
	private Long id;
	
	@OneToOne
    @JoinColumn(name = "layout_id")
	private Layout layout;
	
	@Column(length = 255)
	private String cabecalho;
	
	@Column(length = 255)
	private String rodape;
	
	
	@ManyToOne
	@JoinColumn(name = "medico_id")
	@NotNull
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	@NotNull
	private Paciente paciente;
	
	@ManyToMany
    @JoinTable(name = "procedimentos_recibo", 
    joinColumns = @JoinColumn(name = "recibo_id"),
    inverseJoinColumns = @JoinColumn(name = "procedimento_id"))
	@NotNull
	@Autowired
	private List<Procedimentos> procedimento;
	
	@NotNull
	private Boolean statusFinalizado;
	
	
	private LocalDate data;

	private FormaPagamento formaPagamento;
	
 
	@OneToOne
	@JoinColumn(name = "secretaria_id")
	private Secretaria secretaria;
	
	
	@OneToMany(mappedBy= "recibo", cascade=CascadeType.ALL)
	private List<HistoricoRecibo> historicoRecibo;

	
	public Recibo() {};
	
	public Recibo(Long id, Layout layout, String cabecalho, String rodape, @NotNull Medico medico,
			@NotNull Paciente paciente, @NotNull List<Procedimentos> procedimento, boolean statusFinalizado,
			LocalDate data, FormaPagamento formaPagamento, Secretaria secretaria,
			List<HistoricoRecibo> historicoRecibo) {
		
		this.id = id;
		this.layout = layout;
		this.cabecalho = cabecalho;
		this.rodape = rodape;
		this.medico = medico;
		this.paciente = paciente;
		this.procedimento = procedimento;
		this.statusFinalizado = statusFinalizado;
		this.data = data;
		this.formaPagamento = formaPagamento;
		this.secretaria = secretaria;
		this.historicoRecibo = historicoRecibo;
	}


	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public Layout getLayout() {
		return layout;
	}

	
	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	
	public String getCabecalho() {
		return cabecalho;
	}

	
	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	
	public String getRodape() {
		return rodape;
	}

	
	public void setRodape(String rodape) {
		this.rodape = rodape;
	}

	
	public Medico getMedico() {
		return medico;
	}

	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	
	public Paciente getPaciente() {
		return paciente;
	}

	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	
	public List<Procedimentos> getProcedimento() {
		return procedimento;
	}

	
	public void setProcedimento(List<Procedimentos> procedimento) {
		this.procedimento = procedimento;
	}

	
	public boolean isStatusFinalizado() {
		return statusFinalizado;
	}

	
	public void setStatusFinalizado(boolean statusFinalizado) {
		this.statusFinalizado = statusFinalizado;
	}

	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	
	public Secretaria getSecretaria() {
		return secretaria;
	}

	
	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Boolean getStatusFinalizado() {
		return statusFinalizado;
	}

	public void setStatusFinalizado(Boolean statusFinalizado) {
		this.statusFinalizado = statusFinalizado;
	}

	public List<HistoricoRecibo> getHistoricoRecibo() {
		return historicoRecibo;
	}

	public void setHistoricoRecibo(List<HistoricoRecibo> historicoRecibo) {
		this.historicoRecibo = historicoRecibo;
	}




}
		

