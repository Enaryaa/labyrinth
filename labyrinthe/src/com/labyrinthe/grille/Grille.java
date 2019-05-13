package com.labyrinthe.grille;

import com.labyrinthe.bouton.Action;
import com.labyrinthe.Fenetre;
import com.labyrinthe.bouton.*;
import com.labyrinthe.element.Chemin;
import com.labyrinthe.element.Element;
import com.labyrinthe.listener.Choix;
import com.labyrinthe.listener.Coloration;
import com.labyrinthe.listener.GestionMenu;
import com.labyrinthe.listener.ValidationChoix;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe <code>Grillee</code> sert à créer une grille manuellement
 * et ensuite à simuler un algorithme suivant une méthode 
 * sur cette même grille
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class Grille extends JPanel implements Action, GrilleInterface
{
	/**
	 * Constante désignant l'algorithme déterministe
	 */
	public static final int DETERMINISTE = 1;
	/**
	 * Constante désignant l'algorithme aléatoire
	 */
	public static final int ALEA = 2;
	/**
	 * Constante désignant la méthode manuelle
	 */
	public static final int MANUEL = 3;
	/**
	 * Constante désignant la méthode automatique
	 */
	public static final int AUTO = 4;
	/**
	 * Fenetre contenant la grille et les options
	 */
	private Fenetre f;
	/**
	 * Algorithme qui va parcourir la grille :
	 * deterministe ou aleatoire
	 */
	private String algo;
	/**
	 * Methode selon laquelle l'algorithme va se réaliser :
	 * manuel ou automatique
	 */
	private String methode;
	/**
	 * Nombre de colonnes de la grille
	 */
	private int colonnes;
	/**
	 * Nombre de lignes de la grille 
	 */
	private int lignes;
	/**
	 * Element e de la grille (peut être Thésée, ou la sortie
	 * ou un chemin ou un mur)
	 */
	private Element e;
	/**
	 * Booleen représentant la présence ou non de Thésée à une 
	 * certaine position
	 */
	private boolean theseeLa = false;
	/**
	 * Booleen représentant la présence ou non de la sortie à une 
	 * certaine position
	 */
	private boolean sortieLa = false;
	/**
	 * Panneau représentant la grille de simulation
	 */
	private JPanel grille;
	/**
	 * Sert à renvoyer une case cherchée
	 */
	private Cell cellToRegister;
	/**
	 * Bouton qui sert à déplacer Thésée lorsque l'on utilise
	 * la méthode manuelle
	 */
	Bouton deplacer;
	/**
	 * Bouton qui sert à démarrer l'algorithme
	 */
	Bouton jouer;
	/**
	 * Liste des cellules de la grille
	 */
	private List<Cell> cells;

	/**
	 * Constructeur de Grille en fonction du nombre de colonnes
	 * et de lignes déterminés par la taille souhaités par
	 * l'utilisateur
	 * @param c int
	 * @param l int
	 */
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

	/**
	 *Composante de la fenetre où se trouve les boutons de selection de l'algorithme
	 * de la méthode et des boutons jouer et déplacer
	 * @param f Fenetre
	 */
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
		 jouer = new Bouton(15);
		jouer.setText("Démarrer");
		jouer.addMouseListener(new ValidationChoix(this));
		jouer.setEnabled(false);
		panel3.add(jouer);

		JPanel panel4 = new JPanel();
		 deplacer = new Bouton(16);
		deplacer.setText("Déplacer");
		deplacer.addMouseListener(new ValidationChoix(this));
		deplacer.setEnabled(false);
		panel4.add(deplacer);

		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		f.getContentPane().add(panel);
	}

	/**
	 *Permet de créer la grille contenant des Cell, ce qui permet d'y appliquer les propriétés
	 * des elements
	 * @param f Fenetre
	 */
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

	/**
	 *Composante de la fenetre qui contient les boutons permettant de placer
	 * les elements dans la grille
	 * @param f Fenetre
	 */
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

	/**
	 *Composante qui créer et gère la barre de menu de la fenetre
	 * @param f Fenetre
	 */
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
		int compteur = 0;
		for (int i = 0 ; i < lignes ; i++)
		{
			for (int j = 0 ; j < colonnes ; j++, compteur++)
			{
				cellToRegister = cells.get(compteur);
				if (cellToRegister.getPropriete().getCouleur() == Color.BLUE)
				{
					return (byte) i;
				}
			}
		}
		return theseex;
	}

	@Override
	public byte getTheseeY()
	{
		byte theseey = 0;
		int compteur = 0;
		for (int i = 0 ; i < lignes ; i++)
		{
			for (int j = 0 ; j < colonnes ; j++, compteur++)
			{
				cellToRegister = cells.get(compteur);
				if (cellToRegister.getPropriete().getCouleur() == Color.BLUE)
				{
					return (byte) j;
				}
			}
		}
		return theseey;
	}

	@Override
	public byte getSortieX()
	{
		byte sortiex = 0;
		int compteur = 0;
		for (int i = 0 ; i < lignes ; i++)
		{
			for (int j = 0 ; j < colonnes ; j++, compteur++)
			{
				cellToRegister = cells.get(compteur);
				if (cellToRegister.getPropriete().getCouleur() == Color.GREEN)
				{
					return (byte) i;
				}
			}
		}
		return sortiex;
	}

	@Override
	public byte getSortieY()
	{
		byte sortiey = 0;
		int compteur = 0;
		for (int i = 0 ; i < lignes ; i++)
		{
			for (int j = 0 ; j < colonnes ; j++, compteur++)
			{
				cellToRegister = cells.get(compteur);
				if (cellToRegister.getPropriete().getCouleur() == Color.GREEN)
				{
					return (byte) j;
				}
			}
		}
		return sortiey;
	}

	@Override
	public char getEtat(int in)
	{
		int index = in;
		char etat = '0';
		cellToRegister = cells.get(index);
		if (cellToRegister.getPropriete().getCouleur() == Color.WHITE)
		{
			etat = '0';
		}
		else if (cellToRegister.getPropriete().getCouleur() == Color.BLACK)
		{
			etat = '1';
		}
		return etat;
	}

	@Override
	public Fenetre getFenetre()
	{
		return this.f;
	}

	@Override
	public Bouton getBoutonDemarrer() {
		return jouer;
	}

	@Override
	public Bouton getBoutonDeplacer() {
		return deplacer;
	}

}