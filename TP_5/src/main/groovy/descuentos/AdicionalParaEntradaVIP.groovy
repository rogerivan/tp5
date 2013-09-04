package descuentos


class AdicionalParaEntradaVIP extends Descuento{
	
	@Override
	def cumpleCondicion(def entrada, def compra) {
		return (entrada.noches.size() > 1) 
	}
	
	@Override
	def registrarDescuento(def entrada, def compra) {
		entrada.descuentosAcumulados -= entrada.getPrecioSinDescuento() * this.getDescuentoCombo()
	}
	
	def getDescuentoCombo(){
		0.5
	}
	
}
