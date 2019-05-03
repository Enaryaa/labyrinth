import javax.swing.*;
import java.awt.*;

public class Case extends JPanel
{
	private Grille grille;
	private int index;
	private Color couleur;
	private char etat;
	private int particularité;
	private boolean thesee;
	private boolean sortie;

	public Case(int ind, char e, int p, Grille g)
	{
		super();
		this.grille = g;
		this.index = ind;
		this.etat = e;
		this.particularité = p;
		this.thesee = false;
		this.sortie = false;
		if (etat=='1')
		{
			this.setBackground(Color.BLACK);
		}
		else
		{
			if (particularité == 1)
			{
				this.thesee = true;
				this.setBackground(Color.RED);
			}
			else if (particularité == 2)
			{
				this.sortie = true;
				this.setBackground(Color.BLUE);
			}
			else
			{
				this.setBackground(Color.WHITE);
			}
		}
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Coloration coloration = new Coloration(grille);
		this.addMouseListener(coloration);
	}

	public void changerEtat(char e)
	{
		this.etat = e;
	}

	public char etatActuel()
	{
		return this.etat;
	}

	public int indexCase()
	{
		return this.index;
	}
}