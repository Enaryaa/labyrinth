import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class Observateur_bouton_sauvegarde implements MouseListener
{
	private Bouton bouton;
	private int id_bouton;
	private Fenetre fenetre;
	private Grille grille;

	public Observateur_bouton_sauvegarde(Fenetre f, Grille g)
	{
		this.fenetre = f;
		this.id_bouton = 0;
		this.grille = g;
	}

	public void mouseClicked(MouseEvent evenement){};	// un bouton cliqué
	public void mouseEntered(MouseEvent evenement){};	// debut du survol
	public void mouseExited(MouseEvent evenement){};	// fin du survol
	public void mouseReleased(MouseEvent evenement){};	// un bouton relâché

	@Override
	public void mousePressed(MouseEvent evenement)	// un bouton appuyé
	{
		this.bouton = (Bouton) evenement.getComponent();
		this.id_bouton = bouton.getId();
		JFrame fenetre_sauvegarde = new JFrame();
		fenetre_sauvegarde.setSize(fenetre.getWidth()-50, fenetre.getHeight()-200);
		fenetre_sauvegarde.setLocationRelativeTo(fenetre);
		FlowLayout gestionnaire = new FlowLayout(FlowLayout.CENTER,0,0);
		fenetre_sauvegarde.setLayout(gestionnaire);
		JLabel choix = new JLabel("Nom du fichier de sauvegarde :");
		choix.setPreferredSize(new Dimension(fenetre_sauvegarde.getWidth()-20,(fenetre_sauvegarde.getHeight()-34)/2));
		JTextField champ = new JTextField();
		champ.setPreferredSize(new Dimension(fenetre_sauvegarde.getWidth()-20,(fenetre_sauvegarde.getHeight()-34)/2));
		Observateur_sauvegarde sauvegarde = new Observateur_sauvegarde(fenetre_sauvegarde,grille);
		champ.addActionListener(sauvegarde);
		fenetre_sauvegarde.add(choix);
		fenetre_sauvegarde.add(champ);
		fenetre_sauvegarde.setVisible(true);
	}
}