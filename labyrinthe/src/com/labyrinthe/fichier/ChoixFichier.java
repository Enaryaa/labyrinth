package com.labyrinthe.fichier;

import javax.swing.filechooser.*;
import javax.swing.*;

/**
 * La classe ChoixFichier permet à l'utilisateur
 * de sélectionner le fichier qu'il désire ouvrir
 * pour charger une grille
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class ChoixFichier extends JFileChooser
{
	/**
	 *composante fichier
	 */
	private String fichier;

	/**
	 *
	 * @param repertoire String
	 */
	public ChoixFichier(String repertoire)
	{
		super();
		this.fichier = "";
		FileFilter filtre = (FileFilter) new FiltreFichier();
		this.addChoosableFileFilter(filtre);
		this.setFileFilter(filtre);
	}
}