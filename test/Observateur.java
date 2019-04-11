import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Observateur implements MouseListener{

	private Fenetre cible;
	private int bout;
	private Bouton bouton;

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
			System.out.println("bouton 1");
			cible.clearContent();
			cible.menu2();
			cible.visible();
		}
		if (bout == 2){
			System.out.println("bouton 2");
			cible.clearContent();
			cible.visible();
		}
		if (bout == 3){
			cible.clearContent();
			this.cible.setLayout(new BorderLayout());
			Grille grille = new Grille(8,8);
			this.cible.add(grille,BorderLayout.CENTER);
			cible.visible();

		}
		if (bout == 4){
			cible.clearContent();
			cible.menu3();
			cible.visible();
		}
		if (bout == 5 || bout == 6){
			cible.clearContent();
			cible.menu4();
			cible.visible();
		}
		if (bout == 7 || bout == 8){
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