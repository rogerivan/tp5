package festivalRock_View

import dominio.Entrada

import java.awt.Color

import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.MainWindow
import org.uqbar.lacar.ui.model.Action

class VerEntradaWindow extends MainWindow<Entrada> {
	
	VerEntradaWindow(){
		
		super(new Entrada())
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
		new VerEntradaWindow().startApplication()
	}

}
