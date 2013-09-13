package dominio

import org.uqbar.commons.utils.Observable

@Observable class Entrada {
	
	// ubicacion y banda1 son para jarcodear una entrada y poder setear sus atributos
	def ubicacion
	def banda1
	//jarcodeo una noche, butaca, comprador y espectador para mostrar el resultado de la entrada por pantalla
	Entrada(){
		comprador = new Comprador('Testa','Ferro')
		espectador  = new Espectador ('Jonas', 'Castillo', 23, 'Masculino')
		ubicacion = new Ubicacion("azul", 5)
		butacas = new Butaca(23, ubicacion, null)
		banda1 = new Banda("Radiohead", 4, 200)
		noches = new Noche( '13/09/2013', butacas, banda1)
	}
	
	def comprador
	def espectador
	/* Noches y butacas son colecciones, para poder implementar polimorficamente entrada y entradaVip. 
	 * Si es una entrada vip tendra la misma butaca, una por cada noche del recital.
	 * Si es una entrada comun tendra una sola butaca.*/
	def butacas = []
	def noches = []
	def fechaCompra
	def descuentosAcumulados

	def Entrada(noches, butacas, comprador, espectador){
		this.butacas = butacas
		this.noches = noches
		this.fechaCompra = new Date()
		this.comprador = comprador
		this.espectador = espectador
		this.descuentosAcumulados = 0
	}

	def getPrecioTotalButaca(){
		def butaca = this.butacas.first()
		return ( butaca.getPrecioBase() * this.butacas.size() )
	}
	
	def getPrecioTotalPorBanda(){
		def totalAcumulado = 0
		this.noches.each {
			totalAcumulado += it.buscarPrecioMaxCategoria()
		}
		totalAcumulado
	}
	
	def getPrecioSinDescuento(){
		this.getPrecioTotalButaca() + this.getPrecioTotalPorBanda()
	}

	def calcularPrecioConDescuento(){
		this.getPrecioSinDescuento() - this.descuentosAcumulados
	}
	
	def deshacerCompra(){
		noches.each{
			it.agregarButacaNoReservada(butacas.pop())
		}
	}
	
}
