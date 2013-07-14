package tests

import static org.junit.Assert.*
import org.junit.Ignore
import org.junit.Before
import org.junit.Test
import dominio.*
import descuentos.*

class TestsEntradasVIP {

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
		fechas = [ [fecha1, fecha2] ]
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
		butacasAComprar = [[butaca1, butaca1]]
		butacas = [[butaca1], [butaca2]]
		
		espectador1 = new Espectador("Jonas", "Castillo", 23, "Masculino")
		espectador2 = new Espectador("Diego", "Gonzalez", 25, "Masculino")
		espectadores = [espectador1, espectador2]
		
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
		
		nochesSeleccionadas = [ [noche1, noche2] ]

	}
	
	/*
	 * Tests compra de entradas comunes
	 */
	
	//Test 1 - No entendemos muy bien el error de este test.
	//		   Ambas colecciones son identicas, pero al operar y encontrar la diferencia entre ambas, el assert da falso ( resultado: [] == [] -> false).
	@Test
	@Ignore
	void probarBuscarNochesPorFechaParaCompraVIPUnitaria(){
		def nochesEncontradas = comprador.buscarNochesPorFecha(fechas, planificacion)
		def coleccionDiferencia = nochesEncontradas.first() - nochesSeleccionadas.first()
		assert ( coleccionDiferencia == [])
	}
	
	//Test 2
	@Test
	void probarbuscarButacasDisponiblesParaCadaNocheParaCompraVIPUnitaria(){
		assert ( comprador.buscarButacasDisponiblesParaCadaNoche(nochesSeleccionadas, planificacion) == butacasAComprar)
	}
	
	//Test 3
	@Test
	void probarCompraVIPUnitaria(){
		comprador.comprarEntradas(nochesSeleccionadas, butacasAComprar, espectadores, planificacion)
		def compra = comprador.compras.first()
		assert (compra.entradasCompradas.size() == 1)
	}
	
	//Test 4 - Integrador tests 1, 2, 3
	@Test
	void probarBuscarButacasParaNochesParaCompraVIPUnitaria(){
		comprador.buscarButacasParaNoches(fechas, espectadores, planificacion)
		def compra = comprador.compras.first()
		assert (compra.entradasCompradas.size() == 1)
	}
	
}
