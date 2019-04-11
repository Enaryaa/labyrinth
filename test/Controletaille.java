import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controletaille implements MouseListener{

	private Bouton bouton;
	private int bout;
	private Fenetre cible;
	private JTextField text;
	private int taille;


	public Controletaille (Fenetre cible, JTextField text){
		this.cible = cible;
		this.text = text;
		bout = 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		try{
		this.bouton = (Bouton ) e.getComponent();
		this.bout = bouton.getId();
		if (bout == 9){
			cible.clearContent();
			taille = Integer.parseInt(text.getText());
			System.out.println(taille);
			cible.visible();
		}
	}
	catch (NumberFormatException t){
		System.err.println("Taille non valide, veuillez reessayer");
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