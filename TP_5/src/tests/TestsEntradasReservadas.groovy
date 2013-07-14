package tests;

import static org.junit.Assert.*;
import org.junit.Ignore;
import dominio.Banda
import dominio.Butaca
import dominio.Comprador
import dominio.Espectador
import dominio.Noche
import dominio.Planificacion
import dominio.Ubicacion
import org.junit.Before
import org.junit.Test

class TestsEntradasReservadas {

	def comprador
	def fecha
	def butacas = []
	def butaca1
	def butaca2
	def ubicacion1
	def ubicacion2
	def contrasenia
	def planificacion
	def noches = []
	def noche1
	def noche2
	def fecha2
	def fecha3
	def fecha4
	def fecha5
	def fechasVIP = []
	def fechasComun = []
	def fechas = []
	def banda
	def bandas = []
	def butacasSinReservar = []
	def espectador1
	def espectador2
	def espectadores = []

	@Before
	void Inicializar(){
		comprador = new Comprador("Juan", "Perez")
		fecha = new Date()
		fecha2 = new Date()
		fecha3 = new Date()
		fecha4 = Date.parse( "yyyy-MM-dd", "2013-11-15" )
		fecha5 = new Date()
		fechasVIP = [fecha, fecha2, fecha3,fecha4, fecha5]
		fechasComun = [fecha4]
		fechas = [fechasComun]
		banda = new Banda("Oasis", 0, 0)
		bandas << banda
		ubicacion1 = new Ubicacion("azul", 2)
		ubicacion2 = new Ubicacion("rosa", 35)
		butaca1 = new Butaca(312, ubicacion1, null)
		butaca2 = new Butaca(324, ubicacion2, null)
		butacasSinReservar = [butaca1, butaca2]
		butacas = [butaca1, butaca2]
		contrasenia = "11pp11"
		espectador1 = new Espectador("Juan", "Perez", 30, "Masculino")
		espectador2 = new Espectador("Laura", "Garcia", 25, "Femenino")
		espectadores = [espectador1, espectador2]
		planificacion = new Planificacion()
		noche1 = new Noche()
		noche1.setFecha(fecha)
		noche1.setButacas(butacasSinReservar)
		noche1.setBandas(bandas)
		noche2 = new Noche()
		noches = [noche1]
		planificacion.setNochesConcierto(noches)
	}
	
	/*
	 * Tests entradas reservadas 
	 */

	@Test
	void reservarButaca(){
		comprador.reservarButacas(fecha, butacas, contrasenia, planificacion)
		assert (butaca1.getContrasenia() == contrasenia)
		assert (noche1.butacasReservadas.size() == 2)
	}

	@Test
	void generarButacaReservada(){
		comprador.reservarButacas(fecha, butacas, contrasenia, planificacion)
		comprador.buscarButacasReservadas(fecha, butacas, contrasenia, espectadores, planificacion)
		def compra = comprador.compras.first()
		assert (compra.getEntradasEspeciales().size() == 2)
	}

	@Test
	void butacasReservadasDentroDeBuscarButacasReservadasEnComprador(){
		comprador.reservarButacas(fecha, butacas, contrasenia, planificacion)
		def butacasReservadas = []
		butacas.each{
			butacasReservadas.push(planificacion.buscarButacaReservada(noche1, it, contrasenia))
		}
		assert (butacasReservadas.any{butaca1})
	}

}
