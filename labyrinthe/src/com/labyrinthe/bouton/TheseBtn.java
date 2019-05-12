package com.labyrinthe.bouton;

import com.labyrinthe.element.Thesee;

/**
 * la classe <code>TheseBtn</code> permet d'appliquer au bouton Thesee les propriétés
 *  de l'eleme qui lui correspond
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class TheseBtn extends DefaultBtn {
    /**
     *Constructeur qui prend en paramètre une Action
      * @param a Action
     */
    public TheseBtn(Action a){
        super(new Thesee(),a);
        this.setText("Thesee");

    }
}
