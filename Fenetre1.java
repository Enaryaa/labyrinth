import javax.swing.*;
import java.awt.*;

public class Fenetre1 extends Base{

    private static final int COL = 5;
    private static final int LIGNE = 5;

    public Fenetre1(){
        super();
        JPanel panneau = new JPanel();
        this.add(panneau, BorderLayout.CENTER);

        JButton generer = new JButton("Générer la grille");
        JTextField zone = new JTextField();

        this.add(zone, BorderLayout.CENTER);
        panneau.add(generer);


    }



}
