import java.awt.*;
import javax.swing.*;

public class View extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private Model model;
	private Controller controller;
	private Griglia griglia;
	private Casella[][] caselle;
	
	public View()
	{
		model = new Model(0);
		controller = new Controller(this, model);
		
		setTitle("TicTacSteo");
		getContentPane().setPreferredSize(new Dimension(600, 600));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		
		griglia = new Griglia();
		griglia.setBounds(0, 0, 600, 600);
		
		caselle = new Casella[3][3];
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
			{
				caselle[x][y] = new Casella(new Point(x, y));
				caselle[x][y].setBounds(Griglia.DIMENSIONE_BORDI * (x + 1) + griglia.getDimensioneCaselle().width * x,
										Griglia.DIMENSIONE_BORDI * (y + 1) + griglia.getDimensioneCaselle().height * y,
										griglia.getDimensioneCaselle().width,
										griglia.getDimensioneCaselle().height);
				caselle[x][y].addActionListener(controller);
				add(caselle[x][y]);
			}
		add(griglia);
	}
	public void start()
	{
		add(new JLabel());
		setVisible(true);
	}
	public void update()
	{
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				caselle[x][y].setValore(model.getValoreCasella(new Point(x, y)));
		repaint();
	}
	public void comunicaRisultato()
	{
		String[] messaggi = { "Hai vinto!", "Hai perso...", "Pareggio." };
		
		JOptionPane.showMessageDialog(this, messaggi[model.getStato() - 2]);
	}
}