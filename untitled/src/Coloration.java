import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class Coloration implements MouseListener
{
	/**
	 *
	 */
	private Action action;

	/**
	 *Constructeur  qui prend une Action en paramètre
	 * @param action Action
	 */
	public Coloration(Action action)
	{
		this.action = action;
	}

	/**
	 *
	 * @param e MouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 *Lorsque le bouton est selectionné, on récupère l'element du bouton
	 * puis l'applique dans la cellule.
	 * on test aussi si la sortie et Thésée sont uniques
	 * @param e MouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		final Cell cell = (Cell) e.getComponent();
		final Element elem = this.action.getElem();
		//recupere propriete du bouton

		final Element propcell = cell.getPropriete();
		//recup propriete de la cellule

		if (elem instanceof Sortie) {
			//permet de savoir si elem est une instance de sortie (qui hérite de la classe element

			if (this.action.isSortieLa()) {
				return;
				//si false
			} else {
				this.action.sortieLa(true);
				//sinon le met a vrai et permet d'obtenir une seule sortie (idem thesee)
			}
		}
		if (elem instanceof Thesee){
			if (this.action.isTheseeLa()){
				return;
				//si false
			}
			else{
				this.action.theseeLa(true);
				//sinon met a true
			}
		}
		if (propcell instanceof Thesee){
			this.action.theseeLa(false);
		}
		if (propcell instanceof Sortie){
			this.action.sortieLa(false);
		}
		cell.setPropriete(elem);


	}

	/**
	 *
	 * @param e MouseEvent
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

	}

	/**
	 *
	 * @param e MouseEvent
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	/**
	 *
	 * @param e MouseEvent
	 */
	@Override
	public void mouseExited(MouseEvent e) {

	}
}