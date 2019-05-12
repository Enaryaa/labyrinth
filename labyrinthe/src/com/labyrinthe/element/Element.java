package com.labyrinthe.element;

import java.awt.*;

/**
 * la classe absraite <code>Element</code> permet de faire hériter aux autres
 * elements la couleur
 */
public abstract class Element {
    /**
     * composante qui définie la couleur par defaut a gris.
     */
    protected Color couleur = Color.darkGray;

    /**
     * permet de récupérer la couleur
     * @return Color
     */
    public Color getCouleur() {
        return couleur;
    }
}
