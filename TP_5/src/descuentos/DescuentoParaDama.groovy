package descuentos


class DescuentoParaDama extends Descuento{
	def totalEntradasFestival
	def entradasCompradasPorDamas
	
	def DescuentoParaDama(totalEntradasFestival){
		this.totalEntradasFestival = totalEntradasFestival
		this.entradasCompradasPorDamas = 0
	}
	
	@Override
	def getDescuento(compra){
		compra.entradasCompradas.each{
			if( this.porcentajeEntradasCompradasPorDamas() <= 0.2 && it.espectador.sexo == "Femenino"){
				this.entradasCompradasPorDamas ++
				it.descuentosAcumulados += calcularDescuento(it.butacas.first())
			}
		}
	}
	
	def porcentajeEntradasCompradasPorDamas(){
		(100*this.entradasCompradasPorDamas)/this.totalEntradasFestival
	}
	
	def calcularDescuento(butaca){
		butaca.getPrecioBase() * this.descuento
	}
	
	def getDescuento() {
		0.20
	}
	
}
