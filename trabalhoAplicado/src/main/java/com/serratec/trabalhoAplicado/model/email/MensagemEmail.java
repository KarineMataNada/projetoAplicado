package com.serratec.trabalhoAplicado.model.email;

import java.util.List;

public class MensagemEmail {
	
	private String assunto;
	private String corpo;
	private List<String> destinatarios;
		
	public MensagemEmail() {};
	
	public MensagemEmail(String assunto, String corpo, List<String> destinatarios) {
		this.assunto = assunto;
		this.corpo = corpo;
		this.destinatarios = destinatarios;
	}

	public String getAssunto() {
		return assunto;
	}
	
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	public String getCorpo() {
		return corpo;
	}
	
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	public List<String> getDestinatarios() {
		return destinatarios;
	}
	
	public void setDestinatarios(List<String> destinatarios) {
		this.destinatarios = destinatarios;
	}
	
	
	
}