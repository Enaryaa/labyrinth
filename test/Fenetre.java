import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Fenetre extends JFrame {

    private static final String APP_NAME = "Algorithme Ariane";
    private static final int SCREEN_WIDTH = 300;
    private static final int SCREEN_HEIGHT = 300;

    public Fenetre() {

        this.setTitle(APP_NAME);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

    }


    public void menu3() {
      GridLayout gestionnaire = new GridLayout(2, 1);
      this.setLayout(gestionnaire);
      JPanel panel = new JPanel();

      JLabel etiquette = new JLabel("Type Algorithme");

      etiquette.setHorizontalAlignment(JLabel.CENTER);
      etiquette.setVerticalAlignment(JLabel.BOTTOM);
      this.add(etiquette);

      Bouton deter = new Bouton(5);
      Bouton alea = new Bouton(6);
      deter.setText("Algorithme Deterministe");
      alea.setText("Algorithme Aleatoire");
      panel.add(deter);
      panel.add(alea);
      Observateur o = new Observateur(this);
      alea.addMouseListener(o);
      Observateur c = new Observateur(this);
      deter.addMouseListener(c);


      this.add(panel, BorderLayout.CENTER);

      panel.setBackground(Color.CYAN);
      this.getContentPane().add(panel);
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

    JLabel etiquette = new JLabel("Choix de la nouvelle grille");

    etiquette.setHorizontalAlignment(JLabel.CENTER);
    etiquette.setVerticalAlignment(JLabel.BOTTOM);
    this.add(etiquette);

    Bouton vide = new Bouton(3);
    Bouton rand = new Bouton(4);
    vide.setText("vide");
    rand.setText("random");
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

public void menu4(){
   GridLayout gestionnaire = new GridLayout(2, 1);
   this.setLayout(gestionnaire);
   JPanel panel = new JPanel();

   JLabel etiquette = new JLabel("Choix de la nouvelle grille");

   etiquette.setHorizontalAlignment(JLabel.CENTER);
   etiquette.setVerticalAlignment(JLabel.BOTTOM);
   this.add(etiquette);
   Bouton manuel = new Bouton(7);
   Bouton auto = new Bouton(8);
   manuel.setText(" manuel");
   auto.setText("auto");
   Observateur o = new Observateur(this);
   auto.addMouseListener(o);
   Observateur c = new Observateur(this);
   manuel.addMouseListener(c);
   panel.add(manuel);
   panel.add(auto);

   this.add(panel, BorderLayout.CENTER);

   panel.setBackground(Color.RED);
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