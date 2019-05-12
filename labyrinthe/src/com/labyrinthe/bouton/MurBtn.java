package com.labyrinthe.bouton;

import com.labyrinthe.element.Mur;

/**
 * la classe <code>MurBtn</code> permet d'appliquer au bouton Mur les propriétés
 *  de l'eleme qui lui correspond
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class MurBtn extends DefaultBtn {
    /**
     *Constructeur qui prend en paramètre une Action
     * @param a Action
     */
    public MurBtn(Action a){
        super(new Mur(), a);
        this.setText("Mur");
    }
}
