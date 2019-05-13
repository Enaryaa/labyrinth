package com.labyrinthe.listener;

import com.labyrinthe.bouton.DefaultBtn;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * la classe <code>Selection</code> permet de récupérer le bouton qui hérite
 * de la classe de DefaultBtn
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class Selection implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     *récupère le bouton correspondant et y applique son élément rattaché
     * dans la grille
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        final DefaultBtn defaultbtn = (DefaultBtn) e.getComponent();
        defaultbtn.setEleminAction();
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
