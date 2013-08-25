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
		//FIXME no hagan condicion == true!!!
		def nochesQuePoseenButaca = this.nochesConcierto.findAll{ it.esButacaDisponible(butaca) }
		//FIXME no hagan if condicion return true else return false!!!!!
		return nochesQuePoseenButaca.size() == this.nochesConcierto.size()
	}
	
	/*
	 * Metodos de descuentos
	 */
	def aplicarDescuentos(compra){
		this.descuentosAplicables.each{ it.aplicarDescuento(compra) }
		
	}
	
	/*
	 * Metodos de compra entradas no reservadas.
	 */

	def verificarDisponibilidadButacas(noches,butacas){
		def butaca = butacas.first()
		if( noches.size() == 1){
			if( !noches.first().esButacaDisponible( butaca ) )
				false
			else
				true
		}
		else{
			if( !this.buscarButacaEnTodasLasNoches( butaca ) )
				false
			else
				true
		}
	}
	
	//Pensandolo desde una posible interfaz grafica, planificacion recibe
	//el pedido de compra junto con todos los datos necesarios, como colecciones
	//que funcionen como pilas -> hay correspondencia en el orden de las noches
	//las butacas y los espectadores. Estamos trabajando con un sublistado de noches
	//y butacas copia de los listados originales.
	def comprarEntradas(noches, butacas, espectadores, comprador, medioDePago){
		def butacasAux = []
		butacasAux.addAll(butacas)
		def compra = new Compra(new Date())
		
		noches.each{
			if ( this.verificarDisponibilidadButacas( it, butacasAux.first() ) ){
				butacasAux.remove(butacas.head())
				compra.entradasCompradas << this.generarEntrada( it, butacas.first(), comprador, espectadores.first() )
				butacas.remove( butacas.head() )
				espectadores.remove( espectadores.first() )
			}
			else{
				compra.entradasCompradas.each{
					it.deshacerCompra()
				}
				throw new ButacaNoEncontradaException()
			}
		}
		
		if ( compra.entradasCompradas != null ){
			this.aplicarDescuentos(compra)
			medioDePago.efectuarPago(compra, comprador)
			comprador.agregarCompra(compra)
		}
	}
	
	def generarEntrada(noches, butacas, comprador, espectador){
		def entrada = new Entrada(noches, butacas, comprador, espectador)
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
	
	def generarEntradaReservada(noche, butaca, espectadores, contrasenia, comprador){
		if( noche.buscarButacaReservada(butaca, contrasenia) ){
			noche.desbloquearButaca(butaca, contrasenia)
			def entrada = new Entrada(butaca, noche, comprador, espectadores.pop())
		}
	}
	
	def comprarEntradasReservadas(noche, butacasReservadas, espectadores, contrasenia, comprador){
		def compra = new Compra(new Date())
		butacasReservadas.each{
			compra.entradasEspeciales << this.generarEntradaReservada(noche, it, espectadores, contrasenia, comprador)
		}
		comprador.agregarCompra(compra)
	}
	
	def reservarButacas(noche, butacas, contrasenia){
		butacas.each{
			noche.reservarButaca(it, contrasenia)
		}
	}
	
}
