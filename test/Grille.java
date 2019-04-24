import javax.swing.*;
import java.awt.*;

public class Grille extends JPanel
{
	private int colonnes;
	private int lignes;
	public Grille(int c, int l)
	{	
		Fenetre f = new Fenetre(); 

		GridLayout gestionnaire = new GridLayout(3, 1);
    	this.setLayout(gestionnaire);
		
		//Creation de la barre de menu
		menubarre(f);


		this.colonnes = c;
		this.lignes = l;
		GridLayout grille = new GridLayout(colonnes,lignes);
		this.setLayout(grille);

		for (int i = 0 ; i < (colonnes*lignes) ; i++)
		{
			Cell cell = new Cell(i);
			this.add(cell);
		}

		JPanel panel = new JPanel();
		JRadioButton sortie = new JRadioButton("Sortie");
		panel.add(sortie);
		f.add(panel, BorderLayout.SOUTH);

    	panel.setBackground(Color.CYAN);
    	f.getContentPane().add(panel);
	}

	public void menubarre(Fenetre f){

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

		f.add(barre, BorderLayout.NORTH);


	}
}