import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame
{
    private static final String APP_NAME = "Algorithme d'Ariane";
    private static final int SCREEN_WIDTH = 300;
    private static final int SCREEN_HEIGHT = 300;
    private Color couleur_de_fond;

    public Fenetre()
    {
    	// Titre de la fenêtre
        this.setTitle(APP_NAME);
        // Taille de la fenêtre
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);
        // Fermeture lorsqu'on clique sur la croix
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // La taille de la fenêtre n'est pas modifiable
        this.setResizable(false);
        // Ajout d'un panneau pour la couleur de fond
        JPanel fond = new JPanel();
        fond.setBackground(Color.BLACK);
        this.add(fond);
        // Ajout du Menu de bienvenue
        Menu bienvenue = new Menu(0,this);
        this.add(bienvenue);
    }

    public void clearContent()
    {
	    this.getContentPane().removeAll();
	}
    /*public void changeBackground(Color c)
    {
    	this.couleur_de_fond = c;
    	fond.setBackground(couleur_de_fond);
    	this.repaint();
    }*/
}