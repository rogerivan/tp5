package dominio

import groovy.transform.TupleConstructor;

@TupleConstructor
class Log {

	def fecha
	def compra
	def comprador
	def numeroTarjeta
}
