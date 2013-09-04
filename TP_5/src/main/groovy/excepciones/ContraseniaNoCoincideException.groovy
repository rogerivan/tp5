package excepciones

class ContraseniaNoCoincideException extends RuntimeException{
	
	public String getMessage() {
		return "Contraseña invalida"
	}
}
