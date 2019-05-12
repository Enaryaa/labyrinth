package com.labyrinthe;

import com.labyrinthe.bouton.Bouton;
import com.labyrinthe.listener.ControleTaille;
import com.labyrinthe.listener.Observateur;

import javax.swing.*;
import java.awt.*;

/**
 * la classe <code>Fenetre</code> permet de générer les premières fenetres de l'application.
 * ainsi les fenetres de saisies de la taille, de choix de la grille chargée ou non
 * et le choix de la grille aléatoire ou vide.
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class Fenetre extends JFrame {
    /**
     *constante donnant le nom à l'application
     */
    private static final String APP_NAME = "Algorithme Ariane";
    /**
     *constante ayant la largeur de la fenetre
     */
    public static final int SCREEN_WIDTH = 300;
    /**
     *constante ayant la hauteur de la fenetre
     */
    public static final int SCREEN_HEIGHT = 300;

    /**
     *Constructeur de base de la Fenetre
     */
    public Fenetre() {

        this.setTitle(APP_NAME);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

    }

    /**
     *Constructeur avec les tailles de la fenetre
     * @param width int
     * @param height int
     */
    public Fenetre(int width, int height){
        this.setTitle(APP_NAME);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    /**
     *méthode contenant le menu avec le choix du chargement et de la nouvelle grille
     */
  public void menu1() {
    GridLayout gestionnaire = new GridLayout(2, 1);
    this.setLayout(gestionnaire);
    JPanel panel = new JPanel();

    JLabel etiquette = new JLabel("Algorithme Ariane");

    etiquette.setHorizontalAlignment(JLabel.CENTER);
    etiquette.setVerticalAlignment(JLabel.BOTTOM);
    this.add(etiquette);

    Bouton nouveau = new Bouton(1);
    Bouton load = new Bouton(2);
    nouveau.setText("Nouvelle Grille");
    load.setText("Charger une grille");
    panel.add(nouveau);
    panel.add(load);

    Observateur o = new Observateur(this);
    nouveau.addMouseListener(o);
    Observateur c = new Observateur(this);
    load.addMouseListener(c);

    this.add(panel, BorderLayout.CENTER);

    panel.setBackground(Color.PINK);
    this.getContentPane().add(panel);
}

    /**
     *méthode avec le choix de la nouvelle grille: vide ou aléatoire
     */
    public void menu2(){
    GridLayout gestionnaire = new GridLayout(2, 1);
    this.setLayout(gestionnaire);
    JPanel panel = new JPanel();

    JLabel etiquette = new JLabel("Choix de la nouvelle grille");

    etiquette.setHorizontalAlignment(JLabel.CENTER);
    etiquette.setVerticalAlignment(JLabel.BOTTOM);
    this.add(etiquette);

    Bouton vide = new Bouton(3);
    Bouton rand = new Bouton(4);
    vide.setText("Vide");
    rand.setText("Aleatoire");
    panel.add(vide);
    panel.add(rand);
    Observateur o = new Observateur(this);
    vide.addMouseListener(o);
    Observateur c = new Observateur(this);
    rand.addMouseListener(c);

    this.add(panel, BorderLayout.CENTER);

    panel.setBackground(Color.BLUE);
    this.getContentPane().add(panel);
}

    /**
     *méthode avec la saisie de la taille pour la grille vide
     */
    public void saisi(){
  GridLayout gestionnaire = new GridLayout(2, 1);
   this.setLayout(gestionnaire);
   JPanel panel = new JPanel();

   JLabel etiquette = new JLabel("Taille de la grille");

   etiquette.setHorizontalAlignment(JLabel.CENTER);
   etiquette.setVerticalAlignment(JLabel.BOTTOM);
   this.add(etiquette);
   
   Bouton generer = new Bouton(9);
   generer.setText(" Generer");
   
   panel.add(generer);
   JTextField zone = new JTextField();
   zone.setPreferredSize( new Dimension( 50, 25 ) );
   panel.add(zone);
   ControleTaille o = new ControleTaille(this,zone);
   generer.addMouseListener(o);

   this.add(panel, BorderLayout.CENTER);
   panel.setBackground(Color.GREEN);
   this.getContentPane().add(panel);
}

    /**
     *méthode avec la saisie de la taille pour la grille aléatoire
     */
    public void saisiRand(){
        GridLayout gestionnaire = new GridLayout(2, 1);
        this.setLayout(gestionnaire);
        JPanel panel = new JPanel();

        JLabel etiquette = new JLabel("Taille de la grille");

        etiquette.setHorizontalAlignment(JLabel.CENTER);
        etiquette.setVerticalAlignment(JLabel.BOTTOM);
        this.add(etiquette);

        Bouton generer = new Bouton(10);
        generer.setText(" Generer");

        panel.add(generer);
        JTextField zone = new JTextField();
        zone.setPreferredSize( new Dimension( 50, 25 ) );
        panel.add(zone);
        ControleTaille o = new ControleTaille(this,zone);
        generer.addMouseListener(o);

        this.add(panel, BorderLayout.CENTER);
        panel.setBackground(Color.GREEN);
        this.getContentPane().add(panel);
    }

    /**
     *permet de rendre visible une fenetre
     */
    public void visible() {

        this.setVisible(true);
    }

    /**
     *permet de vider la fenetre de tous ces panels
     */
    public void clearContent() {
        this.getContentPane().removeAll();
        this.setVisible(false);
    }

}