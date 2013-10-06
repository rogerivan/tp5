package festivalRock_View

import java.awt.Color

import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.MainWindow

import dominio.Banda
import dominio.Butaca
import dominio.Comprador
import dominio.Entrada
import dominio.Espectador
import dominio.Noche
import dominio.Ubicacion

class VerEntradaWindow extends MainWindow<Entrada> {
		
	VerEntradaWindow(def entrada){
		
		super(entrada)
	}
	
	@Override
	public void createContents(Panel mainPanel) {
		mainPanel.with { 
			title = "Datos de la Entrada"
			layout = new VerticalLayout()
			new Label(it).with {  
				background = Color.CYAN 
				text = "-----Comprador-----"  
			}
			new Label(it).with {
				
				bindValueToProperty("comprador")
			}
			new Label(it).with { 
				background = Color.CYAN
				text = "-----Espectador-----"  
			}
			new Label(it).with {
				
				bindValueToProperty("espectador")
			}
			new Label(it).with {
				background = Color.CYAN
				text = "---------Noche---------"  
			}
			new Label(it).with {
				
				bindValueToProperty("noches")
			}
			new Label(it).with { 
				background = Color.CYAN 
				text = "-----Butaca (n°-sector-fila)-----"  
			}
			new Label(it).with {
				
				bindValueToProperty("butacas")
			}
		}
		
	}
	
	static void main(String[] args) {
		// ubicacion y banda1 son para jarcodear una entrada y poder setear sus atributos
		def comprador = new Comprador('Testa','Ferro')
		def espectador  = new Espectador ('Jonas', 'Castillo', 23, 'Masculino')
		def ubicacion = new Ubicacion("azul", 5)
		def butacas = new Butaca(23, ubicacion, null)
		def banda1 = new Banda("Radiohead", 4, 200)
		def noches = new Noche( '13/09/2013', butacas, banda1)
		//jarcodeo una noche, butaca, comprador y espectador para mostrar el resultado de la entrada por pantalla
		def entrada = new Entrada(comprador, espectador, ubicacion, butacas, banda1, noches)
		new VerEntradaWindow(entrada).startApplication()
	}

}
