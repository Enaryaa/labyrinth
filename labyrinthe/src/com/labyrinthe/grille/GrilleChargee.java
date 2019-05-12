package com.labyrinthe.grille;

import com.labyrinthe.fichier.ChoixFichier;
import com.labyrinthe.Fenetre;
import com.labyrinthe.bouton.Bouton;
import com.labyrinthe.element.*;
import com.labyrinthe.listener.Choix;
import com.labyrinthe.listener.GestionMenu;
import com.labyrinthe.listener.ValidationChoix;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe GrilleChargee sert à créer une grille à partir 
 * d'un fichier de données et ensuite à simuler un algorithme
 * suivant une méthode sur cette même grille
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class GrilleChargee extends JPanel implements GrilleInterface
{
	/**
	 * Longueur d'un octet/byte
 	 */
	public static final int BITS_PER_BYTE = 8;
	/**
	 * Constante représentant un chemin
	 */
	private static final int CHEMIN = 0;
	/**
	 * Constante représentant un mur
	 */
    private static final int MUR = 1;

	/**
	 * Constante représentant Thésée
	 */
	private static final int THESEE = 2;
	/**
	 * Constante représentant la sortie
	 */
    private static final int SORTIE = 3;
	/**
	 * Fenetre contenant la grille et les options
	 */
	private Fenetre fenetre;
	/**
	 * Panneau représentant la grille de simulation
	 */
	private JPanel grille;
	/**
	 * Element e de la grille (peut être Thésée, ou la sortie
	 * ou un chemin ou un mur)
	 */
    private Element e;
	/**
	 * Methode selon laquelle l'algorithme va se réaliser :
	 * manuel ou automatique
	 */
    private String methode;
	/**
	 * Algorithme qui va parcourir la grille :
	 * deterministe ou aleatoire
	 */
	private String algo;
	/**
	 * Liste d'éléments
	 */
    private List<Element> box;
	/**
	 * Liste des cellules de la grille
	 */
	private List<Cell> cells;
	/**
	 * Taille de la grille (longueur)
	 */
	private byte taille;
	/**
	 * Coordonnées de la case Thesee
	 * 1 : abscisse
	 * 1 : ordonnée
	 */
	private byte[] thesee;
	/**
	 * Coordonnées de la case Sortie
	 * 1 : abscisse
	 * 1 : ordonnée
	 */
	private byte[] sortie;
	/**
	 * Tableau contenant tous les etats de la grille
	 */
	private char[] etats;
	/**
	 * Bouton qui sert à démarrer l'algorithme
	 */
	Bouton jouer;
	/**
	 * Bouton qui sert à déplacer Thésée lorsque l'on utilise
	 * la méthode manuelle
	 */
	Bouton deplacer;

	/**
	 * Constructeur de la GrilleChargee
	 */
	public GrilleChargee()
	{
		super();
		box = new ArrayList<>();
		box.add(new Chemin());
        // int = 0
		box.add(new Mur());
        // int = 1
		box.add(new Thesee());
        // int = 2
		box.add(new Sortie());
        // int = 3
        // On créé la fenêtre de la simulation
		fenetre = new Fenetre(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT+200);
		// On divise la fenêtre en plusieurs parties pour pouvoir afficher la grille et des boutons
		FlowLayout div = new FlowLayout(FlowLayout.CENTER,0,0);
		fenetre.setLayout(div);

		this.thesee = new byte[2];
		this.sortie = new byte[2];
		/**
	 	* Variable etats_byte qui sert à stocker l'état lu sous forme de byte
	 	*/
		byte etats_byte = 0;
		/**
	 	* Variable etats_int qui sert à transformer l'état (préalablement 
	 	* mis sous forme de byte) en int pour ne pas perdre d'information
	 	*/
		int etats_int = 0;
		
		/**
	 	* Variable qui sert à stocker le nom du fichier qui va être lu
	 	*/
		String fichier = "";
		/**
	 	* ChoixFichier est un JFileChooser qui va permettre de sélectionner
	 	* le fichier dont on veut récupérer les données pour construire
	 	* une grille
	 	*/
		ChoixFichier choix = new ChoixFichier(".");
		/**
	 	* On utilise la variable repertoireCourant pour se placer dans
	 	* le repertoire courant à l'aide de setCurrentDirectory()
	 	*/
		File repertoireCourant = new File(".");
		choix.setCurrentDirectory(repertoireCourant);
		choix.showOpenDialog(null);
		while(fichier.equals(""))
		{
			/**
	 		* Tant qu'un fichier n'a pas été séléctionné, on boucle
	 		*/
			fichier = choix.getSelectedFile().getAbsolutePath();
		}
		/**
	 	* Ci-après, le code pour lire le fichier contenant le format de la grille
	 	*/
		try
		{
			/**
	 		* Initialisation de la lecture
	 		*/
			FileInputStream flux = new FileInputStream(fichier);
			DataInputStream data = new DataInputStream(flux);
			/**
	 		* On lit byte par byte pour obtenir les infos voulues
	 		*/
			taille = data.readByte();
			this.etats = new char[taille*taille];
			thesee[0] = data.readByte();
			thesee[1] = data.readByte();
			sortie[0] = data.readByte();
			sortie[1] = data.readByte();

			String etats_string = "";
			/**
	 		* Tant que la lecture peut continuer, on boucle
	 		*/
			while (data.available()!=0)
			{
				/**
	 			* On obtient les etats 8 par 8 dans etats_byte
	 			*/
				etats_byte = data.readByte();
				/**
	 			* On transforme le résultat en etats_int en faisant un
	 			* "et" logique pour ne pas perdre d'information
	 			*/
				etats_int = etats_byte & 0xFF;
				/**
	 			* On met la suite d'état sous la forme d'une chaîne de
	 			* caractères etats_string_tmp
	 			*/
				String etats_string_tmp = Integer.toBinaryString(etats_int);
				/**
	 			* Tant que la longueur de la chaîne n'est pas égale à 8,
	 			* on ajoute des 0 après pour que les bits correspondants à chaque 
	 			* état soient écrits au bon endroit
	 			*/
				while (etats_string_tmp.length() != BITS_PER_BYTE)
				{
					etats_string_tmp = "0" + etats_string_tmp;
				}
				/**
	 			* La chaîne de caractère etats_string représentant l'ensemble des états
	 			* est assemblée à l'aide de tous les morceaux de 8 états
	 			*/
				etats_string = etats_string + etats_string_tmp;
			}
			/**
	 		* On transforme la châine de caractères en tableau de caractères
	 		*/
			etats = etats_string.toCharArray();
			/**
	 		* La variable tmp sert à stocker la valeur d'un état qui va être interverti
	 		*/
			char tmp = 0;
			/**
	 		* La variable j doit être initialisé avant les boucles car elle prendra ensuite la 
	 		* valeur de i pour ne pas intervertir les états plusieurs fois
	 		*/
			int j = 0;
			for (int i = 0 ; i < taille ; i++)
			{
				j = i;
				while(j < taille)
				{
					/**
	 				* Les états étant triés colonnes par colonnes dans le fichier, on les range
	 				* maintenant ligne par ligne en les intervertissant
	 				*/
					tmp = etats[(i*taille)+j];
					etats[(i*taille)+j] = etats[(i+(j*taille))];
					etats[(i+(j*taille))] = tmp;
					j++;
				}
			}
			/**
	 		* On crée la barre de menu servant à raffraîchir ou sauvegarder
	 		*/
			menuBarre(fenetre);
			/**
	 		* La grille est créée à l'aide des données obtenues par la lecture
	 		*/
			drawGrid(taille,thesee,sortie,etats);
			/**
	 		* La méthode jouer sert à commencer la partie après avoir choisi
	 		* la méthode et l'algorithme
	 		*/
			jouer(fenetre);
			/**
	 		* La fenêtre est rendue visible
	 		*/
			fenetre.setVisible(true);
		}
		catch(IOException e)
		{
			/**
	 		* On attrape une possible erreur de lecture
	 		*/
			System.err.println("Erreur de lecture");
		}
	}

	/**
	 * La grille est créée avec tous les paramètres lus
	 * @param taille byte
	 * @param thesee byte
	 * @param sortie byte
	 * @param etats char
	 */
	private void drawGrid(byte taille, byte[] thesee, byte[] sortie, char[] etats)
	{
		cells = new ArrayList<>();
		/**
	 	* Panneau représentant la grille
	 	*/
		this.grille = new JPanel();
		grille.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT));
		/**
	 	* La grille est divisée de façon à respecter la taille lue
	 	*/
		GridLayout gridLayout = new GridLayout(taille,taille);
		grille.setLayout(gridLayout);
    	int index = 0;
    	for (int i = 0 ; i < taille ; i++)
		{
			for (int j = 0 ; j < taille ; j++ , index++)
			{
				if ((j == thesee[1]) && i == thesee[0])
				{
					/**
	 				* Si les coordonnées sont les mêmes que celles lues
	 				* dans le fichier, on crée une case avec les propriétés 
	 				* de Thésée
	 				*/
					Cell cell = new Cell(index, box.get(THESEE));
					grille.add(cell);
					cells.add(cell);
				}
				else if ((j == sortie[1]) && i == sortie[0])
				{
					/**
	 				* Si les coordonnées sont les mêmes que celles lues
	 				* dans le fichier, on crée une case avec les propriétés 
	 				* de Sortie
	 				*/
					Cell cell = new Cell(index, box.get(SORTIE));
					grille.add(cell);
					cells.add(cell);
				}
				else if (etats[index] == '1')
				{
					/**
	 				* Si l'état concerné est 1, on crée un mur
	 				*/
					Cell cell = new Cell(index, box.get(MUR));
					grille.add(cell);
					cells.add(cell);
				}

				else
				{
					/**
	 				* Si l'état concerné est 0, on crée un chemin
	 				*/
					Cell cell = new Cell(index, box.get(CHEMIN));
					grille.add(cell);
					cells.add(cell);
				}
			}
		}
		grille.setFocusable(true);
		grille.requestFocus();
		fenetre.getContentPane().add(grille);
	}

	/**
	 * On construit une barre de menue dans l'en-tête de la fenêtre
	 * @param f Fenetre
	 */
	private void menuBarre(Fenetre f)
	{
		JMenuBar barre = new JMenuBar();

		/**
	 	* Création onglet fichier
	 	*/
		JMenu fichier = new JMenu("Fichier");
		barre.add(fichier);

        //contient une sauvegarde et ecrase et une generation de grille

		JMenuItem save = new JMenuItem("Sauvegarder");
		fichier.add(save);
		JMenuItem repaint = new JMenuItem("Rafraichir");
		fichier.add(repaint);
		/**
	 	* Ajout des listeners permettant de sauvegarder ou raffraichir la grille
	 	*/
		save.addActionListener(new GestionMenu(this));
		repaint.addActionListener(new GestionMenu(this));

		/**
	 	* Ajout de la barre de menu à la fenêtre
	 	*/
		fenetre.setJMenuBar(barre);
	}

	/**
	 * La méthode jouer() permet à l'utilisateur de lancer la recherche de la sortie
	 * après avoir choisie la méthode et l'algorithme
	 * @param f Fenetre
	 */
	private void jouer(Fenetre f) {
		/**
	 	* Choix est le listener permettant de donner à la grille la méthode et l'algorithme
	 	* à utiliser
	 	*/
        Choix choix = new Choix(this);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT-100));

        /**
	 	* Radios boutons pour choisir l'algorithme
	 	*/
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

        /**
	 	* Radios boutons pour choisir la méthode
	 	*/
        JRadioButton manuel = new JRadioButton(Choix.MANUEL);
        manuel.addActionListener(choix);
        panel2.add(manuel);
        JRadioButton auto = new JRadioButton(Choix.AUTO);
        auto.addActionListener(choix);
        panel2.add(auto);

        ButtonGroup choixjeu = new ButtonGroup();

        choixjeu.add(auto);
        choixjeu.add(manuel);

        /**
	 	* Bouton qui sert à démarrer la simulation
	 	*/
        JPanel panel3 = new JPanel();
        jouer = new Bouton(15);
        jouer.setText("Démarrer");
        /**
	 	* ValidationChoix est le MouseListener qui sert à 
	 	* vérifier que la méthode et l'algorithme ont bien
	 	* été séléctionnés
	 	*/
        jouer.addMouseListener(new ValidationChoix(this));
        jouer.setEnabled(false);
        panel3.add(jouer);

        /**
	 	* Bouton qui sert à poursuivre la simulation manuelle
	 	*/
        JPanel panel4 = new JPanel();
        deplacer = new Bouton(16);
        deplacer.setText("Déplacer");
        /**
	 	* ValidationChoix est le MouseListener qui sert à 
	 	* vérifier que la méthode et l'algorithme ont bien
	 	* été séléctionnés
	 	*/
        deplacer.addMouseListener(new ValidationChoix(this));
        deplacer.setEnabled(false);
        panel4.add(deplacer);

        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        f.getContentPane().add(panel);
    }

    /**
	* La méthode getTaille() sert à obtenir la taille de la grille
	*/
	@Override
    public int getTaille(){
        return this.taille;
    }

    /**
	* La méthode getAlgo() sert à obtenir l'algorithme choisi
	* par l'utilisateur
	*/
    @Override
    public String getAlgo() {
        return algo;
    }

    /**
	* La méthode getMethode() sert à obtenir la méthode choisie
	* par l'utilisateur
	*/
    @Override
    public String getMethode() {
        return methode;
    }

    /**
	* La méthode setAlgo() sert à paramétrer l'algorithme choisi
	* par l'utilisateur
	*/
    @Override
    public void setAlgo(String algo) {
        this.algo = algo;
    }

    /**
	* La méthode setMethode() sert à paramétrer la méthode choisie
	* par l'utilisateur
	*/
    @Override
    public void setMethode(String methode) {
        this.methode = methode;
    }

    /**
	* La méthode cacherFenetre() sert à ......
	* Cacher la fenêtre !!
	*/
    @Override
    public void cacherFenetre() {
        fenetre.setVisible(false);
    }

    /**
	* La méthode getCells() sert à obtenir la liste
	* des cases constituant la grille
	*/
    @Override
    public List<Cell> getCells() {
        return cells;
    }

    /**
	* La méthode getTheseeX() sert à obtenir l'abscisse
	* de la postion de Thésée
	*/
	@Override
	public byte getTheseeX()
	{
		return thesee[0];
	}

	/**
	* La méthode getTheseeY() sert à obtenir l'ordonnée
	* de la postion de Thésée
	*/
	@Override
	public byte getTheseeY()
	{
		return thesee[1];
	}

	/**
	* La méthode getSortieX() sert à obtenir l'abscisse
	* de la postion de Thésée
	*/
	@Override
	public byte getSortieX()
	{
		return sortie[0];
	}

	/**
	* La méthode getSortieY() sert à obtenir l'ordonnée
	* de la postion de Thésée
	*/
	@Override
	public byte getSortieY()
	{
		return sortie[1];
	}

	/**
	* La méthode getEtat() sert à obtenir l'état de la case
	* d'index i
	*/
	@Override
	public char getEtat(int i)
	{
		return etats[i];
	}

	/**
	* La méthode getFenetre() sert à récupérer la fenêtre de la
	* simulation
	*/
	@Override
	public Fenetre getFenetre()
	{
		return this.fenetre;
	}

	/**
	* La méthode getBoutonDemarrer() sert à récupérer l'attente du commencement de
	* la partie
	*/
	@Override
	public Bouton getBoutonDemarrer() {
		return jouer;
	}

	/**
	* La méthode getBoutonDeplacer() sert à récupérer l'attente de deplacement
	*/
	@Override
	public Bouton getBoutonDeplacer() {
		return deplacer;
	}
}