package descuentos


class AdicionalParaEntradaVIP extends Descuento{

	@Override
	def getDescuento(compra) {
		compra.entradasCompradas.each{
			if ( it.noches.size() > 1 ){
				it.descuentosAcumulados -= it.getPrecioSinDescuento() * this.getDescuentoCombo()
			}
		}
	}

	def getDescuentoCombo(){
		0.5
	}
	
}
