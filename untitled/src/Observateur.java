import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Observateur implements MouseListener{

	private Bouton bouton;
	private int bout;
	private Fenetre cible;

	public Observateur (Fenetre cible){
		this.cible = cible;
		bout = 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.bouton = (Bouton ) e.getComponent();
		this.bout = bouton.getId();
		if (bout == 1){
			cible.clearContent();
			cible.menu2();
			cible.visible();
		}
		if (bout == 2){
			cible.clearContent();
			Grille_chargee grille_chargee = new Grille_chargee();
			cible.add(grille_chargee);
			cible.visible();
		}
		if (bout == 3){
			cible.clearContent();
			cible.saisi();
			cible.visible();
		}
		if (bout == 4){
			cible.clearContent();
			cible.saisiRand();
			cible.visible();
		}
		if (bout == 7 || bout == 8){
			//mode manuel || auto
			cible.clearContent();
			cible.visible();
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