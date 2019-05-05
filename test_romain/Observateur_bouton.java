import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class Observateur_bouton implements MouseListener
{
	public static final int BITS_PER_BYTE = 8;
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
			int etats_int = 0;
			byte etats_byte = 0;
			int etats_unsigned = 0;
			fenetre.clearContent();
			// a ce moment, ajouter les etapes de choix pour arriver à la grille
			// Ci-après, le code pour lire le fichier contenant le format de la grille
			// Ce code est à placer au moment où l'utilisateur a choisi de prendre une grille préexistante avec le filechooser
			try
			{
				FileInputStream flux = new FileInputStream("1.lab");
				DataInputStream data = new DataInputStream(flux);
				taille = data.readByte();
				char[] etats = new char[taille*taille];
				thesee[0] = data.readByte();
				thesee[1] = data.readByte();
				sortie[0] = data.readByte();
				sortie[1] = data.readByte();

				String etats_string = "";
				while (data.available()!=0)
				{
					etats_byte = data.readByte();
					etats_int = etats_byte & 0xFF;
					String etats_string_tmp = Integer.toBinaryString(etats_int);
					while (etats_string_tmp.length() != BITS_PER_BYTE)
					{
						etats_string_tmp = "0" + etats_string_tmp;
					}
					etats_string = etats_string + etats_string_tmp;
				}
				etats = etats_string.toCharArray();
				char tmp = 0;
				int j = 0;
				for (int i = 0 ; i < taille ; i++)
				{
					j = i;
					while(j < taille)
					{
						tmp = etats[(i*taille)+j];
						etats[(i*taille)+j] = etats[(i+(j*taille))];
						etats[(i+(j*taille))] = tmp;
						j++;
					}
				}
				Grille grille = new Grille_existante(taille,thesee,sortie,etats);
				grille.setPreferredSize(new Dimension(fenetre.getWidth()-100,fenetre.getHeight()-100-30));
				fenetre.add(grille);
			/*Barre_action barre = new Barre_action(fenetre);
			fenetre.add(barre);*/
			Bouton bouton_sauvegarde = new Bouton(19);
			Observateur_bouton_sauvegarde sauvegarde = new Observateur_bouton_sauvegarde(fenetre,grille);
			bouton_sauvegarde.addMouseListener(sauvegarde);
			bouton_sauvegarde.setPreferredSize(new Dimension(fenetre.getWidth()-100,100));
			fenetre.add(bouton_sauvegarde);
			fenetre.repaint();
			fenetre.setVisible(true);
		}
		catch(IOException e)
		{
			System.err.println("Erreur de lecture");
		}

	}
}
	public void mouseReleased(MouseEvent evenement){};	// un bouton relâché
}