package com.serratec.trabalhoAplicado.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceBadRequestException extends RuntimeException {

	private static final long serialVersionUID = -7720461733837115848L;

	public ResourceBadRequestException(String mensagem) {
		super(mensagem);
	}
	
}
