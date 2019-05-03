import javax.swing.*;
import java.awt.*;

public class Grille_existante extends Grille
{
	private int taille;
	private byte[] thesee;
	private byte[] sortie;
	private char[] etats;

	public Grille_existante(int t, byte[] th, byte[] s, char[] e)
	{
		super(t);
		this.taille = t;
		this.thesee = th;
		this.sortie = s;
		this.etats = e;
		char compteur = 0;

		for (int i = 0 ; i < taille ; i++)
		{
			for (int j = 0 ; j < taille ; j++ , compteur++)
			{
				if ((j == thesee[1]) && i == thesee[0])
				{
					Case cell = new Case(compteur,etats[j+(i*4)],1,this);
					this.add(cell);
				}
				else if ((j == sortie[1]) && i == sortie[0])
				{
					Case cell = new Case(compteur,etats[j+(i*4)],2,this);
					this.add(cell);
				}
				else
				{
					Case cell = new Case(compteur,etats[j+(i*4)],0,this);
					this.add(cell);
				}
			}
		}
	}

	public int getTaille()
	{
		return taille;
	}

	public byte getTheseeX()
	{
		return thesee[0];
	}

	public byte getTheseeY()
	{
		return thesee[1];
	}

	public byte getSortieX()
	{
		return sortie[0];
	}

	public byte getSortieY()
	{
		return sortie[1];
	}

	public char getEtat(int i)
	{
		return etats[i];
	}

	public void changerEtat(char e, char c)
	{
		this.etats[e] = c;
	}
}