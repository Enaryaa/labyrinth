import javax.swing.*;
import java.awt.*;

public class Grille_manuelle extends Grille
{
	private int taille;
	private byte[] thesee;
	private byte[] sortie;
	private char[] etats;

	public Grille_manuelle(int t)
	{
		super(t);
		this.taille = t;
		for (char i = 0 ; i < (taille*taille) ; i++)
		{
			Case cell = new Case(i,'0',0,this);
			this.add(cell);
		}
	}

	public void changerEtat(char e, char c)
	{
		this.etats[e] = c;
	}
}