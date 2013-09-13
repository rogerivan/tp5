package dominio

import groovy.transform.TupleConstructor;

@TupleConstructor
class Ubicacion {
	
	def sector
	def fila
	
	def calcularPrecioBase(){
		//TODO Valor harcodeado, la tipica clase PONELE! 
		return fila*3
	}
	
	//PARA MOSTRAR LOS DATOS
	@Override
	public String toString() {
		return this.sector+'-'+this.fila
	}
}
