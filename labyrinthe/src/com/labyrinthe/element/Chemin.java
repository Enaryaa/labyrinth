package com.labyrinthe.element;

import java.awt.*;

/**
 * la classe <code>com.labyrinthe.element.Chemin</code> est un élement que l'on applique
 * ensuite dans la grille, elle a une couleur comme propriété
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class Chemin extends Element {
    /**
     *Constructeur qui met la couleur a blanc dans la grille
     */
    public Chemin() {
        this.couleur = Color.WHITE;
    }
}
