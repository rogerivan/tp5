package dominio

import excepciones.ContraseniaNoCoincideException
import groovy.transform.TupleConstructor

class Butaca {
	
	def numeroButaca
	def ubicacion
	def contrasenia
	
	def Butaca(numeroButaca, ubicacion, contrasenia){
		this.numeroButaca = numeroButaca
		this.ubicacion = ubicacion
		this.contrasenia = contrasenia
	}
	
	def getPrecioBase() {
		this.ubicacion.calcularPrecioBase()
	}
	
	def reservarButaca(contrasenia){
		this.setContrasenia(contrasenia)
	}
	
	def desbloquearButaca(contrasenia){
		if(this.contrasenia == contrasenia)
			this.contrasenia = null //FIXME porqué lo setean en null? Que pasaría si deshacen la compra?
		else
			throw new ContraseniaNoCoincideException()
	}

}
