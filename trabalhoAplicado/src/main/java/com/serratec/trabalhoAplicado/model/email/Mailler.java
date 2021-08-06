package com.serratec.trabalhoAplicado.model.email;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.serratec.trabalhoAplicado.config.PDFConfig;
import com.serratec.trabalhoAplicado.exception.ResourceBadRequestException;
import com.serratec.trabalhoAplicado.model.Recibo;

@Component
public class Mailler {

	private static final String emailSucesso = "Email enviado com sucesso!";

	public String enviar(Recibo recibo, PDFConfig pdf) {

		try {

			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			sender.setHost("smtp.gmail.com");
			sender.setPort(587);

			sender.setUsername("serratecgrupo@gmail.com");
			sender.setPassword("serratec123");

			Properties props = sender.getJavaMailProperties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.debug", "true");

			MimeMessage mensagem = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mensagem);
			helper.setTo(recibo.getPaciente().getEmail());
			helper.setSubject("Recibo");
			helper.setText("Obrigado por solicitar! Falta so colocar o pdf aqui dentro.");

			sender.send(mensagem);

			return emailSucesso;

		} catch (Exception e) {
			throw new ResourceBadRequestException("Erro ao enviar email!");
		}

	}

}
