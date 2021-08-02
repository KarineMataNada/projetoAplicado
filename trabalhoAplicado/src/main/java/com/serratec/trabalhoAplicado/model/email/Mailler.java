package com.serratec.trabalhoAplicado.model.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Mailler {

	@Autowired
	private JavaMailSender javaMailSender;
	

	public void enviar(MenssagemEmail mensagem) {
		try {
			MimeMessage email = javaMailSender.createMimeMessage();	
			MimeMessageHelper helper = new MimeMessageHelper(email, true);
			    
			helper.setSubject(mensagem.getAssunto());
		    
		    helper.setText(mensagem.getCorpo(), true);
		         		
		    helper.setTo(mensagem.getDestinatarios()
						.toArray(new String[mensagem.getDestinatarios().size()]));
				
			javaMailSender.send(email);
				
		} catch (Exception e) {
			
		}
   
	}
	
	
}
