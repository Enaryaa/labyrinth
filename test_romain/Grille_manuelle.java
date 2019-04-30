import javax.swing.*;
import java.awt.*;

public class Grille_manuelle extends Grille
{
	private int taille;

	public Grille_manuelle(int t)
	{
		super(t);
		this.taille = t;
		for (int i = 0 ; i < (taille*taille) ; i++)
		{
			Case cell = new Case(i,'0');
			this.add(cell);
		}
	}
}