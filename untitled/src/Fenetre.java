import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {

    private static final String APP_NAME = "Algorithme Ariane";
    public static final int SCREEN_WIDTH = 300;
    public static final int SCREEN_HEIGHT = 300;

    public Fenetre() {

        this.setTitle(APP_NAME);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

    }
    public Fenetre(int width, int height){
        this.setTitle(APP_NAME);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

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

public void menu2(){
    GridLayout gestionnaire = new GridLayout(2, 1);
    this.setLayout(gestionnaire);
    JPanel panel = new JPanel();

    JLabel etiquette = new JLabel("ValidationChoix de la nouvelle grille");

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

    public void visible() {
        this.setVisible(true);
    }

    public void clearContent() {
        this.getContentPane().removeAll();
        this.setVisible(false);
    }

}