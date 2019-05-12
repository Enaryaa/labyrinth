package com.labyrinthe.bouton;

import com.labyrinthe.element.Sortie;

/**
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class SortieBtn extends DefaultBtn {
    /**
     *
     * @param a com.labyrinthe.bouton.Action
     */
    public SortieBtn(Action a){
        super(new Sortie(),a);
        this.setText("com.labyrinthe.element.Sortie");
    }
}