import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class Cell extends JPanel
{
	/**
	 *l'index permet de numéroté les celulles dans la grille
	 */
	private int index;
	/**
	 *Un element
	 */
	private Element elem;
	/**
	 *une position
	 */
	private Position position;

	/**
	 *Constructeur qui initie toutes les cellues a chemin et y ajoute le mouseListener
	 * ainsi que des positions
	 * @param index int
	 * @param coloration Coloration
	 */
	public Cell(int index, Coloration coloration)
	{
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.index = index;
		this.addMouseListener(coloration);
		setPropriete(new Chemin());
		this.position = new Position(0,0);
		//met element par defaut à chemin
	}

	/**
	 *C
	 * @param index int
	 * @param elem Element
	 */
	public Cell (int index, Element elem){
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.index = index;
		this.setPropriete(elem);
		//permet de récupérer les propriétés des elements
	}

	/**
	 *
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 *
	 * @param elem Element
	 */
	public void setPropriete(Element elem){
		this.elem = elem;
		this.setBackground(elem.getCouleur());
	}

	/**
	 *
	 * @return
	 */
	public Element getPropriete(){
		return elem;
		//associé à la cellule
	}

	/**
	 *
	 * @return position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 *
	 * @param position Position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
}
