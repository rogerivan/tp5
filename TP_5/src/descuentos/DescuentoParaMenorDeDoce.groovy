package descuentos


class DescuentoParaMenorDeDoce extends Descuento{
	
	//FIXME no ven lógica repetida entre todos los descuentos?
	//la mayoría de ellos tienen la forma:
	//	compra.entradasCompradas.each{
	//		if ( ... se cumple alguna condicion ....)
	//			it.descuentosAcumulados +/-=.....el descuento propiamente dicho...
	//	}
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
