package com.serratec.trabalhoAplicado.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.TOO_MANY_REQUESTS)
public class ResourceAnyErrorException extends RuntimeException{

		private static final long serialVersionUID = 4182875561686093765L;

		public ResourceAnyErrorException(String mensagem) {
			super(mensagem);
		}
}
