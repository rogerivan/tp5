package dominio

class Comprador {
	
	def nombre
	def apellido
	def compras = []

	/*
	 * Inicializador
	 */
	
	def Comprador(nombre, apellido){
		this.nombre = nombre
		this.apellido = apellido
	}
	
	/*
	 * Metodos Compra
	 */

	def agregarCompra(compra){
		this.compras << compra
	}
	
}
