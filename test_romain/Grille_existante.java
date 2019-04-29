import javax.swing.*;
import java.awt.*;

public class Grille_existante extends Grille
{
	private int taille;
	private byte[] thesee;
	private byte[] sortie;

	public Grille_existante(int t, byte[] th, byte[] s)
	{
		super(t);
		this.taille = t;
		this.thesee = th;
		this.sortie = s;
		for (int i = 0 ; i < taille ; i++)
		{
			for (int j = 0 ; j < taille ; j++)
			{
				//System.out.println(i);
				Case cell = new Case(i);
				if (i == thesee[0] && j == thesee[1])
				{
					// Mettre Thesee dans le panneau
				}
				else if (i == sortie[0] && j == sortie[1])
				{
					// Mettre la Sortie dans le panneau
				}
				else{}

				this.add(cell);
			}
		}
	}
}