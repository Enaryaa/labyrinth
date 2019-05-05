import javax.swing.filechooser.*;
import javax.swing.*;

public class Choix_fichier extends JFileChooser
{
	private String fichier;

	public Choix_fichier(String repertoire)
	{
		super();
		this.fichier = "";
		FileFilter filtre = (FileFilter) new Filtre_fichier();
		this.addChoosableFileFilter(filtre);
		this.setFileFilter(filtre);
	}
}