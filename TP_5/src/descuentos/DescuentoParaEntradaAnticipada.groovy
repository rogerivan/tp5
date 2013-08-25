package descuentos

import groovy.time.TimeCategory

class DescuentoParaEntradaAnticipada extends Descuento{

	@Override
	def aplicarDescuento(compra) {
		compra.entradasCompradas.each{
			def fechaRecital = this.getPrimeraFechaDelRecital(it)
			if ( (fechaRecital - compra.fechaCompra) >= 30 ){
				it.descuentosAcumulados += it.getPrecioSinDescuento() * this.getDescuentoCombo()
			}
		}
	}
	
	def getPrimeraFechaDelRecital(entrada){
		def noche = entrada.noches.min{it.fecha}
		noche.fecha
	}
	
	def getDescuentoCombo(){
		0.1
	}
	
}
