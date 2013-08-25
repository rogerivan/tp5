package tests;

import static org.junit.Assert.*;
import dominio.*
import mediosDePago.*
import descuentos.*
import org.junit.Ignore
import org.junit.Before
import org.junit.Test

class TestsDeDescuentos {
	def comprador
	
	def efectivo = new Efectivo()
	
	def espectadorJubilado
	def espectador12_18
	def espectadora
	def espectadorMayor
	def espectadorMenor12
	
	def ubicacion1
	def ubicacion2
	def ubicacion3
	
	def butacasReservadas = []
	def butacas = []
	def butaca1
	def butaca2
	def butaca3
	
	def bandas = []
	def banda1
	def banda2
	
	def fecha1
	def fecha2
	
	def noches = []
	def noche1
	def noche2
	
	def planificacion
	
	def totalEntradasAlInicio
	
	enum Sexo { Femenino, Masculino }
	
	@Before
	void inicializar(){
		comprador = new Comprador("Testa", "Ferro")
				
		espectadorJubilado = new Espectador("Martin", "Ramirez", 67, Sexo.Masculino)
		espectador12_18 = new Espectador("Diego", "Gonzalez", 17, Sexo.Masculino)
		espectadora = new Espectador("Julieta", "Diaz", 23, Sexo.Femenino)
		espectadorMayor = new Espectador("Roger", "Rodriguez", 24, Sexo.Masculino)
		espectadorMenor12 = new Espectador("Jonas", "Castillo", 11, Sexo.Masculino)
		
		ubicacion1 = new Ubicacion("azul", 200)
		ubicacion2 = new Ubicacion("rosa", 101)
		ubicacion3 = new Ubicacion("verde", 60)
		
		butaca1 = new Butaca(312, ubicacion1, null)
		butaca2 = new Butaca(324, ubicacion2, null)
		butaca3 = new Butaca(900, ubicacion3, null)
		butacas = [butaca1, butaca2, butaca3]
		
		banda1 = new Banda("parkaBlanca", 3, 100)
		banda2 = new Banda("elPastorJudaz", 4, 200)
		bandas = [banda1, banda2]
		
		fecha1 = Date.parse( "yyyy-MM-dd", "2013-11-09" )
		fecha2 = Date.parse( "yyyy-MM-dd", "2013-11-11" )
		
		noche1 = new Noche(fecha1, butacas, bandas)
		noche2 = new Noche(fecha2, butacas, bandas)
		noches = [noche1, noche2]
		
		totalEntradasAlInicio = noches.size() * butacas.size()
		
		planificacion = new Planificacion()
		planificacion.nochesConcierto = noches
		planificacion.descuentosAplicables << new DescuentoParaJubilado()
		planificacion.descuentosAplicables << new DescuentoParaMenor()
		planificacion.descuentosAplicables << new DescuentoParaDama(totalEntradasAlInicio)
		planificacion.descuentosAplicables << new DescuentoParaEntradaAnticipada()
		planificacion.descuentosAplicables << new DescuentoParaMenorDeDoce()
		planificacion.descuentosAplicables << new AdicionalParaEntradaVIP()
		planificacion.descuentosAplicables << new DescuentoCompraEnCombo()
	}
	
	/*
	 * El valor base de la butaca sera el numero de fila de la ubicacion multiplicado por 3 
	 * - valor hardcodeado dado que no hay precisiones de como se calcula el precio base-
	 */
		
	@Test
	void verficarDescuentoParaJubilado(){
		def compra = new Compra( Date.parse( "yyyy-MM-dd", "2013-11-01" ) )
		compra.entradasCompradas << new Entrada( [noche1], [butaca1], comprador, espectadorJubilado )
		//Luego de haber realizado la compra el valor base de la butaca es 200*3=600 
		//y el valor adicional de la banda de esa fecha es de 200 => total sin descuento = 800 
		planificacion.aplicarDescuentos(compra)
		//El descuento del 15% del precio base es 600*0.15 = 90
		def entrada = compra.entradasCompradas.first()
		assert ( entrada.descuentosAcumulados == 90 )	
	}
	
	@Test
	void verficarDescuentoParaMenor18Mayor12(){
		def compra = new Compra( Date.parse( "yyyy-MM-dd", "2013-11-01" ) )
		compra.entradasCompradas << new Entrada( [noche1], [butaca1], comprador, espectador12_18 )
		//Luego de haber realizado la compra el valor base de la butaca es 200*3=600
		//y el valor adicional de la banda de esa fecha es de 200 => total sin descuento = 800
		planificacion.aplicarDescuentos(compra)
		//El descuento del 20% del precio base es 600*0.2 = 120, se aplica este descuento debido
		//a que el valor base supera los 100
		def entrada = compra.entradasCompradas.first()
		assert ( entrada.descuentosAcumulados == 120 )
	}
	
