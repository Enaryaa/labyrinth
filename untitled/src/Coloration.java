import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Coloration implements MouseListener
{
	private Action action;

	public Coloration(Action action)
	{
		this.action = action;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		final Cell cell = (Cell) e.getComponent();
		final Element elem = this.action.getElem();
		if (elem instanceof Sortie) {
			if (this.action.isSortieLa()) {
				return;
				//si il y est deja, ça fait rien (quitte fonction)
			} else {
				this.action.sortieLa(true);
			}
		}
		if (elem instanceof Thesee){
			if (this.action.isTheseeLa()){
				return;
			}
			else{
				this.action.theseeLa(true);
			}
		}
		cell.setBackground(elem.getCouleur());


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