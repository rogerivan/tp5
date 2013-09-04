package excepciones

class FaltanButacasParaCompraEnComboException extends RuntimeException{
	
	public String getMessage() {
		return "La operacion no puede continuarse debido a que no existen butacas disponibles para todas las noches seleccionadas."
	}

}