package descuentos


class DescuentoParaMenor extends Descuento{
	
	@Override
    def cumpleCondicion(def entrada, def compra ) {
		return (entrada.espectador.edad >= 12 && entrada.espectador.edad < 18)
	}
	
	@Override
	def registrarDescuento(def entrada, def compra) {
		entrada.descuentosAcumulados += calcularDescuento(entrada.butacas.first())
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
