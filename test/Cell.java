import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel
{
	private int index;
	private Color couleur;
	public Cell(int index)
	{
		super();
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.index = index;
		Coloration coloration = new Coloration();
		this.addMouseListener(coloration);
	}
}
