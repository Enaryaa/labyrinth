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
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class Grille extends JPanel implements Action, GrilleInterface
{
	/**
	 *
	 */
	public static final int DETERMINISTE = 1;
	/**
	 *
	 */
	public static final int ALEA = 2;
	/**
	 *
	 */
	public static final int MANUEL = 3;
	/**
	 *
	 */
	public static final int AUTO = 4;
	/**
	 *
	 */
	private Fenetre f;
	/**
	 *
	 */
	private String algo;
	/**
	 *
	 */
	private String methode;
	/**
	 *
	 */
	private int colonnes;
	/**
	 *
	 */
	private int lignes;
	/**
	 *
	 */
	private Element e;
	/**
	 *
	 */
	private boolean theseeLa = false;
	/**
	 *
	 */
	private boolean sortieLa = false;
	/**
	 *
	 */
	private JPanel grille;
	/**
	 *
	 */
	private Cell cellToRegister;
	/**
	 *
	 */
	Bouton deplacer;
	/**
	 *
	 */
	Bouton jouer;

	private List<Cell> cells;

	/**
	 *
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
	 *
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
	 *
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
	 *
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
	 *
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