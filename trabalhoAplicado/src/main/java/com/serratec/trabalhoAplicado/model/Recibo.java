package com.serratec.trabalhoAplicado.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.serratec.trabalhoAplicado.model.enuns.FormaPagamento;

@Entity
@Table(name = "recibo")
@SequenceGenerator(name = "generator_recibo", sequenceName = "sequence_recibo", initialValue = 1, allocationSize = 1)
public class Recibo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_recibo")
	private Long id;
	
	@Column(length = 255)
	private String cabecario;
	
	@Column(length = 255)
	private String rodape;
	
	@ManyToOne()
	@JoinColumn(name = "medico_id")
	private Medico medico;
	
	@ManyToOne()
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	@ManyToMany
    @JoinTable(name = "procedimentos_recibo", 
    joinColumns = @JoinColumn(name = "recibo_id"),
    inverseJoinColumns = @JoinColumn(name = "procedimento_id"))
	private List<Procedimentos> procedimento = new ArrayList<Procedimentos>();
	
	private Date data;


	private FormaPagamento formaPagamento;
 
	public Recibo(Long id, String cabecario, String rodape, Medico medico, Paciente paciente,
			List<Procedimentos> procedimento, Date data, FormaPagamento formaPagamento) {
		
		this.id = id;
		this.cabecario = cabecario;
		this.rodape = rodape;
		this.medico = medico;
		this.paciente = paciente;
		this.procedimento = procedimento;
		this.data = data;
		this.formaPagamento = formaPagamento;
	}


	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCabecario() {
		return cabecario;
	}

	public void setCabecario(String cabecario) {
		this.cabecario = cabecario;
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

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	

	
	
}
