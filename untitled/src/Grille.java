import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Grille extends JPanel implements Action
{
	private int colonnes;
	private int lignes;
	private Element e;
	private boolean theseeLa = false;
	private boolean sortieLa = false;

	public Grille(int c, int l)
	{
		this.e = new Chemin();
		Fenetre f = new Fenetre(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT+200);
		FlowLayout div = new FlowLayout(FlowLayout.CENTER,0,0);
		f.setLayout(div);

		//Creation de la barre de menu
		menuBarre(f);

		this.colonnes = c;
		this.lignes = l;

		createGrid(f);
		actionBarre(f);
		jouer(f);

		f.setVisible(true);
	}

	private void jouer(Fenetre f) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT-100));

		Bouton deter = new Bouton(12);
		deter.setText("Deterministe");
		panel.add(deter);
		Bouton alea = new Bouton(13);
		alea.setText("Aleatoire");
		panel.add(alea);

		ChoixAlgo o = new ChoixAlgo(this, f);
		alea.addMouseListener(o);
		ChoixAlgo c = new ChoixAlgo(this, f);
		deter.addMouseListener(c);

		f.getContentPane().add(panel);
	}

	private void createGrid(Fenetre f) {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT));
		GridLayout grille = new GridLayout(colonnes,lignes);
		p.setLayout(grille);
		Coloration coloration = new Coloration(this);

		for (int i = 0 ; i < (colonnes*lignes) ; i++)
		{
			Cell cell = new Cell(i,coloration);
			p.add(cell);
		}
		f.getContentPane().add(p);
	}

	private void actionBarre(Fenetre f) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT-200));

		SortieBtn sortie = new SortieBtn(this);
		panel.add(sortie);
		TheseBtn thesee = new TheseBtn(this);
		panel.add(thesee);
		MurBtn mur = new MurBtn(this);
		panel.add(mur);
		ChemBtn chemin = new ChemBtn(this);
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

		save.addActionListener(new GestionMenu(f));
		erase.addActionListener(new GestionMenu(f));

		f.setJMenuBar(barre);
	}

	@Override
	public void saveElem(Element e) {

		this.e = e;
	}

	@Override
	public Element getElem(){

		return e;
	}

	@Override
	public void theseeLa(boolean bool) {

		theseeLa = bool;
		//permet de determiner si thesee est deja present sur la grille
	}

	@Override
	public boolean isTheseeLa() {

		return theseeLa;
	}

	@Override
	public void sortieLa(boolean bool) {

		sortieLa = bool;
	}

	@Override
	public boolean isSortieLa() {

		return sortieLa;
		//return si false (non present) ou  true (present)
	}
}