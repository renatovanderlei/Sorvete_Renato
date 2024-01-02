package br.com.sorvete.exception;

public class InvalidOperationException extends RuntimeException{
	
	 private static final long serialVersionUID = 1L;

		public InvalidOperationException(String messageErro){
	        super(messageErro);
	    }

}
