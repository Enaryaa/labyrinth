import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class ControleTaille implements MouseListener{
	/**
	 *
	 */
	private Bouton bouton;
	/**
	 *
	 */
	private int bout;
	/**
	 *
	 */
	private Fenetre cible;
	/**
	 *
	 */
	private JTextField text;
	/**
	 *
	 */
	private int taille;

	/**
	 *
	 * @param cible Fenetre
	 * @param text JTextField
	 */
	public ControleTaille(Fenetre cible, JTextField text){
		this.cible = cible;
		this.text = text;
		bout = 0;
	}

	/**
	 *
	 * @param e MouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 *
	 * @param e MouseEvent
	 */
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

	/**
	 *
	 * @return int
	 */
	public int getTaille(){
		return taille;
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