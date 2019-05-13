package com.labyrinthe.listener;

import com.labyrinthe.grille.GrilleInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * la classe <code>Choix</code>  permet de récupérer l'algorithme que l'utilisateur
 * souhaite appliquer ainsi que la méthode de simulation
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class Choix implements ActionListener {
    /**
     *composante GrilleInterface
     */
    private GrilleInterface grille;
    /**
     *détermine en constante le String Déterministe
     */
    public static final  String DETER = "Déterministe";
    /**
     *défini la constante  aléatoire
     */
    public static final String ALEA = "Aléatoire";
    /**
     *definie la constante Manuel
     */
    public static final String MANUEL = "Manuel";
    /**
     *definie la constante Automatique
     */
    public static final String AUTO = "Automatique";

    /**
     *Constructeur prend en paramètre une grilleInterface qu'elle rend publique
     * @param grille GrilleInterface
     */
    public Choix(GrilleInterface grille){
        this.grille = grille;
    }

    /**
     *permet de set l'algorithme et la methode choisie pour ensuite
     * les utiliser dans la classe Algo
     * puis rend utilisable les boutons demarrer et deplacer une fois que l'algorithme
     * et la méthode on été choisie
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String choix = e.getActionCommand();
        if (choix.equals(DETER) || choix.equals(ALEA)) {
            grille.setAlgo(choix);
        }
        if (choix.equals(MANUEL) || choix.equals(AUTO)){
            grille.setMethode(choix);
        }
        if (grille.getAlgo() != null && grille.getMethode() != null){
            grille.getBoutonDemarrer().setEnabled(true);
            grille.getBoutonDeplacer().setEnabled(true);
        }
    }
}
