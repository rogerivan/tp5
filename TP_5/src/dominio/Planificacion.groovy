package dominio

class Planificacion {
	
	def nochesConcierto = [] as Set
	def descuentosAplicables = [] as Set

	/*
	 * Metodos noche
	 */
	
	def agregarNoche(noche){	
		nochesConcierto << noche
	}
	
	def buscarNoche(fecha) { 
		this.nochesConcierto.find{ it.fecha == fecha }
	}
	
	/*
	 * Metodos butaca
	 */
	
	def buscarButacaDisponible(noche){
		noche.buscarPrimeraButacaDisponible()
	}
	
	def buscarButacaEnTodasLasNoches(butaca){
		def nochesQuePoseenButaca = this.nochesConcierto.findAll{ it.buscarButaca(butaca) == true }
		if ( nochesQuePoseenButaca.size() == this.nochesConcierto.size() )
			true
		else
			false
	}
	
	def comprarEntradas(noches, butacas, espectadores,comprador){
		def compra = new Compra(new Date())
		noches.each{ 
			this.generarEntrada(it,butacas.pop(), comprador, espectadores.pop(), compra)
		}
		this.aplicarDescuentos(compra)
		comprador.agregarCompra(compra)
	}
	
	def generarEntrada(noches, butacas, comprador, espectador, compra){
		def entrada = new Entrada(noches, butacas, comprador, espectador)
		compra.entradasCompradas << entrada
		noches.each{
			it.sacarButacaNoReservada( butacas.first() )
		}
		entrada
	}
	
	def aplicarDescuentos(compra){
		this.descuentosAplicables.each{ it.getDescuento(compra) } 
		
	}
	
	/*
	 * Metodos reservadas
	 */
	
	def buscarButacaReservada(noche, butaca, contrasenia){
		noche.buscarButacaReservada(butaca, contrasenia)
	}
	
	def generarEntradasReservadas(noche, butaca, comprador, espectadores, contrasenia){
		noche.desbloquearButaca(butaca, contrasenia)
		def entrada = new Entrada(butaca, noche, comprador, espectadores.pop())
}

	def reservarButaca(noche, butaca, contrasenia){
		noche.reservarButaca(butaca, contrasenia)
	}
}
