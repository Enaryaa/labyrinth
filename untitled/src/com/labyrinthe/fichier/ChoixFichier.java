package com.labyrinthe.fichier;

import javax.swing.filechooser.*;
import javax.swing.*;

/**
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class ChoixFichier extends JFileChooser
{
	/**
	 *
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