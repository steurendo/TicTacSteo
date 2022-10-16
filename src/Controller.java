import java.awt.event.*;

public class Controller implements ActionListener
{
	private View view;
	private Model model;
	
	public Controller(View view, Model model)
	{
		this.view = view;
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Casella src;
		
		src = (Casella)e.getSource();
		model.mossaGiocatore(src.getCoordinate());
		view.update();
		if (model.getStato() == 2 || model.getStato() == 4)
		{
			view.comunicaRisultato();
			model.reset();
		}
		else
		{
			model.mossaPC();
			view.update();
			if (model.getStato() == 3 || model.getStato() == 4)
			{
				view.comunicaRisultato();
				model.reset();
			}
		}
		view.update();
	}
}