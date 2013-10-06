package festivalRock_View

import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.MainWindow
import org.uqbar.lacar.ui.model.Action

import dominio.Planificacion

class BuscadorWindow extends MainWindow<Planificacion>{
		
	BuscadorWindow(){
		
		super(new Planificacion());
	}
	
	@Override
	public void createContents(Panel mainPanel) {
		mainPanel.with {
			title = "Buscador de Butacas"
			layout = new HorizontalLayout()
			this.addActions(mainPanel)
		}
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
				.setCaption("Buscar Vip")
				.onClick({this.vip()} as Action)
				

		new Button(actionsPanel) //
				.setCaption("Buscar Normal")
				.onClick({ this.vip() } as Action)

		new Button(actionsPanel)//
				.setCaption("Comprar Reservadas")
				.onClick({ this.vip() } as Action)
	}

	def vip(){
		this.openDialog( new BuscarButacaVipWindow(this))
		
	}
	
	static void main(String[] args) {
		new BuscadorWindow().startApplication()
	}

}

