package descuentos

import tests.TestsDeDescuentos.Sexo;


class DescuentoParaDama extends Descuento{
	def totalEntradasFestival
	def entradasCompradasPorDamas
	enum Sexo{ Femenino }
	
	def DescuentoParaDama(totalEntradasFestival){
		this.totalEntradasFestival = totalEntradasFestival
		this.entradasCompradasPorDamas = 0
	}
	
	@Override
	def aplicarDescuento(compra){
		compra.entradasCompradas.each{
			//FIXME no es una buena idea modelar valores enumerados que no tengan comportamiento
			//de dominio usando strings o números, porque es fácil equivocarse al tipear y que nada funcione
			//o intentar comparar contra valores que no pertenecen a la enumeración (Esto último es más util
			//en lenguajes con tipado estático)
			//Recomiendo para tales casos usar enums. Por ejemplo, podrían 
			//Definir un enum Sexo con valores MASCULINO, FEMENINO y cualquier otra cosa que se les ocurra :P 						
			if( this.porcentajeEntradasCompradasPorDamas() <= 0.2 && it.espectador.sexo.toString() == Sexo.Femenino.toString()){
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
