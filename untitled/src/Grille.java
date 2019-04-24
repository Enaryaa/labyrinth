import javax.swing.*;
import java.awt.*;

public class Grille extends JPanel
{
	private int colonnes;
	private int lignes;
	public Grille(int c, int l)
	{
		Fenetre f = new Fenetre();
		JPanel panel = new JPanel();

		//Creation de la barre de menu
		menubarre(f);

		this.colonnes = c;
		this.lignes = l;
		GridLayout grille = new GridLayout(colonnes,lignes);
		f.setLayout(grille);

		for (int i = 0 ; i < (colonnes*lignes) ; i++)
		{
			Cell cell = new Cell(i);
			f.add(cell);
		}

		JRadioButton sortie = new JRadioButton("Sortie");
		panel.add(sortie);
		JRadioButton thesee = new JRadioButton("Thesee");
		panel.add(thesee);
		JRadioButton mur = new JRadioButton("Mur");
		panel.add(mur);
		JRadioButton chemin = new JRadioButton("Chemin");
		panel.add(chemin);

		ButtonGroup choix = new ButtonGroup();

		choix.add(sortie);
		choix.add(thesee);
		choix.add(mur);
		choix.add(chemin);

		f.add(panel, BorderLayout.CENTER);

    	panel.setBackground(Color.CYAN);
    	f.getContentPane().add(panel);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(panel);
		f.setSize(500, 300);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
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

		f.add(barre, BorderLayout.NORTH);


	}
}