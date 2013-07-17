package dominio

import excepciones.ButacaNoEncontradaException;
import excepciones.ContraseniaNoEncontradaException;
import groovy.transform.TupleConstructor;

class Noche {

	def fecha
	def butacas = [] as Set
	def bandas = [] as Set
	def butacasReservadas = [] as Set

	/*
	 Metodo Constructor
	 */
	def Noche(fecha,butacas, bandas){
		this.fecha = fecha
		this.butacas.addAll(butacas)
		this.bandas.addAll(bandas)
	}

	/*
	 Metodos de butacas no reservadas
	 */

	def agregarButacaNoReservada(butaca){

		this.butacas << butaca
	}

	def sacarButacaNoReservada(butaca){
		this.butacas.removeAll{it == butaca}
	}

	def esButacaDisponible(butaca){
		this.butacas.any{it == butaca}
	}

	/*
	 Metodos para bandas
	 */

	def buscarPrecioMaxCategoria(){

		def categoriaMax = 1
		def precioMax = 0

		bandas.each {
			if (categoriaMax < it.categoria){

				categoriaMax = it.categoria
				precioMax = it.precioCategoria
			}
		}

		return precioMax
	}

	def agregarBanda(banda){

		this.bandas << banda
	}

	/*
	 Metodos Butacas Reservadas
	 */

	def buscarButacaReservada(butaca, contrasenia){
		if(butacasReservadas.find{ it.contrasenia == contrasenia })
			true
		else
			throw new ButacaNoEncontradaException()
	}

	def sacarButacaReservada(butaca){
		this.butacasReservadas.removeAll{it == butaca}
	}

	def desbloquearButaca(butaca, contrasenia){
		if (this.buscarButacaReservada(butaca, contrasenia)){
			butaca.desbloquearButaca(contrasenia)
			this.agregarButacaNoReservada(butaca)
			this.sacarButacaReservada(butaca)
		}
		else
			throw new ButacaNoEncontradaException()
	}

	def reservarButaca(butaca, contrasenia){
		if (this.esButacaDisponible(butaca)){
			butaca.reservarButaca(contrasenia)
			this.sacarButacaNoReservada(butaca)
			this.butacasReservadas << butaca
		}
		else
			throw new ButacaNoEncontradaException()
	}
}
