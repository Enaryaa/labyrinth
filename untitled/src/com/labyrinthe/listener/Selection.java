package com.labyrinthe.listener;

import com.labyrinthe.bouton.DefaultBtn;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class Selection implements MouseListener {
    /**
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     *
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        final DefaultBtn defaultbtn = (DefaultBtn) e.getComponent();
        defaultbtn.setEleminAction();
    }

    /**
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
