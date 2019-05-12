package com.labyrinthe.bouton;

import com.labyrinthe.element.Chemin;

/**
 * la classe <code>com.labyrinthe.bouton.ChemBtn</code> permet d'appliquer au bouton com.labyrinthe.element.Chemin les propriétés
 * de l'element, ici chemin
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class ChemBtn extends DefaultBtn {
    /**
     *Constructeur qui prend en paramètre une com.labyrinthe.bouton.Action
     * @param a com.labyrinthe.bouton.Action
     */
    public ChemBtn(Action a){

        super(new Chemin(),a);
        this.setText("com.labyrinthe.element.Chemin");
    }
}