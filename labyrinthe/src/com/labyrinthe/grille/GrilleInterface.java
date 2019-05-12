package com.labyrinthe.grille;

import com.labyrinthe.Fenetre;
import com.labyrinthe.bouton.Bouton;

import java.util.List;

/**
 * L'interface GrilleInterface est utilisée pour nommer
 * des méthodes communes aux différentes grilles
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public interface GrilleInterface {
    /**
     * La méthode getTaille() sert à obtenir la taille de la grille
     * @return int
     */
     int getTaille();

    /**
     * La méthode getCells() sert à obtenir la liste
     * des cases constituant la grille
     * @return List
     */
     List<Cell> getCells();

    /**
     * La méthode getAlgo() sert à obtenir l'algorithme choisi
     * par l'utilisateur
     * @return String
     */
     String getAlgo();

    /**
     * La méthode getMethode() sert à obtenir la méthode choisie
     * par l'utilisateur
     * @return String
     */
     String getMethode();

    /**
     * La méthode setAlgo() sert à paramétrer l'algorithme choisi
     * par l'utilisateur
     * @param s String
     */
     void setAlgo(String s);

    /**
     * La méthode setMethode() sert à paramétrer la méthode choisie
     * par l'utilisateur
     * @param s String
     */
     void setMethode(String s);

    /**
     * La méthode cacherFenetre() sert à ......
     * Cacher la fenêtre !!
     */
    void cacherFenetre();

    /**
     * La méthode getTheseeX() sert à obtenir l'abscisse
     * de la postion de Thésée
     * @return byte
     */
	 byte getTheseeX();

    /**
     * La méthode getTheseeY() sert à obtenir l'ordonnée
     * de la postion de Thésée
     * @return byte
     */
	 byte getTheseeY();

    /**
     * La méthode getSortieX() sert à obtenir l'abscisse
     * de la postion de Thésée
     * @return byte
     */
	 byte getSortieX();

    /**
     * La méthode getSortieY() sert à obtenir l'ordonnée
     * de la postion de Thésée
     * @return byte
     */
	 byte getSortieY();

    /**
     * La méthode getEtat() sert à obtenir l'état de la case
     * d'index i
     * @param i inr
     * @return char
     */
	 char getEtat(int i);

    /**
     * La méthode getFenetre() sert à récupérer la fenêtre de la
     * simulation
     * @return Fenetre
     */
     Fenetre getFenetre();

    /**
     * La méthode getBoutonDemarrer() sert à récupérer l'attente du commencement de
     * la partie
     * @return Bouton
     */
     Bouton getBoutonDemarrer();

    /**
     * La méthode getBoutonDeplacer() sert à récupérer l'attente de deplacement
     * @return Bouton
     */
     Bouton getBoutonDeplacer();
}
