package dominio

import groovy.transform.TupleConstructor;

@TupleConstructor
class Espectador {
	
	def nombre
	def apellido
	def edad
	def sexo
	
	//PARA MOSTRAR LOS DATOS
	@Override
	public String toString() {
		return this.nombre+' '+this.apellido
	}
}
