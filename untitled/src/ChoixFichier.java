import javax.swing.filechooser.*;
import javax.swing.*;

public class ChoixFichier extends JFileChooser
{
	private String fichier;

	public ChoixFichier(String repertoire)
	{
		super();
		this.fichier = "";
		FileFilter filtre = (FileFilter) new FiltreFichier();
		this.addChoosableFileFilter(filtre);
		this.setFileFilter(filtre);
	}
}