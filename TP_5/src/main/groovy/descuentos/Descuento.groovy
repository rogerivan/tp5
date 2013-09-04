package descuentos


abstract class Descuento {
	
	//TODO agregar en planficacion y noche los metodos y atributos necesarios para poder calcular
	//el porcentaje de entradas compradas por clientes de sexo femenino.

	//FIXME este método se llama getDescuento, pero en realidad no devuelve nada,
	//sino que tiene un efecto. Lo cual no está mal, pero el nombre del método
	//no es representativo, porque diera a entender que va a retornar ese valor.
	//aplicarDescuento podría ser un mejor nombre
	
	//Este hace de Template Method para que cada subclase implemente cumpleCondicion y regsitrarDescuento
	def aplicarDescuento(compra){
		compra.entradasCompradas.each{
			if (this.cumpleCondicion(it, compra))
				this.registrarDescuento(it, compra)
		}
	}
	
	abstract cumpleCondicion(def entrada, def compra)
	
	abstract registrarDescuento(def entrada, def compra)

}
