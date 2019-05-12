package com.labyrinthe.bouton;

import javax.swing.*;

/**
 * La classe <code>Bouton</code> hérite de JButton afin de pouvoir mettre un id
 * sur chaque bouton
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class Bouton extends JButton{
	/**
	 *Identifiant du bouton
	 */
	private int id;

	/**
	 *Constructeur qui rend public l'id
	 * @param id int
	 */
	public Bouton(int id){

		this.id = id;
	}

	/**
	 *permet de récupérer l'identifiant du bouton
	 * @return int
	 */
	public int getId(){

		return id;
	}
}