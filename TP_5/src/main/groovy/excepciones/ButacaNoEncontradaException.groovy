package excepciones

class ButacaNoEncontradaException extends RuntimeException{
	
	public String getMessage() {
		return "No se ha encontrado ninguna butaca para esa fecha."
	}
}
