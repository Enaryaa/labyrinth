import javax.swing.*;
import java.awt.*;

public class Grille extends JPanel
{
	private int colonnes;
	private int lignes;
	public Grille(int c, int l)
	{
		super();
		this.colonnes = c;
		this.lignes = l;
		GridLayout grille = new GridLayout(colonnes,lignes);
		this.setLayout(grille);
		for (int i = 0 ; i < (colonnes*lignes) ; i++)
		{
			Cell cell = new Cell(i);
			this.add(cell);
		}
	}
}