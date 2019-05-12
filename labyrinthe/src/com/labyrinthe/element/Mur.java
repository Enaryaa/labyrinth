package com.labyrinthe.element;

import java.awt.*;

/**
 * la classe <code>Mur</code> est un élement que l'on applique
 *   dans la grille, elle a une couleur comme propriété
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class Mur extends Element{
    /**
     *Constructeur attribut une couleur a l'element
     */
    public Mur() {
        this.couleur = Color.BLACK;
    }
}
