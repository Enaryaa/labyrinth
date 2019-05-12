package com.labyrinthe.listener;

import com.labyrinthe.bouton.Bouton;
import com.labyrinthe.Fenetre;
import com.labyrinthe.grille.GrilleChargee;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * la classe <code>Observateur</code> contient un MouseListener qui permet
 * de gérer les boutons des menus de selection des grilles
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class Observateur implements MouseListener{
	/**
	 *
	 */
	private Bouton bouton;
	/**
	 *
	 */
	private int bout;
	/**
	 *
	 */
	private Fenetre cible;

	/**
	 *
	 * @param cible com.labyrinthe.Fenetre
	 */
	public Observateur (Fenetre cible){
		this.cible = cible;
		bout = 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 *
	 * @param e MouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		this.bouton = (Bouton ) e.getComponent();
		this.bout = bouton.getId();
		if (bout == 1){
			cible.clearContent();
			cible.menu2();
			cible.visible();
		}
		if (bout == 2){
			cible.dispose();
			GrilleChargee grille_chargee = new GrilleChargee();
		}
		if (bout == 3){
			cible.clearContent();
			cible.saisi();
			cible.visible();
		}
		if (bout == 4){
			cible.clearContent();
			cible.saisiRand();
			cible.visible();
		}

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