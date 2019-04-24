import javax.swing.*;
import java.awt.*;

public class Grille extends JPanel
{
	private int colonnes;
	private int lignes;


	public Grille(int c, int l)
	{
		Fenetre f = new Fenetre(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT+100);
		FlowLayout div = new FlowLayout(FlowLayout.CENTER,0,0);
		f.setLayout(div);

		//Creation de la barre de menu
		menuBarre(f);

		this.colonnes = c;
		this.lignes = l;

		createGrid(f);
		actionBarre(f);

		f.setVisible(true);
	}

	private void createGrid(Fenetre f) {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT));
		GridLayout grille = new GridLayout(colonnes,lignes);
		p.setLayout(grille);

		for (int i = 0 ; i < (colonnes*lignes) ; i++)
		{
			Cell cell = new Cell(i);
			p.add(cell);
		}
		f.getContentPane().add(p);
	}

	private void actionBarre(Fenetre f) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT-200));

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

		panel.setBackground(Color.PINK);
		f.getContentPane().add(panel);
	}

	private void menuBarre(Fenetre f){
		JMenuBar barre = new JMenuBar();

		//Creation onglet fichier
		JMenu fichier = new JMenu("Fichier");
		barre.add(fichier);
		//contient une sauvegarde et erase
		JMenuItem save = new JMenuItem("Sauvegarder");
		fichier.add(save);
		JMenuItem erase = new JMenuItem("Effacer");
		fichier.add(erase);

		f.setJMenuBar(barre);
	}
}