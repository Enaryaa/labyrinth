package com.labyrinthe.bouton;

import javax.swing.*;

/**
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class Bouton extends JButton{
	/**
	 *Identifiant du bouton
	 */
	private int id;

	/**
	 *Constructeur qui rend public
	 * @param id int
	 */
	public Bouton(int id){

		this.id = id;
	}

	/**
	 *getter pour récupérer l'identifiant du bouton
	 * @return int
	 */
	public int getId(){

		return id;
	}
}