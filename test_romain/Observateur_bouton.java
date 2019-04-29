import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class Observateur_bouton implements MouseListener
{
	private Bouton bouton;
	private int id_bouton;
	private Fenetre fenetre;

	public Observateur_bouton(Fenetre f)
	{
		this.fenetre = f;
		this.id_bouton = 0;
	}

	public void mouseClicked(MouseEvent evenement){};	// un bouton cliqué
	public void mouseEntered(MouseEvent evenement){};	// debut du survol
	public void mouseExited(MouseEvent evenement){};	// fin du survol

	@Override
	public void mousePressed(MouseEvent evenement)	// un bouton appuyé
	{
		this.bouton = (Bouton) evenement.getComponent();
		this.id_bouton = bouton.getId();
		if(id_bouton == 0)
		{
			/*int taille = 0;
			fenetre.clearContent();
			// a ce moment, ajouter les etapes de choix pour arriver à la grille
			// Ci-après, le code pour lire le fichier contenant le format de la grille
			// Ce code est à placer au moment où l'utilisateur a choisi de prendre une grille préexistante avec le filechooser
			try
			{
				FileInputStream flux = new FileInputStream("petit.lab");
				DataInputStream data = new DataInputStream(flux);
				taille = data.readByte();
			}
			catch(IOException e)
			{
				System.err.println("Erreur de lecture");
			}
			Grille grille = new Grille(taille);*/

			byte taille = 0;
			byte[] thesee = new byte[2];
			byte[] sortie = new byte[2];
			fenetre.clearContent();
			// a ce moment, ajouter les etapes de choix pour arriver à la grille
			// Ci-après, le code pour lire le fichier contenant le format de la grille
			// Ce code est à placer au moment où l'utilisateur a choisi de prendre une grille préexistante avec le filechooser
			try
			{
				FileInputStream flux = new FileInputStream("petit.lab");
				DataInputStream data = new DataInputStream(flux);
				taille = data.readByte();
				thesee[0] = data.readByte();
				thesee[1] = data.readByte();
				sortie[0] = data.readByte();
				sortie[1] = data.readByte();
				String gf = data.read();
				System.out.println(gf);
			}
			catch(IOException e)
			{
				System.err.println("Erreur de lecture");
			}
			Grille grille = new Grille_existante(taille,thesee,sortie);


			fenetre.add(grille);
			fenetre.repaint();
			fenetre.setVisible(true);
		}
	}
	public void mouseReleased(MouseEvent evenement){};	// un bouton relâché
}