import javax.swing.*;
import java.awt.*;

public class Grille extends JPanel implements Action, GrilleInterface
{
	public static final int DETERMINISTE = 1;
	public static final int ALEA = 2;
	public static final int MANUEL = 3;
	public static final int AUTO = 4;
	private String algo;
	private String methode;
	private int colonnes;
	private int lignes;
	private Element e;
	private boolean theseeLa = false;
	private boolean sortieLa = false;



	public Grille(int c, int l)
	{
		this.e = new Chemin();
		Fenetre f = new Fenetre(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT+300);
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
		panel.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT-50));

		JRadioButton deter = new JRadioButton(Choix.DETER);
		panel.add(deter);
		JRadioButton aleat = new JRadioButton(Choix.ALEA);
		panel.add(aleat);

		JPanel panel2 = new JPanel();
		ButtonGroup choixalgo = new ButtonGroup();

		choixalgo.add(deter);
		choixalgo.add(aleat);

		JRadioButton manuel = new JRadioButton(Choix.MANUEL);
		panel2.add(manuel);
		JRadioButton auto = new JRadioButton(Choix.AUTO);
		panel2.add(auto);

		ButtonGroup choixjeu = new ButtonGroup();

		choixjeu.add(auto);
		choixjeu.add(manuel);

		JPanel panel3 = new JPanel();
		Bouton jouer = new Bouton(15);
		jouer.setText("Démarrer");
		panel3.add(jouer);
		ValidationChoix validationChoix = new ValidationChoix(this);
		jouer.addMouseListener(validationChoix);

		panel.add(panel2);
		panel.add(panel3);
		f.getContentPane().add(panel);
	}

	public void createGrid(Fenetre f) {
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
		panel.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT-250));

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

	@Override
	public int getTaille(){
		return this.colonnes;
	}

	@Override
	public String getAlgo() {
		return algo;
	}

	@Override
	public String getMethode() {
		return methode;
	}

	@Override
	public void setAlgo(String algo) {
		this.algo = algo;
	}

	@Override
	public void setMethode(String methode) {
		this.methode = methode;
	}
}