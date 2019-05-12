package com.labyrinthe.fichier;

import javax.swing.filechooser.FileFilter;
import java.io.*;

/**
 * La classe FiltreFichier sert à choisir les 
 * fichiers qui seront visible dans le JFileChooser
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class FiltreFichier extends FileFilter
{
    /**
     * Extension que doit comporter le fichier
     */
    public static final String MOTIF = "lab";

    /**
     * Constructeur de la classe FiltreFichier
     */
	public FiltreFichier()
	{
		super();
	}

    /**
     * La méthode accept renvoie un booléen
     * pour dire si un fichier est accepté 
     * ou non
     * @param f File
     * @return boolean
     */
	public boolean accept(File f)
	{
        if (f.isDirectory())
        {
            return true;
        }
        /**
         * extension est la suite de caractère suivant
         * le dernier point
         */
        String extension = null;

        /**
         * s est une chaîne de caractère intermédiaire
         * déstinée à stocker le nom du fichier
         * concerné
         */
        String s = f.getName();

        /**
         * i est un entier qui désigne la dernière
         * itération d'un point dans le nom du fichier
         */
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1)
        {
            /**
            * on soustraie au nom du fichier l'extension
            * qui est la partie du nom après le dernier point
            */
            extension = s.substring(i+1).toLowerCase();
        }
        /**
        * on vérifie que le fichier comporte une extension
        * et qu'elle est conforme au motif recherché
        */
        if (extension != null)
        {
            if (extension.equals(MOTIF))
            {
                    return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    /**
     * Renvoie la description du nom de fichier attendu
     * @return String
     */
    public String getDescription()
    {
        return "*.lab files";
    }
}