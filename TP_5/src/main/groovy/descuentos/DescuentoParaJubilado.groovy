package descuentos


class DescuentoParaJubilado extends Descuento{
	
	@Override
	def cumpleCondicion(def entrada, def compra) {
		return (entrada.espectador.edad > 65)
	}
	
	@Override
	def registrarDescuento(def entrada, def compra) {
		entrada.descuentosAcumulados += calcularDescuento(entrada.butacas.first())
	}
	
	def calcularDescuento(butaca){
		butaca.getPrecioBase() * this.descuento
	}
	
	def getDescuento() {
		0.15
	}
	
}
