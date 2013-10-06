package festivalRock_View

import dominio.Planificacion
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class BuscarButacaVipWindow  extends Dialog<Planificacion>{
	
	BuscarButacaVipWindow(WindowOwner owner) {
		super(owner, new Planificacion())
	}

	@Override
	public void createContents(Panel mainPanel) {
		// TODO Auto-generated method stub
		mainPanel.with {
			title = "Buscador de Butacas Vip"
			layout = new HorizontalLayout()
		}
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		// TODO Auto-generated method stub
		
	}
}
