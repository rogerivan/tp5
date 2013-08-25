package descuentos

import groovy.time.TimeCategory

class DescuentoParaEntradaAnticipada extends Descuento{
	
	@Override
	def cumpleCondicion(def entrada, def compra) {
		def fechaRecital = this.getPrimeraFechaDelRecital(entrada)
		return ((fechaRecital - compra.fechaCompra) >= 30)
	}
	
	@Override
	def registrarDescuento(def entrada, def compra) {
		entrada.descuentosAcumulados += entrada.getPrecioSinDescuento() * this.getDescuentoCombo()
	}
	
	def getPrimeraFechaDelRecital(entrada){
		def noche = entrada.noches.min{it.fecha}
		noche.fecha
	}
	
	def getDescuentoCombo(){
		0.1
	}
	
}
