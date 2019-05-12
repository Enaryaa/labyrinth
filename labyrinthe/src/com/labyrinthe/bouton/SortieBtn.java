package com.labyrinthe.bouton;

import com.labyrinthe.element.Sortie;

/**
 * la classe <code>SortieBtn</code> permet d'appliquer au bouton Sortie les propriétés
 *  *  de l'eleme qui lui correspond
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class SortieBtn extends DefaultBtn {
    /**
     *Constructeur qui prend en paramètre une Action
     * @param a Action
     */
    public SortieBtn(Action a){
        super(new Sortie(),a);
        this.setText("Sortie");
    }
}