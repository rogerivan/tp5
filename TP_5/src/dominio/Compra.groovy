package dominio

import groovy.transform.TupleConstructor;

@TupleConstructor
class Compra {

	def fechaCompra
	def entradasCompradas = []
	def entradasEspeciales = []
	def descuento = 0
	
	// TODO va aca?
	def efectuarPago(comprador, medioDePago){
		//compra se envia a si mismo para que medioDePago pueda deshacer la compra si es necesario
		medioDePago.efectuarPago(this, comprador)
	}
	
	def calcularPrecioFinal(){
		this.getMontoTotalEntradas() - this.descuento
	}
	
	def getMontoTotalEntradas(){
		def precioTotal = 0
		this.entradasCompradas.each{precioTotal += it.calcularPrecioConDescuento()}
		precioTotal
	}
	
}
