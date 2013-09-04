package tests;

import static org.junit.Assert.*;
import excepciones.*
import dominio.*
import mediosDePago.Tarjeta
import org.junit.Ignore
import org.junit.Before
import org.junit.Test

class TestMediosDePago {
	
	class PaymentGatewayQueNoValidaDatos {
		def pagar(precioFinal, nombre, apellido, numero) {
		  throw new ValidacionDeDatosException()
		}
	}
	
	class PaymentGatewayQueNoConecta {
		def pagar(precioFinal, nombre, apellido, numero) {
		  throw new NoHayConexionException()
		}
	}
	
	def comprador
	
	def tarjetaValidacion
	def tarjetaConexion
	def paymentGatewayValidacion
	def paymentGatewayConexion
	
	def espectadores = []
	def espectador1
	
	def entrada
	
	def compra
	
	def butacasSinReservar = []
	def butacas = []
	def butaca1
	def butaca2
	def butaca3
	
	def ubicacion1
	def ubicacion2
	
	def contrasenia
	
	def planificacion
	
	def noches = []
	def noche1
	def noche2
	
	def fecha1
	def fecha2
	
	def banda1
	def banda2
	def bandas = []
	
	@Before
	void inicializar(){
		comprador = new Comprador('Testa','Ferro')
		
		espectador1 = new Espectador ('Jonas', 'Castillo', 23, 'Masculino')
		espectadores << espectador1
		
		ubicacion1 = new Ubicacion("azul", 5)
		ubicacion2 = new Ubicacion("amarillo", 50)
		
		butaca1 = new Butaca(23, ubicacion1, null)
		butaca2 = new Butaca(87, ubicacion2, null)
		butaca3 = new Butaca(9999, ubicacion1, null)
		butacas = [butaca1, butaca2]
		
		banda1 = new Banda("Radiohead", 4, 200)
		banda2 = new Banda("StereoFoot", 3, 100)
		bandas = [banda1, banda2]
		
		fecha1 = Date.parse( "yyyy-MM-dd", "2013-11-09" )
		fecha2 = Date.parse( "yyyy-MM-dd", "2013-11-10" )
		
		noche1 = new Noche(fecha1, butacas, bandas)
		noche2 = new Noche(fecha2, butacas, bandas)
		noches = [noche1, noche2]
		
		entrada = new Entrada(noches, butacas, comprador, espectador1)
		
		compra = new Compra(new Date())
		compra.entradasCompradas.add(entrada)
		
		planificacion = new Planificacion()
		planificacion.nochesConcierto = noches
		
		paymentGatewayValidacion = new PaymentGatewayQueNoValidaDatos()
		paymentGatewayConexion = new PaymentGatewayQueNoConecta()
		tarjetaValidacion = new Tarjeta(4327897, paymentGatewayValidacion)
		tarjetaConexion = new Tarjeta (63472648, paymentGatewayConexion)
		
	}
	
	@Test
	void testConexion(){
		try {
			tarjetaConexion.efectuarPago(compra, comprador)
		}
		catch (NoHayConexionException e){
			assert( tarjetaConexion.logsPagosPendientes.size() > 0)
		}
		
	}
	
	@Test
	void testValidacion(){
		try {
			tarjetaValidacion.efectuarPago(compra, comprador)
		}
		catch (ValidacionDeDatosException  e){
			assert( compra.entradasCompradas.size() == 0)
		}
	}
	
}
