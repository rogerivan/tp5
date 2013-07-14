package descuentos


class DescuentoParaJubilado extends Descuento{
	
	@Override
	def getDescuento(compra){
		compra.entradasCompradas.each{
			if(it.espectador.edad > 65)
				it.descuentosAcumulados += calcularDescuento(it.butacas.first())
		}
	}	

	def calcularDescuento(butaca){
		butaca.getPrecioBase() * this.descuento
	}
	
	def getDescuento() {
		0.15
	}
	
}
