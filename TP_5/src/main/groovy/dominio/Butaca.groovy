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
		if(this.contrasenia != contrasenia)			
			throw new ContraseniaNoCoincideException()
	}

	//PARA MOSTRAR LOS DATOS
	@Override
	public String toString() {
		return this.numeroButaca+'-'+this.ubicacion
	}
}
