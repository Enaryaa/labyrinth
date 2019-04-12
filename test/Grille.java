import javax.swing.*;
import java.awt.*;

public class Grille extends JPanel
{
	private int colonnes;
	private int lignes;
	public Grille(int c, int l)
	{
		super();

		//Creation de la barre de menu
		JMenuBar barre = new JMenuBar();

		//Creation onglet fichier
		JMenu fichier = new JMenu("Fichier");
		barre.add(fichier);
		//contient une sauvegarde et erase
		JMenuItem save = new JMenuItem("Sauvegarder");
		fichier.add(save);
		JMenuItem erase = new JMenuItem("Effacer");
		fichier.add(erase);

		//onglet element
		JMenu element = new JMenu("Ajouter unelements");
		barre.add(element);

		JMenuItem these = new JMenuItem("Thesee");
		element.add(these);
		JMenuItem sortie = new JMenuItem("Sortie");
		element.add(sortie);
		JMenuItem mur = new JMenuItem("Mur");
		element.add(mur);
		
		this.colonnes = c;
		this.lignes = l;
		GridLayout grille = new GridLayout(colonnes,lignes);
		this.setLayout(grille);
		this.add(barre, BorderLayout.NORTH);
		for (int i = 0 ; i < (colonnes*lignes) ; i++)
		{
			Cell cell = new Cell(i);
			this.add(cell);
		}
	}
}