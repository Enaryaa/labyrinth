import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrilleRand extends Fenetre {
    private int colonnes;
    private int lignes;
    private Element e;
    private List<Element> box;
    private static final int CHEMIN = 0;
    private static final int MUR = 1;
    private static final int THESEE = 2;
    private static final int SORTIE = 3;
    private int[][] grille;
    private int posx;
    private int posy;
    private int theseex;
    private int theseey;


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

        //Creation de la barre de menu
        menuBarre(this);

        this.colonnes = c;
        this.lignes = l;

        this.setVisible(true);
    }

    private void menuBarre(Fenetre f) {
        JMenuBar barre = new JMenuBar();

        //Creation onglet fichier
        JMenu fichier = new JMenu("Fichier");
        barre.add(fichier);
        //contient une sauvegarde et erase
        JMenuItem save = new JMenuItem("Sauvegarder");
        fichier.add(save);
        JMenuItem erase = new JMenuItem("Effacer");
        fichier.add(erase);

        f.setJMenuBar(barre);
    }

    private void drawGrid() {
        grille = arrayFill();
        placerThesee();
        deplacement();

    }

    private int[][] arrayFill() {
        int[][] grille = new int[colonnes][lignes];
        for (int i = 0; i < colonnes; i++){
            for (int j = 0; j < lignes; j++){
                grille[i][j] = MUR;
            }
        }
     return grille;
    }

    private void placerThesee(){
         posx = (int) (Math.random()*(colonnes-1));
         posy = (int) (Math.random()*(lignes-1));

        grille[posx][posy] = THESEE;

        theseex = posx;
        theseey = posy;
    }

    private void deplacement(){
        int nbchemin = (colonnes*lignes)-2;

        for (int i = 0; i < nbchemin; i++){
            boolean randXouY = new Random().nextBoolean(); // choisi x ou y
            if (randXouY) { // x si c'est true
                boolean randdirect = new Random().nextBoolean(); // choisi gauche ou droite
                if (randdirect) { // gauche si c'est true
                    if()

                }
            }
        }
    }
}

