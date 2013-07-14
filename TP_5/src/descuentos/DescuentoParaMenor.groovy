package descuentos


class DescuentoParaMenor extends Descuento{
	@Override
	def getDescuento(compra){
		compra.entradasCompradas.each{
			if (it.espectador.edad >= 12 && it.espectador.edad < 18)
				it.descuentosAcumulados += calcularDescuento(it.butacas.first())
		}
	}
	
	def calcularDescuento(butaca){
		def valorBase = butaca.getPrecioBase()
		
		if(valorBase > 100)
			valorBase * this.descuento
		else {
			if(50 < valorBase && valorBase <= 100)
				10
			else
				0
		}
	}
	
	def getDescuento() {
		0.20
	}
}
