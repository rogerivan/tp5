package descuentos


abstract class Descuento {
	
	//TODO agregar en planficacion y noche los metodos y atributos necesarios para poder calcular
	//el porcentaje de entradas compradas por clientes de sexo femenino.

	//FIXME este método se llama getDescuento, pero en realidad no devuelve nada,
	//sino que tiene un efecto. Lo cual no está mal, pero el nombre del método
	//no es representativo, porque diera a entender que va a retornar ese valor.
	//aplicarDescuento podría ser un mejor nombre
	abstract aplicarDescuento(compra)

}
