package tests;

import static org.junit.Assert.*
import dominio.*
import mediosDePago.*
import org.junit.Ignore
import org.junit.Before
import org.junit.Test

class TestsDeCompra {

	def comprador
	
	def efectivo = new Efectivo()
	
	def espectador1
	
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
		
		planificacion = new Planificacion()
		planificacion.nochesConcierto = noches
	}
	
	@Test
	void probarMetodoComprarEntradasParaUnaSolaEntrada(){
		planificacion.comprarEntradas([[noche1]], [[butaca1]], [[espectador1]], comprador, efectivo)
		def compras = comprador.compras
		//Verifica que sea una sola compra
		assert( compras.size() == 1 )
		def entradas = compras.first().entradasCompradas
		//Verifica que sea una sola entrada
		assert( entradas.size() == 1 )
		def entrada = entradas.first()
		//Verifica que la entrada fue generada con la noche y la butaca solicitada
		assert( entrada.noches == [noche1] && entrada.butacas == [butaca1] )
		//Verifica en planificacion que en la noche solicitada la butaca haya sido vendida
		assert(!planificacion.nochesConcierto.any{ it == noche1 && it.butacas.any{ it == butaca1 } })
	}
	
	@Test
	void probarMetodoComprarEntradasParaEntradasEnCombo(){
		planificacion.comprarEntradas([[noche1],[noche2]], [[butaca1],[butaca1]], [[espectador1],[espectador1]], comprador, efectivo)
		def compras = comprador.compras
		//Verifica que sea una sola compra
		assert( compras.size() == 1 )
		def entradas = compras.first().entradasCompradas
		//Verifica que sean dos entradas
		assert( entradas.size() == 2 )
		def entrada1 = entradas.first()
		def entrada2 = entradas.last()
		//Verifica que las entradas fueron generadas con las noches y la butacas solicitadas
		assert( entrada1.noches == [noche1] && entrada1.butacas == [butaca1] 
			 && entrada2.noches == [noche2] && entrada2.butacas == [butaca1])
		//Verifica en planificacion que en las noches solicitadas las butacas hayan sido vendidas
		assert(!planificacion.nochesConcierto.any{ it == noche1 && it.butacas.any{ it == butaca1 } })
		assert(!planificacion.nochesConcierto.any{ it == noche2 && it.butacas.any{ it == butaca1 } })
	}
		
}
