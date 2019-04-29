import javax.swing.*;
import java.awt.*;

public class Case extends JPanel
{
	private int index;
	private Color couleur;
	public Case(int index)
	{
		super();
		this.index = index;
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Coloration coloration = new Coloration();
		this.addMouseListener(coloration);
	}
}