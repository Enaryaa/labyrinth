import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel
{
	private int index;
	private Element elem;

	public Cell(int index, Coloration coloration)
	{
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.index = index;
		this.addMouseListener(coloration);
		setPropriete(new Chemin());
		//met element par defaut à chemin
	}

	public Cell (int index, Element elem){
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.index = index;
		this.setPropriete(elem);
		//permet de récupérer les propriétés des elements
	}

	public void setPropriete(Element elem){
		this.elem = elem;
		this.setBackground(elem.getCouleur());
	}

	public Element getPropriete(){
		return elem;
		//associé à la cellule
	}
}
