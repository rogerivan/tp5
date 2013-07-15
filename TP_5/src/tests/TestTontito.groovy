package tests;

import static org.junit.Assert.*
import org.junit.Ignore
import org.junit.Before
import org.junit.Test

class TestTontito {

	def col = []
		
	@Before
	void Inicializar(){
		col.push([2])
		col.push([3])
		col.push([4])
		col.push([5,5,6])
	}
	
	@Test
	void pila(){
		assert(col.pop() == [5,5,6])
	}
		
}
