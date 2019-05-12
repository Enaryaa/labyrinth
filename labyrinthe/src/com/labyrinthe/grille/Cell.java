package com.labyrinthe.grille;

import com.labyrinthe.algorithme.Position;
import com.labyrinthe.element.Chemin;
import com.labyrinthe.element.Element;
import com.labyrinthe.listener.Coloration;

import javax.swing.*;
import java.awt.*;

/**
 *La classe <code>Cell</code> permet de de créer les cellules dans la grille
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
	private boolean[] cardinal;

	/**
	 *Constructeur qui initie toutes les cellues a chemin et y ajoute le mouseListener
	 * ainsi que des positions
	 * @param index int
	 * @param coloration com.labyrinthe.listener.Coloration
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
		this.cardinal = new boolean[4];
		for (byte compteur = 0 ; compteur < 3 ; compteur++)
        {
        	this.cardinal[compteur] = false; // Tableau de directions Nord, Ouest, Sud, Est
        }
	}

	/**
	 *Constructeur qui permet de mettre l'élement passé en paramètre
	 * dans la cellule choisie
	 * @param index int
	 * @param elem Element
	 */
	public Cell (int index, Element elem){
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.index = index;
		this.setPropriete(elem);
		//permet de récupérer les propriétés des elements
		this.cardinal = new boolean[4];
		for (byte compteur = 0 ; compteur < 3 ; compteur++)
        {
        	this.cardinal[compteur] = false; // Tableau de directions Nord, Ouest, Sud, Est
        }
	}

	/**
	 *permet de get l'index
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 *met les propriétés de l'element dans la cellule, donc la couleur
	 * @param elem Element
	 */
	public void setPropriete(Element elem){
		this.elem = elem;
		this.setBackground(elem.getCouleur());
	}

	/**
	 *récupère les propriétés de la cellule
	 * @return
	 */
	public Element getPropriete(){
		return elem;
	}

	/**
	 *get la  position de la cellule
	 * @return position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 *set la position de la cellule
	 * @param position Position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 *
	 * @param i int
	 * @return boolean
	 */
	public boolean getCardinal(int i)
    {
    	return cardinal[i];
    }

	/**
	 *
	 * @param i int
	 * @param b boolean
	 */
    public void setCardinal(int i, boolean b)
    {

    	this.cardinal[i] = b;
    }
}
