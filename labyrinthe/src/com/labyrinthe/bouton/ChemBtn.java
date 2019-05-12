package com.labyrinthe.bouton;

import com.labyrinthe.element.Chemin;

/**
 * la classe <code>ChemBtn</code> permet d'appliquer au bouton Chemin les propriétés
 * de l'element, ici chemin
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class ChemBtn extends DefaultBtn {
    /**
     *Constructeur qui prend en paramètre une Action
     * @param a Action
     */
    public ChemBtn(Action a){

        super(new Chemin(),a);
        this.setText("com.labyrinthe.element.Chemin");
    }
}