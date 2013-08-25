package descuentos

class DescuentoParaDama extends Descuento{
	def totalEntradasFestival
	def entradasCompradasPorDamas
	enum Sexo{ Femenino }
	
	def DescuentoParaDama(totalEntradasFestival){
		this.totalEntradasFestival = totalEntradasFestival
		this.entradasCompradasPorDamas = 0
	}
			
	@Override
	def cumpleCondicion(def entrada, def compra) {
		return (this.porcentajeEntradasCompradasPorDamas() <= 0.2 && entrada.espectador.sexo.toString() == Sexo.Femenino.toString())
	}
	
	@Override
	def registrarDescuento(def entrada, def compra) {
		this.entradasCompradasPorDamas ++
		entrada.descuentosAcumulados += calcularDescuento(entrada.butacas.first())
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
