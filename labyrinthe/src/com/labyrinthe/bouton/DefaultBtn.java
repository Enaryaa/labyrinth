package com.labyrinthe.bouton;

import com.labyrinthe.element.Element;
import com.labyrinthe.listener.Selection;

import javax.swing.*;

/**
 * la classe <code>DefaultBtn</code> hérite de JRadioButton afin de l'adapter pour permettre
 * la création de la grille vide en lui attribuant un element et une action
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class DefaultBtn extends JRadioButton {
    private Element elem;
    private Action action;

    /**
     *Constructeur destiné à la création des variables publiques
     * @param elem Element
     * @param action Action
     */
    public DefaultBtn(Element elem, Action action){
        this.elem = elem;
        this.action = action;
        this.addMouseListener(new Selection());
    }

    /**
     *Stock l'element du bouton cliqué dans l'action, qui permet de mettre
     * les bons parametres apres
     */
    public void setEleminAction(){
        this.action.saveElem(this.elem);
    }
}
