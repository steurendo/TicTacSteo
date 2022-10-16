import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Casella extends JComponent implements MouseListener
{
	private static final long serialVersionUID = 1L;
	private static final Color COLORE_COLORE_OVER = new Color(0, 0, 0, 50);
	private static final Color COLORE_COLORE_PRESSED = new Color(0, 0, 0, 80);
	private static final Color COLORE_COLORE_OCCUPATO = new Color(255, 0, 0, 70);
	
	private int valore;
	private Point coordinate;
	private boolean mouseOver;
	private boolean mousePressed;
	
	public Casella(Point coordinate)
	{
		valore = 0;
		this.coordinate = coordinate;
		mouseOver = false;
		mousePressed = false;
		addMouseListener(this);
	}
	public Casella() { this(new Point()); }
	
	public int getValore() { return valore; }
	public Point getCoordinate() { return coordinate; }
	
	public void setValore(int valore) { this.valore = valore; }

    public void addActionListener(ActionListener listener) { listenerList.add(ActionListener.class, listener); }
    public void removeActionListener(ActionListener listener) { listenerList.remove(ActionListener.class, listener); }
	private void fireActionPerformed()
	{
        ActionListener[] listeners;
        
        listeners = listenerList.getListeners(ActionListener.class);
        if (listeners != null)
			if (listeners.length > 0)
				listeners[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
    }
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e)
	{
		mouseOver = true;
		repaint();
	}
	public void mouseExited(MouseEvent e)
	{
		mouseOver = false;
		repaint();
	}
	public void mousePressed(MouseEvent e)
	{
		mousePressed = true;
		repaint();
	}
	public void mouseReleased(MouseEvent e)
	{
		mousePressed = false;
		if (mouseOver && valore == 0)
			fireActionPerformed();
		repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d;
		
		g2d = (Graphics2D)g;
		if (mousePressed && mouseOver)
			g.setColor(valore == 0 ? COLORE_COLORE_PRESSED : COLORE_COLORE_OCCUPATO);
		else if (mouseOver)
			g.setColor(COLORE_COLORE_OVER);
		else
			g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(Griglia.DIMENSIONE_BORDI));
		if (valore == 1)
			g.drawOval(getWidth() / 6, getHeight() / 6, getWidth() / 6 * 4, getHeight() / 6 * 4);
		else if (valore == -1)
		{
			g.drawLine(getWidth() / 6, getHeight() / 6, getWidth() / 6 * 5, getHeight() / 6 * 5);
			g.drawLine(getWidth() / 6, getHeight() / 6 * 5, getWidth() / 6 * 5, getHeight() / 6);
		}
	}
}