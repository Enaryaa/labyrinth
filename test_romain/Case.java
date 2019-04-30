import javax.swing.*;
import java.awt.*;

public class Case extends JPanel
{
	private int index;
	private Color couleur;
	private char etat;
	public Case(int index, char e)
	{
		super();
		this.index = index;
		this.etat = e;
		if (etat=='1')
		{
			this.setBackground(Color.BLACK);
		}
		else
		{
			this.setBackground(Color.WHITE);
		}
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Coloration coloration = new Coloration();
		this.addMouseListener(coloration);
	}
}