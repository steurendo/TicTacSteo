import java.awt.*;
import javax.swing.*;

public class Griglia extends JComponent
{
	private static final long serialVersionUID = 1L;
	private static final Color COLORE_BORDI = Color.BLACK;
	public static final int DIMENSIONE_BORDI = 5;
	
	public void paintComponent(Graphics g)
	{
		g.setColor(COLORE_BORDI);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(getBackground());
		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				g.fillRect(DIMENSIONE_BORDI * (x + 1) + getDimensioneCaselle().width * x,
						   DIMENSIONE_BORDI * (y + 1) + getDimensioneCaselle().height * y,
						   getDimensioneCaselle().width,
						   getDimensioneCaselle().height);
	}
	
	public Dimension getDimensioneCaselle()
	{
		Dimension dimensione;
		
		dimensione = new Dimension(0, 0);
		dimensione.width = (getWidth() - DIMENSIONE_BORDI * 4) / 3;
		dimensione.height = (getHeight() - DIMENSIONE_BORDI * 4) / 3;
		
		return dimensione;
	}
}