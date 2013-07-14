package dominio

import groovy.transform.TupleConstructor;

@TupleConstructor
class Banda {
	//XXX ojo, esto es un dto (data transfer object). No digo que este mal, sino solo que tengan cuidado 
	//de hacer esto
	def nombreBanda
	def categoria
	def precioCategoria
}
