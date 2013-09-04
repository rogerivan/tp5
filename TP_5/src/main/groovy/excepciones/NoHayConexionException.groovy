package excepciones

class NoHayConexionException extends RuntimeException{
	
	public String getMessage() {
		return "En este momento la conexion esta interrumpida. Por favor, intente mas tarde."
	}
}
