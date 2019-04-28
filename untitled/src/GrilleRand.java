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
    private int maxUp = 10;
    private int maxDown = 10;
    private int maxLeft = 10;
    private int maxRight = 10;


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
                boolean randXouY = new Random().nextBoolean();
                if (randXouY) {
                    grille[i][j] = MUR;
                } else {
                    grille[i][j] = CHEMIN;
                }
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
        int nbchemin = (colonnes*lignes*2);
        boolean randXouY;
        boolean randDirect;

        for (int i = 0; i < nbchemin; i++){
            if (i+1 == nbchemin){
                grille[posx][posy] = SORTIE;
                break;
            }
            randXouY = new Random().nextBoolean(); // choisi x ou y
            if (randXouY) { // x si c'est true
                randDirect = new Random().nextBoolean(); // choisi gauche ou droite
                if (randDirect) { // gauche si c'est true
                    if (!moveLeft()) {
                        if (!moveRight()) {
                            randDirect = new Random().nextBoolean();
                            if (randDirect) { // haut ici
                                if (!moveUp()) {
                                    moveDown();
                                }
                            } else { //bas ici
                                if (!moveDown()) {
                                    moveUp();
                                }
                            }
                        }
                    }
                } else { // droite ici
                    if (!moveRight()) {
                        if (!moveLeft()) {
                            randDirect = new Random().nextBoolean();
                            if (randDirect) { // haut ici
                                if (!moveUp()) {
                                    moveDown();
                                }
                            } else { //bas ici
                                if (!moveDown()) {
                                    moveUp();
                                }
                            }
                        }
                    }
                }
            } else { // start Y
                randDirect = new Random().nextBoolean();
                if (randDirect) { // haut ici
                    if (!moveUp()) {
                        if (!moveDown()) {
                            randDirect = new Random().nextBoolean();
                            if (randDirect) { //gauche
                                if (!moveLeft()) {
                                    moveRight();
                                }
                            } else {
                                if (!moveRight()) {
                                    moveLeft();
                                }
                            }
                        }
                    }
                } else { //bas ici
                    if (!moveDown()) {
                        if (!moveUp()) {
                            randDirect = new Random().nextBoolean();
                            if (randDirect) { //gauche
                                if (!moveLeft()) {
                                    moveRight();
                                }
                            } else {
                                if (!moveRight()) {
                                    moveLeft();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean moveLeft() {
        int rand = (int) (Math.random()) * 5;
        for (int i = 0; i < rand; i ++) {
            if (posx > 0 && (posx-1 != theseex)) {
                posx --;
                grille[posx][posy] = CHEMIN;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean moveRight() {
        int rand = (int) (Math.random()) * 5;
        for (int i = 0; i < rand; i ++) {
            if (posx < (colonnes - 1) && (posx + 1 != theseex)) {
                posx++;
                grille[posx][posy] = CHEMIN;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean moveUp() {
        int rand = (int) (Math.random()) * 5;
        for (int i = 0; i < rand; i ++) {
            if (posy > 0 && (posy - 1 != theseey)) {
                posy--;
                grille[posx][posy] = CHEMIN;
            }
            else {
                return false;
            }
        }
        return true;
    }

    private boolean moveDown() {
        int rand = (int) (Math.random()) * 5;
        for (int i = 0; i < rand; i ++) {
            if (posy < (lignes - 1) && (posy + 1 != theseey)) {
                posy++;
                grille[posx][posy] = CHEMIN;
            }
            else {
                return false;
            }
        }
        return true;
    }

}

