import javax.swing.*;
import java.awt.*;

public class Grille extends JPanel
{
	private int taille;
	private byte[] thesee;
	private byte[] sortie;
	private char[] etats;

	public Grille(int t)
	{
		super();
		this.taille = t;
		this.thesee = new byte[2];
		this.sortie = new byte[2];
		this.etats = new char[taille];
		
		GridLayout grille = new GridLayout(taille,taille);
		this.setLayout(grille);
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