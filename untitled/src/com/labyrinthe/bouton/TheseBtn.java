package com.labyrinthe.bouton;

import com.labyrinthe.element.Thesee;

/**
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class TheseBtn extends DefaultBtn {
    /**
     *
      * @param a com.labyrinthe.bouton.Action
     */
    public TheseBtn(Action a){
        super(new Thesee(),a);
        this.setText("com.labyrinthe.element.Thesee");

    }
}
