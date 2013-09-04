package excepciones

class ContraseniaNoEncontradaException extends RuntimeException{
	
	public String getMessage() {
		return "La contrasenia ingresada no coincide con ninguna de las butacas disponibles."
	}
}
