package com.serratec.trabalhoAplicado.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceInternalServerErrorException extends RuntimeException{

	private static final long serialVersionUID = 3334080614906250335L;

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public class ResourceNotFoundException extends RuntimeException {

		private static final long serialVersionUID = 4182875561686093765L;

		public ResourceNotFoundException(String mensagem) {
			super(mensagem);
		}
	}
}
