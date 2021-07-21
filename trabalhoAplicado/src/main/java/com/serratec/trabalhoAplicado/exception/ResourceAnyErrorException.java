package com.serratec.trabalhoAplicado.exception;



public class ResourceAnyErrorException extends RuntimeException{

		private static final long serialVersionUID = 4182875561686093765L;

		public ResourceAnyErrorException(String mensagem) {
			super(mensagem);
		}
}
