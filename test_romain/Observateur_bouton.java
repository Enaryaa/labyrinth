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
			char[] etats = new char[16];
			int etats_int = 0;
			int etats_unsigned = 0;
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
				/*etats_int = (int) data.readShort();
				String etats_string_tmp = Integer.toBinaryString(etats_int);
				etats_unsigned = Integer.parseUnsignedInt(etats_string_tmp);
				String etats_string = Integer.toBinaryString(etats_unsigned);
				while (etats_string.length() != (taille*taille))
				{
					etats_string = "0" + etats_string;
				}
				etats = etats_string.toCharArray();
				char tmp = 0;
				int j = 0;
				for (int i = 0 ; i < taille ; i++)
				{
					j = i;
					for ( ; j < taille ; j++)
					{
						tmp = etats[(i*4)+j];
						etats[(i*4)+j] = etats[(i+(j*4))];
						etats[(i+(j*4))] = tmp;
						System.out.println(etats[i+j]);
					}
				}*/
				String etats_string = "";
				while (data.available()!=0)
				{
					etats_int = getUnsignedInt(data.readByte());
					System.out.println("Etats int : " + etats_int);
					String etats_string_tmp = Integer.toString(etats_int);
					System.out.println("Etats string tmp : " + etats_string_tmp);
					etats_unsigned = Integer.parseUnsignedInt(etats_string_tmp);
					System.out.println("Etats unsigned : " + etats_unsigned);
					etats_string = Integer.toBinaryString(etats_unsigned);
					System.out.println("Etats string : " + etats_string);
					while (etats_string.length() != BITS_PER_BYTE)
					{
						etats_string = "0" + etats_string;
					}
					System.out.println(etats_string);
				}
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
			Bouton bouton_sauvegarde = new Bouton(19);
			Observateur_bouton_sauvegarde sauvegarde = new Observateur_bouton_sauvegarde(fenetre,grille);
			bouton_sauvegarde.addMouseListener(sauvegarde);
			bouton_sauvegarde.setPreferredSize(new Dimension(fenetre.getWidth()-100,100));
			fenetre.add(bouton_sauvegarde);
			fenetre.repaint();
			fenetre.setVisible(true);
		}
	}
	public void mouseReleased(MouseEvent evenement){};	// un bouton relâché

	public static long getUnsignedInt(int x)
	{
    	return x & 0x00000000ffffffffL;
	}
}