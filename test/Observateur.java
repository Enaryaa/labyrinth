import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Observateur implements MouseListener{

	private Fenetre cible;


	public Observateur (Fenetre cible){
		this.cible = cible;
	


	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Bouton bout = (Bouton ) e.getComponent();
		//Bouton bout = bouton.getId();
		if (bout.getId().equals("1")){
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