package com.labyrinthe.element;

import java.awt.*;

/**
 * la classe <code>Chemin</code> est un élement que l'on applique
 * ensuite dans la grille, elle a une couleur comme propriété
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class Chemin extends Element {
    /**
     *Constructeur attribut une couleur a l'element
     */
    public Chemin() {
        this.couleur = Color.WHITE;
    }
}
