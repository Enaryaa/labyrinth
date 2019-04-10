import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Observateur implements MouseListener
{
	private Cell panneau;
	public Observateur(Cell p)
	{
		super();
		this.panneau = p;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if (this.panneau.getBackground().equals(Color.WHITE))
		{
			return;
		} 
		else
		{
			this.panneau.setBackground(Color.BLACK);
		}
		this.panneau.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		 
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}
}