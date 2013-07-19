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

	//FIXME esto es un pésimo diseño de interfaz
	//Están mezclando dos contratos de manejo de errores diferentes:
	//   * true/false: true signfica que funcionó, false que falló
	//   * terminación normal/excepción: 
	//           si la evaluación del mensaje no falla, significa que función, 
	//           una excepción significa lo contrario
	// Acá sin embargo están devolviendo un true si todo funciona, y pero lanzan una excepción si no.
	// Esto significa que NUNCA PUEDE RETORNAR FALSE. Ergo, NO HAY INFORMACIÓN EN EL TRUE, porque es el único
	// valor posible. Es decir, si el método no lanza excepción, la probabilidad de que devuelva true es 100%.
	// Como verán en Comunicaciones, esto es un caso extremo donde la entropía es nula y 
	// el sistema no aporta información http://es.wikipedia.org/wiki/Entrop%C3%ADa_(informaci%C3%B3n)
	// Puesto de otra forma, si ustedes ponen un if(buscarButacaReservada(butaca, contrasenia)) rama1 else rama2 
	// sólo podrá entrar en la rama1, por lo que la rama2 (y todo el if) es inútil
	// Moraleja: usen un contrato de comunicación errores consistente: o todo true/false, o todo ok/excepcion
	// ¿Cuál de las dos les conviene más? Eso depende de cómo piensen enviar este mensaje.   
	// Si en el caso en que falle deberían hacer algo y el problema no se propagará a todos los otroso objetos
	// involucrados en la pila de envío de mensajes, entonces un true/false puede ser razonable. 
	// Pero si, por el contrario, no pueden hacer mucho, y no van a tener mas opción que reportar
	// los errores a los niveles superiores, entonces las excepciones son un mejor mencanismo porque por defecto
	// "burbujean". Yo veo que éste es el caso.  
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
