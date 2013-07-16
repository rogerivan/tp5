package dominio

import excepciones.ButacaNoEncontradaException

class Planificacion {
	
	def nochesConcierto = [] as Set
	def descuentosAplicables = [] as Set
	
	/*
	 * Metodos noche
	 */
	
	def agregarNoche(noche){
		nochesConcierto << noche
	}
	
	/*
	 * Metodos butaca
	 */
	
	//Este metodo verifica que para todas las noches la butaca seleccionada, este disponible [entrada VIP]
	def buscarButacaEnTodasLasNoches(butaca){
		def nochesQuePoseenButaca = this.nochesConcierto.findAll{ it.buscarButaca(butaca) == true }
		if ( nochesQuePoseenButaca.size() == this.nochesConcierto.size() )
			true
		else
			false
	}
	
	/*
	 * Metodos de descuentos
	 */
	def aplicarDescuentos(compra){
		this.descuentosAplicables.each{ it.getDescuento(compra) }
		
	}
	
	/*
	 * Metodos de compra entradas no reservadas.
	 */

	def verificarDisponibilidadButacas(noches,butacas){
		def butacasAux = butacas
		
		noches.each{
			def butaca = butacasAux.pop()
		
			if( it.size() == 1){
				if( !it.esButacaDisponible( butaca.first() ) )
					throw new ButacaNoEncontradaException()
				else
					true
			}
			else{
				if( !it.buscarButacaEnTodasLasNoches( butaca.first() ) )
					throw new ButacaNoEncontradaException()
				else
					true
			}
		}
	}
	
	//Pensandolo desde una posible interfaz grafica, planificacion recibe
	//el pedido de compra junto con todos los datos necesarios, como colecciones
	//que funcionen como pilas -> hay correspondencia en el orden de las noches
	//las butacas y los espectadores. Estamos trabajando con un sublistado de noches
	//y butacas copia de los listados originales.
	def comprarEntradas(noches, butacas, espectadores,comprador, medioDePago){
		if( verificarDisponibilidadButacas(noches, butacas) ){
			def compra = new Compra(new Date())
			noches.each{
				this.generarEntrada(it,butacas.pop(), comprador, espectadores.pop(), compra)
			}
			this.aplicarDescuentos(compra)
			medioDePago.efectuarPago(compra, comprador)
			comprador.agregarCompra(compra)
		}
	}
	
	def generarEntrada(noches, butacas, comprador, espectador, compra){
		def entrada = new Entrada(noches, butacas, comprador, espectador)
		compra.entradasCompradas << entrada
		noches.each{
			it.sacarButacaNoReservada( butacas.first() )
		}
		entrada
	}
	
	/*
	 * Metodos reservadas
	 */
	
	//ACLARACION 1: las entradas reservadas solo se "compran" para una sola noche a la vez.
	//ACLARACION 2: al generar las entradas reservadas no se incluye la contraseña porque
	//las mismas no se pueden cancelar en ningun momento una vez generadas.
	
	def generarEntradaReservada(noche, butaca, contrasenia){
		if( noche.buscarButacaReservada(butaca, contrasenia) ){
			noche.desbloquearButaca(butaca, contrasenia)
			def entrada = new Entrada(butaca, noche, comprador, espectadores.pop())
		}
	}
	
	def comprarEntradasReservadas(noche, butacasReservadas, espectadores, contrasenia, comprador){
		def compra = new Compra(new Date())
		butacasReservadas.each{
			compra.entradasEspeciales << this.generarEntradaReservada(noche, it, this, espectadores, contrasenia)
		}
		comprador.agregarCompra(compra)
	}
	
	def reservarButacas(noche, butacas, contrasenia){
		butacas.each{
			noche.reservarButaca(it, contrasenia)
		}
	}
	
}
