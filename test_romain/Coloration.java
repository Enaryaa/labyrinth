import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Coloration implements MouseListener
{
	private char etat;
	private Case cell;
	private Grille grille;

	public Coloration(Grille g)
	{
		super();
		this.grille = g;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		this.cell = (Case) e.getComponent();
		if (cell.etatActuel()=='0')
		{
			cell.changerEtat('1');
			cell.setBackground(Color.BLACK);
			cell.repaint();
			grille.changerEtat(cell.indexCase(),'1');
		}

		else if (cell.etatActuel()=='1')
		{
			cell.changerEtat('0');
			cell.setBackground(Color.WHITE);
			cell.repaint();
			grille.changerEtat(cell.indexCase(),'0');
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