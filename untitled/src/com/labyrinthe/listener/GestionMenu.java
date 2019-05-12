package com.labyrinthe.listener;

import com.labyrinthe.*;
import com.labyrinthe.grille.Grille;
import com.labyrinthe.grille.GrilleChargee;
import com.labyrinthe.grille.GrilleInterface;
import com.labyrinthe.grille.GrilleRand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * la classe <code>com.labyrinthe.listener.GestionMenu</code> permet de gérer le JMenuBarre dans la grille de jeu
 * avec la sauvegarde, l'effacement de la grille ou la générartion d'une nouvelle grille
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class GestionMenu implements ActionListener {
    /**
     *
     */
    private GrilleChargee grilleC;
    /**
     *
     */
    private GrilleInterface grille;
    /**
     *
     */
    private GrilleRand grillr;
    /**
     *
     */
    private Grille gril;
    /**
     *
     */
    private JPanel grillep;

    /**
     *
     * @param grillr com.labyrinthe.grille.GrilleRand
     */
    public GestionMenu(GrilleRand grillr){
        this.grille = grillr;
    }

    /**
     *
     * @param grilleC com.labyrinthe.grille.GrilleChargee
     */
    public GestionMenu(GrilleChargee grilleC){
        this.grille = grilleC;
    }

    /**
     *
     * @param gril com.labyrinthe.grille.Grille
     */
    public GestionMenu(Grille gril){
        this.grille = gril;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nom = e.getActionCommand();

        if (nom == "Sauvegarder")
        {
            Fenetre fenetre_sauvegarde = new Fenetre();
            fenetre_sauvegarde.setSize(500, 200);
            //fenetre_sauvegarde.setLocationRelativeTo(griller);
            FlowLayout gestionnaire = new FlowLayout(FlowLayout.CENTER,0,0);
            fenetre_sauvegarde.setLayout(gestionnaire);
            JLabel choix = new JLabel("Nom du fichier de sauvegarde :");
            choix.setPreferredSize(new Dimension(fenetre_sauvegarde.getWidth()-20,(fenetre_sauvegarde.getHeight()-34)/2));
            JTextField champ = new JTextField();
            champ.setPreferredSize(new Dimension(fenetre_sauvegarde.getWidth()-20,(fenetre_sauvegarde.getHeight()-34)/2));
            ObservateurSauvegarde sauvegarde = new ObservateurSauvegarde(fenetre_sauvegarde,grille);
            champ.addActionListener(sauvegarde);
            fenetre_sauvegarde.add(choix);
            fenetre_sauvegarde.add(champ);
            fenetre_sauvegarde.setVisible(true);
        }
        if (nom == "Effacer"){
            grille.getFenetre().dispose();
            grille = new Grille(grille.getTaille(),grille.getTaille());
        }

        if (nom.equals("Rafraichir")){
            grille.getFenetre().dispose();
            grille = new GrilleRand(grille.getTaille(),grille.getTaille());
        }
    }
}
