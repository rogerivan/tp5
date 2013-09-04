package descuentos


class DescuentoCompraEnCombo extends Descuento{
	
	@Override
	def cumpleCondicion(def entrada,  def compra) {
		return (compra.entradasCompradas.size() > 1 && compra.getMontoTotalEntradas() > 1000)
	}
	
	@Override
	def registrarDescuento(def entrada, def compra) {
		compra.descuento = compra.getMontoTotalEntradas() * this.getDescuentoCombo()
	}
	
	def getDescuentoCombo(){
		0.1
	}
	
}
