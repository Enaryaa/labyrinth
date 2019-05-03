import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Observateur_sauvegarde implements ActionListener
{
	private String nom;
	private JFrame fenetre;
	private Grille grille;

	public Observateur_sauvegarde(JFrame f, Grille g)
	{
		super();
		this.fenetre = f;
		this.grille = g;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.nom = e.getActionCommand();
		nom = nom + ".lab";
		try
		{
			FileOutputStream flux = new FileOutputStream(nom);
			DataOutputStream data = new DataOutputStream(flux);
			try
			{
				data.writeByte(grille.getTaille());
				data.writeByte(grille.getTheseeX());
				data.writeByte(grille.getTheseeY());
				data.writeByte(grille.getSortieX());
				data.writeByte(grille.getSortieY());
				String suite_etats = "";

				for (int i = 0 ; i < grille.getTaille() ; i++)
				{
					for (int j = 0 ; j < grille.getTaille() ; j++)
					{
						suite_etats = suite_etats + grille.getEtat(i+(4*j));
					}
					System.out.println(suite_etats);
				}
				try
				{
					short etats_short = (short) Integer.parseInt(suite_etats,2);
					System.out.println(etats_short);
					data.writeShort(etats_short);
				}
				catch(NumberFormatException exception)
				{
					System.err.println("Erreur de conversion");
				}			
			}
			catch(IOException exception)
			{
				System.err.println("Erreur d'écriture dans le fichier de sauvegarde");
			}
		}
		catch(IOException exception)
		{
			System.err.println("Erreur de lecture du fichier de generation de grille");
		}
		fenetre.setVisible(false);
	}
}