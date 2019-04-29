import javax.swing.*;
import java.awt.*;

public class Grille extends JPanel
{
	private int taille;

	public Grille(int t)
	{
		super();
		this.taille = t;
		GridLayout grille = new GridLayout(taille,taille);
		this.setLayout(grille);
	}
}