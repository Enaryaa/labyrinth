import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.*;

public class Filtre_fichier extends FileFilter
{
	public Filtre_fichier()
	{
		super();
	}

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
   
    public String getDescription()
    {
        return "*.lab files";
    }
}