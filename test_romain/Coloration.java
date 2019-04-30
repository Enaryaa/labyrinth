import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Coloration implements MouseListener
{
	private Color couleur;
	private Case cell;

	public Coloration()
	{
		super();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		this.cell = (Case) e.getComponent();
		this.couleur = this.cell.getBackground();
		if(this.couleur.equals(Color.WHITE))
		{
			cell.setBackground(Color.BLACK);
			this.cell.repaint();
		}

		else if(this.couleur.equals(Color.BLACK))
		{
			cell.setBackground(Color.WHITE);
			this.cell.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}