package descuentos


class DescuentoParaMenorDeDoce extends Descuento{
			
	@Override
	def cumpleCondicion(def entrada, def compra) {
		return (entrada.espectador.edad < 12 && this.hayAlgunEspectadorMayor(compra.entradasCompradas, entrada.noches))
	}
	
	@Override
	def registrarDescuento(def entrada, def compra) {
		entrada.descuentosAcumulados += calcularDescuento(entrada.butacas.first())
	}
	
	def hayAlgunEspectadorMayor(entradasCompradas, noches){
		entradasCompradas.any{it.espectador.edad > 18 && it.noches == noches}
	}
	
	def calcularDescuento(butaca){
		butaca.getPrecioBase() * this.descuento
	}
	
	def getDescuento(){
		0.50
	}

}
