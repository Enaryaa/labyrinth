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
     * Constructeur de la classe FiltreFichier
     */
	public FiltreFichier()
	{
		super();
	}

    /**
     *
     * @param f File
     * @return boolean
     */
	public boolean accept(File f)
	{
        if (f.isDirectory())
        {
            return true;
        }
        String extension = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1)
        {
            extension = s.substring(i+1).toLowerCase();
        }
        if (extension != null)
        {
            if (extension.equals("lab"))
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
     *
     * @return String
     */
    public String getDescription()
    {
        return "*.lab files";
    }
}