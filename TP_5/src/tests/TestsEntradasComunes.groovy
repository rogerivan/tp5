package tests

import static org.junit.Assert.*
import org.junit.Ignore
import org.junit.Before
import org.junit.Test
import dominio.*

class TestsEntradasComunes {

	def comprador
	
	def butaca1
	def butaca2
	def butacas = []
	def butacasAComprar = []
	def butacasAComprar2 = []
	
	def ubicacion1
	def ubicacion2
	
	def planificacion
	
	def noche1
	def noche2
	def nochesSeleccionadas = []
	def nochesSeleccionadas2 = []
	
	def fecha1
	def fecha2
	def fechas = []
	def fechas2 = []
	
	def banda
	def banda2
	def banda3
	def bandas = []
	def bandas2 = []

	def espectador1
	def espectador2
	def espectadores = []
	def espectadores2 = []

	@Before
	void Inicializar(){
		comprador = new Comprador("Testa", "Ferro")
		
		fecha1 = Date.parse( "yyyy-MM-dd", "2013-11-09" )
		fecha2 = Date.parse( "yyyy-MM-dd", "2013-11-11" )
		fechas = [ [fecha1] ]
		fechas2 = [ [fecha1], [fecha2] ]
		
		banda = new Banda("parkaBlanca", 3, 100)
		banda2 = new Banda("elPastorJudaz", 4, 200)
		banda3 = new Banda("laCura", 4, 200)
		bandas << banda
		bandas << banda2
		bandas2 << banda3
		
		ubicacion1 = new Ubicacion("azul", 2)
		ubicacion2 = new Ubicacion("rosa", 35)
		
		butaca1 = new Butaca(312, ubicacion1, null)
		butaca2 = new Butaca(324, ubicacion2, null)
		butacasAComprar = [[butaca1]]
		butacasAComprar2 = [[butaca1], [butaca1]]
		butacas = [[butaca1], [butaca2]]
		
		espectador1 = new Espectador("Jonas", "Castillo", 23, "Masculino")
		espectador2 = new Espectador("Diego", "Gonzalez", 25, "Masculino")
		espectadores = [espectador1]
		espectadores2 = [espectador1, espectador2]
		
		planificacion = new Planificacion()
		
		noche1 = new Noche()
		noche1.setFecha(fecha1)
		noche1.setButacas(butacas)
		noche1.setBandas(bandas)
		
		noche2 = new Noche()
		noche2.setFecha(fecha2)
		noche2.setButacas(butacas)
		noche2.setBandas(bandas2)
		
		planificacion.nochesConcierto << noche1
		planificacion.nochesConcierto << noche2
		
		nochesSeleccionadas = [ [noche1] ]
		nochesSeleccionadas2 = [ [noche1], [noche2] ]
	}
	
	/*
	 * Tests compra unitaria de entradas comunes
	 */
	
	//Test 1
	@Test
	void probarBuscarNochesPorFechaParaCompraUnitaria(){
		assert (comprador.buscarNochesPorFecha(fechas, planificacion) == nochesSeleccionadas)
	}
	
	//Test 2
	@Test
	void probarbuscarButacasDisponiblesParaCadaNocheParaCompraUnitaria(){
		assert ( comprador.buscarButacasDisponiblesParaCadaNoche(nochesSeleccionadas, planificacion) == butacasAComprar)
	}
	
	//Test 3
	@Test
	void probarCompraUnitaria(){
		comprador.comprarEntradas(nochesSeleccionadas, butacasAComprar, espectadores, planificacion)
		def compra = comprador.compras.first()
		assert (compra.entradasCompradas.size() == 1)
	}
	
	//Test 4 - Integrador tests 1, 2, 3
	@Test
	void probarBuscarButacasParaNochesParaCompraUnitaria(){
		comprador.buscarButacasParaNoches(fechas, espectadores, planificacion)
		def compra = comprador.compras.first()
		assert (compra.entradasCompradas.size() == 1)
	}
	
	/*
	 * Tests compra multiple de entradas comunes
	 */
	
	//Test 5
	@Test
	void probarBuscarNochesPorFechaParaCompraCompuesta(){
		assert (comprador.buscarNochesPorFecha(fechas2, planificacion) == nochesSeleccionadas2)
	}
	
	//Test 6
	@Test
	void probarbuscarButacasDisponiblesParaCadaNocheParaCompraCompuesta(){
		assert ( comprador.buscarButacasDisponiblesParaCadaNoche(nochesSeleccionadas2, planificacion) == butacasAComprar2)
	}
	
	//Test 7
	@Test
	void probarCompraCompuesta(){
		comprador.comprarEntradas(nochesSeleccionadas2, butacasAComprar2, espectadores2, planificacion)
		def compra = comprador.compras.first()
		assert (compra.entradasCompradas.size() == 2)
	}
	
	//Test 8 - Integrador tests 5, 6, 7
	@Test
	void probarBuscarButacasParaNochesParaCompraCompuesta(){
		comprador.buscarButacasParaNoches(fechas2, espectadores2, planificacion)
		def compra = comprador.compras.first()
		assert (compra.entradasCompradas.size() == 2)
	}

}
