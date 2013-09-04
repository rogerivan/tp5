package tests;

import static org.junit.Assert.*
import dominio.*
import org.junit.Ignore
import org.junit.Before
import org.junit.Test

class TestsDeBusqueda {
	
	def butacasSinReservar = []
	def butacas = []
	def butaca1
	def butaca2
	def butaca3
	
	def ubicacion1
	def ubicacion2
	
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
		ubicacion1 = new Ubicacion("azul", 5)
		ubicacion2 = new Ubicacion("amarillo", 50)
		butaca1 = new Butaca(23, ubicacion1, null)
		butaca2 = new Butaca(87, ubicacion2, null)
		butaca3 = new Butaca(9999, ubicacion1, null)
		banda1 = new Banda("Radiohead", 4, 200)
		banda2 = new Banda("StereoFoot", 3, 100)
		bandas << banda1
		bandas << banda2
		butacas = [butaca1, butaca2]
		fecha1 = Date.parse( "yyyy-MM-dd", "2013-11-09" )
		noche1 = new Noche(fecha1, butacas, bandas)
		fecha2 = Date.parse( "yyyy-MM-dd", "2013-11-10" )
		noche2 = new Noche(fecha2, butacas, bandas)
		planificacion = new Planificacion()
		noches << noche1
		noches << noche2
		planificacion.nochesConcierto = noches
	}
	
	/*
	 * Tests metodos basicos de busqueda
	 */
	
	@Test
	void probarMetodoDeDisponibilidadDeButacas(){
		assert( noche1.esButacaDisponible( butaca1 ) )
	}
	
	@Test
	void probarMetodoDeBuscarButacaEnTodasLasNoches(){
		assert( planificacion.buscarButacaEnTodasLasNoches( butaca1 ) )
	}
	
	@Test
	void probarMetodoDeVerificarDisponibilidadButacasSegunNoches(){
		assert( planificacion.verificarDisponibilidadButacas( [noche1], [butaca1] ) )
	}
	
	
}
