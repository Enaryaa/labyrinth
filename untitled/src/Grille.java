import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grille extends JPanel implements Action, GrilleInterface
{
	public static final int DETERMINISTE = 1;
	public static final int ALEA = 2;
	public static final int MANUEL = 3;
	public static final int AUTO = 4;
	private Fenetre f;
	private String algo;
	private String methode;
	private int colonnes;
	private int lignes;
	private Element e;
	private boolean theseeLa = false;
	private boolean sortieLa = false;
	private JPanel grille;

	private List<Cell> cells;

	public Grille(int c, int l)
	{
		this.e = new Chemin();
		f = new Fenetre(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT+300);
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
		Choix choix = new Choix(this);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT-100));

		JRadioButton deter = new JRadioButton(Choix.DETER);
		deter.addActionListener(choix);
		panel.add(deter);
		JRadioButton aleat = new JRadioButton(Choix.ALEA);
		aleat.addActionListener(choix);
		panel.add(aleat);

		JPanel panel2 = new JPanel();
		ButtonGroup choixalgo = new ButtonGroup();

		choixalgo.add(deter);
		choixalgo.add(aleat);

		JRadioButton manuel = new JRadioButton(Choix.MANUEL);
		manuel.addActionListener(choix);
		panel2.add(manuel);
		JRadioButton auto = new JRadioButton(Choix.AUTO);
		auto.addActionListener(choix);
		panel2.add(auto);

		ButtonGroup choixjeu = new ButtonGroup();

		choixjeu.add(auto);
		choixjeu.add(manuel);

		JPanel panel3 = new JPanel();
		Bouton jouer = new Bouton(15);
		jouer.setText("Démarrer");
		jouer.addMouseListener(new ValidationChoix(this));
		panel3.add(jouer);

		JPanel panel4 = new JPanel();
		Bouton deplacer = new Bouton(16);
		deplacer.setText("Déplacer");
		deplacer.addMouseListener(new ValidationChoix(this));
		panel4.add(deplacer);

		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		f.getContentPane().add(panel);
	}

	public void createGrid(Fenetre f) {
		cells = new ArrayList<>();
		this.grille = new JPanel();
		grille.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT));
		GridLayout gestionnaire = new GridLayout(colonnes,lignes);
		grille.setLayout(gestionnaire);
		Coloration coloration = new Coloration(this);

		for (int i = 0 ; i < (colonnes*lignes) ; i++)
		{
			Cell cell = new Cell(i,coloration);
			cells.add(cell);
			grille.add(cell);
		}
		f.getContentPane().add(grille);
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

		save.addActionListener(new GestionMenu(this ));
		erase.addActionListener(new GestionMenu(this));

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

	@Override
	public void cacherFenetre() {
		f.dispose();
	}

	@Override
	public List<Cell> getCells() {
		return cells;
	}

	@Override
    public byte getTheseeX()
    {
        byte theseex = 0;
        /*for (int i = 0; i < colonnes; i++)
        {
            for (int j = 0; j < lignes; j++)
            {
                int current = grille[i][j];
                if(current == THESEE)
                {
                    theseex = (byte) i;
                }
            }
        }*/
        return theseex;
    }

    @Override
    public byte getTheseeY()
    {
        byte theseey = 0;
        /*for (int i = 0; i < colonnes; i++)
        {
            for (int j = 0; j < lignes; j++)
            {
                int current = grille[i][j];
                if(current == THESEE)
                {
                    theseey = (byte) j;
                }
            }
        }*/
        return theseey;
    }

    @Override
    public byte getSortieX()
    {
        byte sortiex = 0;
        /*for (int i = 0; i < colonnes; i++)
        {
            for (int j = 0; j < lignes; j++)
            {
                int current = grille[i][j];
                if(current == SORTIE)
                {
                    sortiex = (byte) i;
                }
            }
        }*/
        return sortiex;
    }

    @Override
    public byte getSortieY()
    {
        byte sortiey = 0;
        /*for (int i = 0; i < colonnes; i++)
        {
            for (int j = 0; j < lignes; j++)
            {
                int current = grille[i][j];
                if(current == SORTIE)
                {
                    sortiey = (byte) j;
                }
            }
        }*/
        return sortiey;
    }
	@Override
    public char getEtat(int in)
    {
        int index = in;
        int compteur = 0;
        char etat = '0';
        /*for (int i = 0; i < colonnes; i++)
        {
            for (int j = 0; j < lignes; j++)
            {
                int current = grille[i][j];
                if (current == MUR && index == compteur)
                {
                    etat = '1';
                }
                else if (current != MUR && index == compteur)
                {
                    etat = '0';
                }
                compteur++;
            }
        }*/
        return etat;
    }
}