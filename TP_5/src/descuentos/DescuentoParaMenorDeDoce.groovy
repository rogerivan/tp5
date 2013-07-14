package descuentos


class DescuentoParaMenorDeDoce extends Descuento{
	
	@Override
	def getDescuento(compra){
		compra.entradasCompradas.each{
			if ( it.espectador.edad < 12 && this.hayAlgunEspectadorMayor(compra.entradasCompradas) )
				it.descuentosAcumulados += calcularDescuento(it.butacas.first())
		}
	}
	
	def hayAlgunEspectadorMayor(entradasCompradas){
		entradasCompradas.any{it.espectador.edad > 18}
	}
	
	def calcularDescuento(butaca){
		butaca.getPrecioBase() * this.descuento
	}
	
	def getDescuento(){
		0.50
	}

}
