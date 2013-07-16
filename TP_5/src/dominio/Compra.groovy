package dominio

import groovy.transform.TupleConstructor;

@TupleConstructor
class Compra {

	def fechaCompra
	def entradasCompradas = []
	def entradasEspeciales = []
	def descuento = 0
	
	def calcularPrecioFinal(){
		this.getMontoTotalEntradas() - this.descuento
	}
	
	def getMontoTotalEntradas(){
		def precioTotal = 0
		this.entradasCompradas.each{precioTotal += it.calcularPrecioConDescuento()}
		precioTotal
	}
	
}