	@Test 
	void verficarDescuentoParaDama(){
		def compra = new Compra( Date.parse( "yyyy-MM-dd", "2013-11-01" ) )
		compra.entradasCompradas << new Entrada( [noche1], [butaca1], comprador, espectadora )
		//Luego de haber realizado la compra el valor base de la butaca es 200*3=600
		//y el valor adicional de la banda de esa fecha es de 200 => total sin descuento = 800
		planificacion.aplicarDescuentos(compra)
		//El descuento del 20% del precio base es 600*0.2 = 120, se aplica este descuento debido
		//a que solamente se compro una entrada y representa el 11,11% del total de entradas
		//(siendo el total de entradas disponibles al inicio 9)
		def entrada = compra.entradasCompradas.first()
		assert ( entrada.descuentosAcumulados == 120 )
	}
	
	@Test
	void verficarDescuentoParaEntradaAnticipada(){
		def compra = new Compra( Date.parse( "yyyy-MM-dd", "2013-10-01" ) )
		compra.entradasCompradas << new Entrada( [noche1], [butaca1], comprador, espectadorMayor )
		//Luego de haber realizado la compra el valor base de la butaca es 200*3=600
		//y el valor adicional de la banda de esa fecha es de 200 => total sin descuento = 800
		planificacion.aplicarDescuentos(compra)
		//El descuento del 10% del subtotal de la entrada es 800*0.1 = 80, se aplica este descuento debido
		//a que la entrada se compro 30 dias antes de la primera fecha de recital del festival
		def entrada = compra.entradasCompradas.first()
		assert ( entrada.descuentosAcumulados == 80 )
	}
	
	@Test
	void verficarDescuentoParaMenor12(){
		def compra = new Compra( Date.parse( "yyyy-MM-dd", "2013-11-01" ) )
		compra.entradasCompradas << new Entrada( [noche1], [butaca1], comprador, espectadorMayor )
		compra.entradasCompradas << new Entrada( [noche1], [butaca2], comprador, espectadorMenor12 )
		//Mayor:
		//Luego de haber realizado la compra el valor base de la butaca es 200*3=600
		//y el valor adicional de la banda de esa fecha es de 200 => total sin descuento = 800
		//Menor:
		//Luego de haber realizado la compra el valor base de la butaca es 101*3=303
		//y el valor adicional de la banda de esa fecha es de 200 => total sin descuento = 503
		planificacion.aplicarDescuentos(compra)
		//El descuento del 20% del precio base es 303*0.5 = 151,5, se aplica este descuento debido
		//a que el menor es acompañado por un mayor
		def entrada = compra.entradasCompradas.find{ it.espectador.edad == 11 }
		assert ( entrada.descuentosAcumulados == 151.5 )
	}
	
	@Test
	void verficarAdicionalEntradaVip(){
		def compra = new Compra( Date.parse( "yyyy-MM-dd", "2013-11-01" ) )
		compra.entradasCompradas << new Entrada( [noche1, noche2], [butaca1, butaca1], comprador, espectadorMayor )
		//Luego de haber realizado la compra el valor base de la butaca por cada noche es 200*3*2=1200
		//y el valor adicional de la banda de esa fecha es de 200*2 = 400 => total sin descuento = 1600
		planificacion.aplicarDescuentos(compra)
		//El valor adicional del 50% del subtotal de la entrada es 1600*0.5 = 800
		def entrada = compra.entradasCompradas.first()
		assert ( entrada.descuentosAcumulados == -800 )
	}
	
	@Test
	void verficarDescuentoCompraCombo(){
		def compra = new Compra( Date.parse( "yyyy-MM-dd", "2013-11-01" ) )
		compra.entradasCompradas << new Entrada( [noche1], [butaca1], comprador, espectadorMayor )
		compra.entradasCompradas << new Entrada( [noche2], [butaca2], comprador, espectadorMayor )
		//Entrada noche 1:
		//Luego de haber realizado la compra el valor base de la butaca es 200*3=600
		//y el valor adicional de la banda de esa fecha es de 200 => total sin descuento = 800
		//Entrada noche 2:
		//Luego de haber realizado la compra el valor base de la butaca es 101*3=303
		//y el valor adicional de la banda de esa fecha es de 200 => total sin descuento = 503
		//Subtotal de compra = 1303
		planificacion.aplicarDescuentos(compra)
		//El descuento del 10% del subtotal de la compra es 1303*0.1 = 130.3, se aplica este descuento debido
		//a que el subtotal de la compra supera los 1000
		assert ( compra.descuento == 130.3 )
	}
}
