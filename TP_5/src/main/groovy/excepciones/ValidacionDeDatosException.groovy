package excepciones

class ValidacionDeDatosException extends RuntimeException{
	
	public String getMessage() {
		return "Sus datos no han podido ser validados."
	}
}
