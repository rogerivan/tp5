package descuentos


class DescuentoCompraEnCombo extends Descuento{

	@Override
	def getDescuento(compra) {
		if (compra.entradasCompradas.size() > 1 && compra.getMontoTotalEntradas() > 1000)
			compra.descuento = compra.getMontoTotalEntradas() * this.getDescuentoCombo()
	}

	def getDescuentoCombo(){
		0.1
	}
	
}
