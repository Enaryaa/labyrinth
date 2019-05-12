package com.labyrinthe.bouton;

import com.labyrinthe.element.Mur;

/**
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class MurBtn extends DefaultBtn {
    /**
     *
     * @param a com.labyrinthe.bouton.Action
     */
    public MurBtn(Action a){
        super(new Mur(), a);
        this.setText("com.labyrinthe.element.Mur");
    }
}
