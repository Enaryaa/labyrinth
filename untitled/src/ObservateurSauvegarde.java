import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ObservateurSauvegarde implements ActionListener
{
	public static final int BITS_PER_BYTE = 8;
	private String nom;
	private JFrame fenetre;
	private GrilleInterface grille;

	public ObservateurSauvegarde(JFrame f, GrilleInterface g)
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
				byte etats_byte = 0;

				for (int i = 0 ; i < grille.getTaille() ; i++)
				{
					for (int j = 0 ; j < grille.getTaille() ; j++)
					{
						suite_etats = suite_etats + grille.getEtat(i+(grille.getTaille()*j));
						if (suite_etats.length()==BITS_PER_BYTE)
						{
							try
							{
								etats_byte = (byte) Integer.parseInt(suite_etats,2);
								data.writeByte(etats_byte);
							}
							catch(NumberFormatException exception)
							{
								System.err.println("Erreur de conversion");
							}
							suite_etats ="";
						}
						else if(i == (grille.getTaille()-1) && j == (grille.getTaille()-1))
						{
							try
							{
								while(suite_etats.length()!=BITS_PER_BYTE)
								{
									suite_etats = suite_etats + "0";
								}
								etats_byte = (byte) Integer.parseInt(suite_etats,2);
								data.writeByte(etats_byte);
							}
							catch(NumberFormatException exception)
							{
								System.err.println("Erreur de conversion");
							}
							suite_etats ="";
						}
					}
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