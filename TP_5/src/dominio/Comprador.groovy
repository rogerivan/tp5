package dominio

import excepciones.*
import excepciones.ButacaNoEncontradaException;
import excepciones.FaltanButacasParaCompraEnComboException;

class Comprador {

	def nombre
	def apellido
	def compras = []

	/*
	 * Inicializador
	 */
	
	def Comprador(nombre, apellido){
		this.nombre = nombre
		this.apellido = apellido
	}
	
	/*
	 * Metodos busqueda
	 */

	def buscarNochesPorFecha(fechas, planificacion){
		def nochesConcierto = []
		fechas.each{
			if(it.size() == 1){
				def noches = []
				noches.push( planificacion.buscarNoche( it.first() ) )
				nochesConcierto.push(noches)
			}
			else{
				def noches = []
				noches = planificacion.nochesConcierto
				nochesConcierto.push(noches)
			}
		}
		nochesConcierto
	}

	//Estrategia: por cada noche en las fechas se agrega una butaca
	//entonces si no se pudo conseguir una butaca para una de las noches
	//la longitud de ambas colecciones va a diferir. Como se agregan elementos
	//a la pila, se ingresan de manera ordenada, entonces se puede realizar
	//una comparacion elemento a elemento de cada coleccion.
	def buscarButacasParaNoches(fechas, espectadores, planificacion){
		def nochesConcierto = this.buscarNochesPorFecha(fechas, planificacion)
		def butacasAComprar = this.buscarButacasDisponiblesParaCadaNoche(nochesConcierto, planificacion)
		if (butacasAComprar.size() != nochesConcierto.size())
			throw new FaltanButacasParaCompraEnComboException()
		else
			this.comprarEntradas(nochesConcierto, butacasAComprar, espectadores, planificacion)
	}

	def buscarButacasDisponiblesParaCadaNoche(noches, planificacion){
		def butacasAComprar = []
		noches.each{
			if (it.size() == 1){
				def butacasEncontradas = planificacion.buscarButacaDisponible( it.first() )
				butacasAComprar.push( butacasEncontradas )
			}
			else{
				def butaca = planificacion.buscarButacaDisponible( it.first() )
				if ( planificacion.buscarButacaEnTodasLasNoches(butaca) ){
					def butacasSeleccionadas = []
					it.each{
						butacasSeleccionadas.push( butaca.first() )
					}
					butacasAComprar.push(butacasSeleccionadas)
				}
				else
					throw new ButacaNoEncontradaException()
			}
		}
		butacasAComprar
	}

	/*
	 * Metodos Compra
	 */

	def agregarCompra(compra){
		this.compras << compra
	}

	def comprarEntradas(noches, butacas, espectadores, planificacion){
		planificacion.comprarEntradas(noches, butacas, espectadores, this)
	}
	
	def efectuarPago(compra, medioDePago){
		compra.efectuarPago(this, medioDePago)
	}

	/*
	 * Metodos Reservadas: No usan coleccion de coleccion, NO CONFUNDIRSE!!!!
	 */

	def buscarNoche(fecha, planificacion){
		planificacion.buscarNoche(fecha)
	}

	//Las entradas reservadas se generan por noche
	//Al ser parte de descuentos especiales o regalos y como no se pagan una vez generadas no se van a cancelar
	//por lo cual la contrasenia no necesita ser guardada
	def buscarButacasReservadas(fecha, butacas, contrasenia, espectadores, planificacion){
		def noche = this.buscarNoche(fecha, planificacion)
		def butacasReservadas = []
		butacas.each{
			def butaca = planificacion.buscarButacaReservada(noche, it, contrasenia)
			butacasReservadas.push(butaca)
		} //Tiene un problema entre la linea de arriba y la de abajo
			this.generarEntradasReservadas(noche, butacasReservadas, espectadores, contrasenia, planificacion)
	}

	def generarEntradasReservadas(noche, butacasReservadas, espectadores, contrasenia, planificacion){
		def compra = new Compra(new Date())
		butacasReservadas.each{compra.entradasEspeciales << planificacion.generarEntradasReservadas(noche, it, this, espectadores, contrasenia)}
		this.compras << compra
	}

	def reservarButacas(fecha, butacas, contrasenia, planificacion){
		def noche = this.buscarNoche(fecha, planificacion)
		butacas.each{planificacion.reservarButaca(noche, it, contrasenia)}
	}
}
