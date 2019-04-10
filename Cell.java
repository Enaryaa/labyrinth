import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Cell extends JPanel
{
	private int index;

	public Cell(int index)
	{
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.index = index;
		Observateur coloration = new Observateur(this);
		this.addMouseListener(coloration);
	}
}
