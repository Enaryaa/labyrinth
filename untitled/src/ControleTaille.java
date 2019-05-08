import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControleTaille implements MouseListener{

	private Bouton bouton;
	private int bout;
	private Fenetre cible;
	private JTextField text;
	private int taille;


	public ControleTaille(Fenetre cible, JTextField text){
		this.cible = cible;
		this.text = text;
		bout = 0;
	}

	public ControleTaille(){
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
			cible.dispose();
			taille = Integer.parseInt(text.getText());
			Grille grille = new Grille(taille,taille);
		}
		else if (bout == 10){
			cible.dispose();
			taille = Integer.parseInt(text.getText());
			GrilleRand grilleR = new GrilleRand(taille,taille);
		}
	}
	catch (NumberFormatException t){
		JOptionPane.showMessageDialog(cible, "Taille non valide", "Erreur", JOptionPane.WARNING_MESSAGE);
		}
	}

	public int getTaille(){
		return taille;
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