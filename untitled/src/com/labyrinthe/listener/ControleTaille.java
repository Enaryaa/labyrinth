package com.labyrinthe.listener;

import com.labyrinthe.bouton.Bouton;
import com.labyrinthe.Fenetre;
import com.labyrinthe.grille.Grille;
import com.labyrinthe.grille.GrilleRand;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class ControleTaille implements MouseListener{
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
	 */
	private JTextField text;
	/**
	 *
	 */
	private int taille;

	/**
	 *
	 * @param cible com.labyrinthe.Fenetre
	 * @param text JTextField
	 */
	public ControleTaille(Fenetre cible, JTextField text){
		this.cible = cible;
		this.text = text;
		bout = 0;
	}

	/**
	 *
	 * @param e MouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 *au clique de l'utilisateur sur le bouton générer,
	 * affiche la grille avec les tailles demandées
	 * sinon affiche une popup de warning
	 * @param e MouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		try{
		this.bouton = (Bouton ) e.getComponent();
		this.bout = bouton.getId();
		if (bout == 9){
			taille = Integer.parseInt(text.getText());
			if( taille >= 5 && taille <= 50) {
				new Grille(taille, taille);
				cible.dispose();
			}
			else {
				this.popup();
			}
		}
		else if (bout == 10){
			taille = Integer.parseInt(text.getText());
			if( taille >= 5 && taille <= 50) {
				new GrilleRand(taille, taille);
				cible.dispose();
			}
			else {
				this.popup();
			}
		}
	}
	catch (NumberFormatException t){
			this.popup();
		}
	}

	/**
	 * affiche la popup pour l'erreur
	 */
	private void popup(){
		JOptionPane.showMessageDialog(cible, "Taille non valide, minimum 5 et maximum 60",
				"Erreur", JOptionPane.WARNING_MESSAGE);

	}

	/**
	 *retourne la taille choisie par l'utilisateur
	 * @return int
	 */
	public int getTaille(){
		return taille;
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