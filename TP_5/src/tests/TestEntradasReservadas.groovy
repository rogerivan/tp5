package tests;

import static org.junit.Assert.*;
import dominio.*
import org.junit.Ignore
import org.junit.Before
import org.junit.Test

class TestEntradasReservadas {

	def comprador
	
	def espectador1
	def espectador2
	def espectadores = []
	
	def butacasReservadas = []
	def butacas = []
	def butaca1
	def butaca2
	def butaca3
	
	def ubicacion1
	def ubicacion2
	
	def contrasenia
	
	def planificacion
	
	def noches = []
	def noche
	
	def fecha1
	def fecha2
	
	def banda1
	def banda2
	def bandas = []
	
	@Before
	void inicializar(){
		comprador = new Comprador('Testa','Ferro')
		
		espectador1 = new Espectador ('Jonas', 'Castillo', 23, 'Masculino')
		espectador2 = new Espectador ('Julieta', 'Gonzalez', 22, 'Femenino')
		espectadores = [espectador1, espectador2]
		
		ubicacion1 = new Ubicacion("azul", 5)
		ubicacion2 = new Ubicacion("amarillo", 50)
		
		butaca1 = new Butaca(23, ubicacion1, null)
		butaca2 = new Butaca(87, ubicacion2, null)
		butaca3 = new Butaca(9999, ubicacion1, null)
		butacas = [butaca1, butaca2]
		
		banda1 = new Banda("Radiohead", 4, 200)
		banda2 = new Banda("StereoFoot", 3, 100)
		bandas = [banda1, banda2]
		
		noche = new Noche(fecha1, butacas, bandas)
		noches << noche
		
		planificacion = new Planificacion()
		planificacion.nochesConcierto = noches
		
		contrasenia = "lsk988"
	}
	
	@Test
	void testReservarButaca(){
		noche.reservarButaca(butaca1, contrasenia)
		//Verifica que la nueva butaca reservada tenga contraseña y sea la correcta
		assert (butaca1.getContrasenia() == contrasenia)
		//Verifica que la butaca no este mas con las butacas sin reservar
		assert(!noche.butacas.any{it == butaca1})
		//Verfica que la butaca este con las butacas reservadas
		assert(noche.butacasReservadas.any{it == butaca1})
	}
	
	@Test
	void testDesbloquearButaca(){
		noche.reservarButaca(butaca1, contrasenia)
		noche.desbloquearButaca(butaca1, contrasenia)		
		//Verifica que la butaca este con las no reservadas
		assert(noche.butacas.any{it == butaca1})
		//Verifica que la butaca no este mas con las reservadas
		assert(!noche.butacasReservadas.any{it == butaca1})
	}
	
	@Test
	void buscarButacasReservadas(){
		noche.reservarButaca(butaca1, contrasenia)
		assert(noche.buscarButacaReservada(butaca1, contrasenia))
	}
	
	@Test
	void testReservarButacaPlanificacion(){
		planificacion.reservarButacas(noche, butacas, contrasenia)
		//Verifica que se reservaron las dos butacas
		assert (butaca1.getContrasenia() == contrasenia)
		assert (butaca2.getContrasenia() == contrasenia)
		assert(noche.butacasReservadas.any{it == butaca1})
		assert(noche.butacasReservadas.any{it == butaca2})
	}
	
	@Test
	void comprarButacasReservadas(){
		planificacion.reservarButacas(noche, butacas, contrasenia)
		planificacion.comprarEntradasReservadas(noche, butacas, espectadores, contrasenia, comprador)
		assert (comprador.compras.any{it.entradasEspeciales.size() ==  2})
	}
}
