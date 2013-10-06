package festivalRock_View

import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

import dominio.Planificacion

class BuscarButacaVipWindow  extends SimpleWindow<Planificacion>{
	
	BuscarButacaVipWindow(WindowOwner owner) {
		super(owner, new Planificacion())
	}

	@Override
	public void createContents(Panel mainPanel) {
		// TODO Auto-generated method stub
		mainPanel.with {
			title = "Buscador de Butacas Vip"
			layout = new HorizontalLayout()
			new Button(mainPanel)
			.setCaption("Volver")
			.onClick({this.close()} as Action)
		}
		
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		// TODO Auto-generated method stub
		
	}

}
