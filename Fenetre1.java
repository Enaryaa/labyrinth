import javax.swing.*;
import java.awt.*;

public class Fenetre1 extends Base 
{

    private static final int COL = 5;
    private static final int LIGNE = 5;

    public Fenetre1()
    {
        super();
        JPanel panneau = new JPanel();
        this.add(panneau, BorderLayout.CENTER);
        Grille grille = new Grille(4,4);
        grille.setVisible(true);
        /*JButton generer = new JButton("Generer la grille");
        JTextField zone = new JTextField();
        this.add(zone, BorderLayout.CENTER);
        panneau.add(generer);*/
    }
}


