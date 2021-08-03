package com.serratec.trabalhoAplicado.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.serratec.trabalhoAplicado.model.HistoricoRecibo;
import com.serratec.trabalhoAplicado.model.Procedimentos;
import com.serratec.trabalhoAplicado.model.enuns.FormaPagamento;

public class ReciboDto {
	

	private String cabecario;
	
	private Long id;
	
	private String nomePaciente;
	
	private String cpfPaciente;
	
	private String nomeMedico;
	
	private String crmMedico;
	
	private String nomeSecretaria;
	
	private List<Procedimentos> procedimento;
	
	private FormaPagamento formaPagamento;
	
	private LocalDate data;
	
	private String rodape;
	
	private List<HistoricoRecibo> historicoRecibo = new ArrayList<>();
	
	private Boolean statusFinalizado;
	

	public String getCabecario() {
		return cabecario;
	}

	public void setCabecario(String cabecario) {
		this.cabecario = cabecario;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getCpfPaciente() {
		return cpfPaciente;
	}

	public void setCpfPaciente(String cpfPaciente) {
		this.cpfPaciente = cpfPaciente;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public String getCrmMedico() {
		return crmMedico;
	}

	public void setCrmMedico(String crmMedico) {
		this.crmMedico = crmMedico;
	}

	public String getNomeSecretaria() {
		return nomeSecretaria;
	}

	public void setNomeSecretaria(String nomeSecretaria) {
		this.nomeSecretaria = nomeSecretaria;
	}


	public String getRodape() {
		return rodape;
	}

	public void setRodape(String rodape) {
		this.rodape = rodape;
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

	public List<HistoricoRecibo> getHistoricoRecibo() {
		return historicoRecibo;
	}

	public void setHistoricoRecibo(List<HistoricoRecibo> historicoRecibo) {
		this.historicoRecibo = historicoRecibo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getStatusFinalizado() {
		return statusFinalizado;
	}

	public void setStatusFinalizado(Boolean statusFinalizado) {
		this.statusFinalizado = statusFinalizado;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
}