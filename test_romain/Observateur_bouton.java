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
		FlowLayout affichage = new FlowLayout(FlowLayout.CENTER,0,0);
		fenetre.setLayout(affichage);
		this.bouton = (Bouton) evenement.getComponent();
		this.id_bouton = bouton.getId();
		if(id_bouton == 0)
		{
			byte taille = 0;
			byte[] thesee = new byte[2];
			byte[] sortie = new byte[2];
			char[] etats = new char[16];
			int etats_int = 0;
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
				etats_int = (int) data.readShort();
				String etats_string = Integer.toBinaryString(etats_int);
				while (etats_string.length() != 16)
				{
					etats_string = "0" + etats_string;
				}
				etats = etats_string.toCharArray();
			}
			catch(IOException e)
			{
				System.err.println("Erreur de lecture");
			}
			Grille grille = new Grille_existante(taille,thesee,sortie,etats);
			grille.setPreferredSize(new Dimension(fenetre.getWidth()-100,fenetre.getHeight()-100-30));
			fenetre.add(grille);
			/*Barre_action barre = new Barre_action(fenetre);
			fenetre.add(barre);*/
			Bouton coucou = new Bouton(19);
			Observateur_bouton sauvegarde = new Observateur_bouton(fenetre);
			coucou.addMouseListener(sauvegarde);
			coucou.setPreferredSize(new Dimension(fenetre.getWidth()-100,100));
			fenetre.add(coucou);
			fenetre.repaint();
			fenetre.setVisible(true);
		}
		if (id_bouton == 19)
		{
			JFrame fenetre_sauvegarde = new JFrame();
			fenetre_sauvegarde.setSize(fenetre.getWidth()-50, fenetre.getHeight()-200);
			fenetre_sauvegarde.setLocationRelativeTo(fenetre);
			FlowLayout gestionnaire = new FlowLayout(FlowLayout.CENTER,0,0);
			fenetre_sauvegarde.setLayout(gestionnaire);
			JLabel choix = new JLabel("Nom du fichier de sauvegarde :");
			choix.setPreferredSize(new Dimension(fenetre_sauvegarde.getWidth()-20,(fenetre_sauvegarde.getHeight()-34)/2));
			JTextField champ = new JTextField();
			champ.setPreferredSize(new Dimension(fenetre_sauvegarde.getWidth()-20,(fenetre_sauvegarde.getHeight()-34)/2));
			Observateur_sauvegarde sauvegarde = new Observateur_sauvegarde(fenetre_sauvegarde);
			champ.addActionListener(sauvegarde);
			fenetre_sauvegarde.add(choix);
			fenetre_sauvegarde.add(champ);
			fenetre_sauvegarde.setVisible(true);
		}
	}
	public void mouseReleased(MouseEvent evenement){};	// un bouton relâché
}