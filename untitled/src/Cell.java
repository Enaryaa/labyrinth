import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel
{
	private int index;
	private Element elem;
	private Position position;
	private boolean[] cardinal;

	public Cell(int index, Coloration coloration)
	{
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.index = index;
		this.addMouseListener(coloration);
		setPropriete(new Chemin());
		this.position = new Position(0,0);
		//met element par defaut à chemin
		this.cardinal = new boolean[4];
		for (byte compteur = 0 ; compteur < 4 ; compteur++)
        {
        	this.cardinal[compteur] = false; // Tableau de directions Nord, Ouest, Sud, Est
        }
	}

	public Cell (int index, Element elem){
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.index = index;
		this.setPropriete(elem);
		//permet de récupérer les propriétés des elements
	}

	public int getIndex() {
		return index;
	}

	public void setPropriete(Element elem){
		this.elem = elem;
		this.setBackground(elem.getCouleur());
	}

	public Element getPropriete(){
		return elem;
		//associé à la cellule
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean getCardinal(int i)
    {
    	return cardinal[i];
    }

    public void setCardinal(int i, boolean b)
    {
    	cardinal[i] = b;
    }
}
