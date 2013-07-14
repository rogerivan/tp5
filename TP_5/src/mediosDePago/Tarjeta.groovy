package mediosDePago

import java.lang.Exception

import dominio.Log

import excepciones.*

class Tarjeta implements MedioDePago{

	def paymentGateway
	def numero
	def logsPagosPendientes = [] as Set
	
	def Tarjeta(numero, paymentGateway){
		this.numero = numero
	}
	
	@Override
	def efectuarPago(compra, comprador){
		//TRY CATCH POR LOS DOS ERRORES QUE PUEDE TIRAR EL SISTEMA DE PAGO
		try{
			this.paymentGateway.pagar(compra.calcularPrecioFinal(), comprador.nombre,comprador.apellido, this.numero)	
		}
		catch(NoHayConexionException e){
			//Registrar un pago pendiente en el sistema
			this.logsPagosPendientes << new Log(new Date(), compra, comprador, this.numero)
			throw new NoHayConexionException()
		}
		catch(ValidacionDeDatosException e2){
			//Deshacer la compra, osea la/s butaca/s pasa/n a estar disponible/s otra vez
			compra.entradasCompradas.each{it.deshacerCompra}
			throw new ValidacionDeDatosException()
		}
	}
}
