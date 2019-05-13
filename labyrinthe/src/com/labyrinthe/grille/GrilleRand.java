package com.labyrinthe.grille;

import com.labyrinthe.Fenetre;
import com.labyrinthe.bouton.Bouton;
import com.labyrinthe.element.*;
import com.labyrinthe.listener.Choix;
import com.labyrinthe.listener.GestionMenu;
import com.labyrinthe.listener.ValidationChoix;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La classe <code>GrilleRand</code> crée une grille remplie aléatoirement
 * avec une taille préalablement définie par l'utilisateur
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class GrilleRand extends JPanel implements GrilleInterface {
    /**
     * Nombre de colonnes de la grille
     */
    private int colonnes;
    /**
     * Nombre de lignes de la grille
     */
    private int lignes;
    /**
     * Fenetre contenant la grille et les options
     */
    private Fenetre f;
    /**
     * Element e de la grille (peut être Thésée, ou la sortie
     * ou un chemin ou un mur)
     */
    private Element e;
    /**
     * Liste d'éléments
     */
    private List<Element> box;
    /**
     * Tableau représentant chaque position de case dans la grille
     */
    private int[][] grille;
    /**
     * Tableau représentant l'état visité ou non de chaque case
     */
    private boolean[][] visited;
    /**
     * Constante représentant un chemin
     */
    private static final int CHEMIN = 0;
    /**
     * Constante représentant un mur
     */
    private static final int MUR = 1;
    /**
     * Constante représentant un Thesee
     */
    private static final int THESEE = 2;
    /**
     * Constante représentant un sortie
     */
    private static final int SORTIE = 3;
    /**
     * Methode selon laquelle l'algorithme va se réaliser :
     * manuel ou automatique
     */
    private String methode;
    /**
     *choix de l'algorithme : déterministe ou aléatoire
     */
    private String algo;
    /**
     *position x de la cellule
     */
    private int posx;
    /**
     *position y de la cellule
      */
    private int posy;
    /**
     *position x de thesee
     */
    private int theseex;
    /**
     *position y de thesee
     */
    private int theseey;
    /**
     *position x de l'element sortie
     */
    private int sortiex;
    /**
     *position y de l'element sortie
     */
    private int sortiey;
    /**
     *bouton déplacer avec un id
      */
    Bouton deplacer;
    /**
     * bouton jouet avec un id
      */
    Bouton jouer;
    /**
     *liste des cellules
     */
    private List<Cell> cells;

    /**
     *Constructeur qui prend en paramètre la taille de la grille que l'utilisateur
     * choisie (ligne et colonne)
     * @param c int
     * @param l int
     */
    public GrilleRand(int c, int l) {
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

        this.colonnes = c;
        this.lignes = l;

        f = new Fenetre(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT+200);
        FlowLayout div = new FlowLayout(FlowLayout.CENTER,0,0);
        f.setLayout(div);
        menuBarre(f);
        drawGrid(f);
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
     *Composante qui créer et gère la barre de menu de la fenetre
     * @param f Fenetre
     */
    private void menuBarre(Fenetre f) {
        JMenuBar barre = new JMenuBar();

        //Creation onglet fichier
        JMenu fichier = new JMenu("Fichier");
        barre.add(fichier);

        //contient une sauvegarde et erase et une generation de grille

        JMenuItem save = new JMenuItem("Sauvegarder");
        fichier.add(save);
        JMenuItem repaint = new JMenuItem("Rafraichir");
        fichier.add(repaint);

        save.addActionListener(new GestionMenu(this));
        repaint.addActionListener(new GestionMenu(this));

        f.setJMenuBar(barre);
    }

    /**
     *Permet de dessiner la grille en associant chaque element aux Cell
     * @param f Fenetre
     */
    private void drawGrid(Fenetre f) {
        cells = new ArrayList<>();
        grille = arrayFill();

        placerThesee();
        deplacement();
        placerSortie();

        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT));
        GridLayout gridLayout = new GridLayout(colonnes,lignes);
        p.setLayout(gridLayout);

        int index = 0;
        for (int i = 0; i < colonnes; i++){
            for (int j = 0; j < lignes; j++){
                int current = grille[i][j];
                Cell cell;
                if (current == MUR){
                    cell = new Cell(index, box.get(MUR));
                }
                else if(current == CHEMIN){
                    cell = new Cell(index, box.get(CHEMIN));
                }
                else if(current == THESEE){
                    cell = new Cell(index, box.get(THESEE));
                }
                else if(current == SORTIE){
                    cell = new Cell(index, box.get(SORTIE));
                } else {
                    cell = new Cell(index, box.get(CHEMIN));
                }
                p.add(cell);
                cells.add(cell);
                index++;
            }
        }
        p.setFocusable(true);
        p.requestFocus();
        f.getContentPane().add(p);
    }

    /**
     *Permet de créer la grille où chaque Cell est soit un Mur soit un Chemin non visité
     * @return int
     */
    private int[][] arrayFill() {
        grille = new int[colonnes][lignes];
        visited = new boolean[colonnes][lignes];
        for (int i = 0; i < colonnes -1 ; i++){
            for (int j = 0; j < lignes -1; j++){
                boolean randXouY = new Random().nextBoolean();
                if (randXouY) {
                    grille[i][j] = MUR;
                } else {
                    grille[i][j] = CHEMIN;
                    visited[i][j] = false;
                }
            }
        }
        return grille;
    }

    /**
     *place aléatoirement Thesée sur la grille en fonction de la taille de la grille
     * et récupère sa position
     */
    private void placerThesee(){
        posx = (int) (Math.random()*(colonnes-1));
        posy = (int) (Math.random()*(lignes-1));

        grille[posx][posy] = THESEE;

        theseex = posx;
        theseey = posy;
    }

    /**
     *place aléatoirement la Sortie sur la grille en fonction de la taille de la grille
     * si le chemin a été visité.
     */
    private void placerSortie(){
        posx = (int) (Math.random()*(colonnes-1));
        posy = (int) (Math.random()*(lignes-1));

        if (grille[posx][posy] == CHEMIN && visited[posx][posy]) {
            grille[posx][posy] = SORTIE;
        }
        else{
            placerSortie();
        }
        sortiex = posx;
        sortiey = posy;
    }

    /**
     *Algorithme qui crée un chemin aléatoire afin d'avoir au moins un chemin possible
     *entre thesee et la sortie, la sortie est placée aleatoirement sur les chemins visités
     */
    private void deplacement(){
        //a
        int nbchemin = (colonnes+lignes);
        boolean randDirect;

        for (int i = 0; i < nbchemin; i++){
            randDirect = new Random().nextBoolean(); // choisi gauche ou droite
            if (randDirect) { // gauche si c'est true
                if (!moveLeft()) {
                    if (!moveRight()) {
                        randDirect = new Random().nextBoolean();
                        if (randDirect) { // haut ici
                            if (!moveUp()) {
                                moveDown();
                            }
                        } else { //bas ici
                            if (!moveDown()) {
                                moveUp();
                            }
                        }
                    }
                }
            } else { // droite ici
                if (!moveRight()) {
                    if (!moveLeft()) {
                        randDirect = new Random().nextBoolean();
                        if (randDirect) { // haut ici
                            if (!moveUp()) {
                                moveDown();
                            }
                        } else { //bas ici
                            if (!moveDown()) {
                                moveUp();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *méthode qui gère le déplacement aleatoire à gauche du chemin possible
     * @return boolean
     */
    private boolean moveLeft() {
        int  rand = (int) (Math.random()*((colonnes/2)))+1;
        for (int i = 0; i < colonnes-rand; i ++) {
            if (posx > 0 && grille[posx-1][posy] != THESEE) {
                posx --;
                grille[posx][posy] = CHEMIN;
                visited[posx][posy] = true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     *méthode qui gère le déplacement aleatoire à droite du chemin possible
     * @return boolean
     */
    private boolean moveRight() {
        int rand = (int) (Math.random()*((colonnes/2)))+1;
        for (int i = 0; i < colonnes-rand; i ++) {
            if (posx < colonnes-1 && grille[posx+1][posy] != THESEE) {
                posx++;
                grille[posx][posy] = CHEMIN;
                visited[posx][posy] = true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     *méthode qui gère le déplacement aleatoire en haut du chemin possible
     * @return boolean
     */
    private boolean moveUp() {
        int  rand = (int) (Math.random()*((colonnes/2)))+1;
        for (int i = 0; i < colonnes-rand; i ++) {
            if (posy > 0 && grille[posx][posy-1] != THESEE) {
                posy--;
                grille[posx][posy] = CHEMIN;
                visited[posx][posy] = true;
            }
            else {
                return false;
            }
        }
        return true;
    }

    /**
     *méthode qui gère le déplacement aleatoire en bas du chemin possible
     * @return boolean
     */
    private boolean moveDown() {
        int rand = (int) (Math.random()*((colonnes/2)))+1;
        for (int i = 0; i < colonnes-rand; i ++) {
            if (posy < colonnes-1 && grille[posx][posy+1] != THESEE) {
                posy++;
                grille[posx][posy] = CHEMIN;
                visited[posx][posy] = true;
            }
            else {
                return false;
            }
        }
        return true;
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
        for (int i = 0; i < colonnes; i++)
        {
            for (int j = 0; j < lignes; j++)
            {
                int current = grille[i][j];
                if(current == THESEE)
                {
                    theseex = (byte) i;
                }
            }
        }
        return theseex;
    }

    @Override
    public byte getTheseeY()
    {
        byte theseey = 0;
        for (int i = 0; i < colonnes; i++)
        {
            for (int j = 0; j < lignes; j++)
            {
                int current = grille[i][j];
                if(current == THESEE)
                {
                    theseey = (byte) j;
                }
            }
        }
        return theseey;
    }

    @Override
    public byte getSortieX()
    {
        byte sortiex = 0;
        for (int i = 0; i < colonnes; i++)
        {
            for (int j = 0; j < lignes; j++)
            {
                int current = grille[i][j];
                if(current == SORTIE)
                {
                    sortiex = (byte) i;
                }
            }
        }
        return sortiex;
    }

    @Override
    public byte getSortieY()
    {
        byte sortiey = 0;
        for (int i = 0; i < colonnes; i++)
        {
            for (int j = 0; j < lignes; j++)
            {
                int current = grille[i][j];
                if(current == SORTIE)
                {
                    sortiey = (byte) j;
                }
            }
        }
        return sortiey;
    }

    @Override
    public char getEtat(int in)
    {
        int index = in;
        int compteur = 0;
        char etat = '0';
        for (int i = 0; i < colonnes; i++)
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