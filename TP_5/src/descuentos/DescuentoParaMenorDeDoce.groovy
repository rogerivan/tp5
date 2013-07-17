package descuentos


class DescuentoParaMenorDeDoce extends Descuento{
	
	@Override
	def getDescuento(compra){
		compra.entradasCompradas.each{
			if ( it.espectador.edad < 12 && this.hayAlgunEspectadorMayor(compra.entradasCompradas, it.noches) )
				it.descuentosAcumulados += calcularDescuento(it.butacas.first())
		}
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
