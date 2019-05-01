import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrilleRand extends Fenetre {
    private int colonnes;
    private int lignes;
    private Element e;
    private List<Element> box;
    private Element[][] grille;
    private Thesee thesee;
    private Mur mur;
    private Chemin chemin;
    private Sortie sortie;
    private int posx;
    private int posy;
    private int theseex;
    private int theseey;
    private int sortiex;
    private int sortiey;

    public GrilleRand(int c, int l) {
        super();
        JPanel panel = JPanel();

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

        drawGrid();

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
        placerSortie();
    }

    private Element[][] arrayFill() {
        Element [][] grille = new Element[colonnes][lignes];
        JPanel p = new JPanel();
        for (int i = 0; i < colonnes; i++){
            for (int j = 0; j < lignes; j++){
                Cell cell = new Cell(i);
                p.add(cell);
                boolean randXouY = new Random().nextBoolean();
                if (randXouY) {
                    grille[i][j] = mur = new Mur();
                    cell.getPropriete();
                } else {
                    grille[i][j] = chemin = new Chemin();
                    cell.getPropriete();
                }
            }
        }
        this.getContentPane().add(p);
        return grille;
    }

    private void placerThesee(){
        posx = (int) (Math.random()*(colonnes-1));
        posy = (int) (Math.random()*(lignes-1));

        grille[posx][posy] = thesee = new Thesee();

        theseex = posx;
        theseey = posy;
        System.out.println("pos T : " + posx + " ; " +posy);
    }

    private void placerSortie(){
        posx = (int) (Math.random()*(colonnes-1));
        posy = (int) (Math.random()*(lignes-1));

        if (grille[posx][posy] == chemin) {
            grille[posx][posy] = sortie = new Sortie();
        }
        else{
            placerSortie();
        }

        sortiex = posx;
        sortiey = posy;
        System.out.println("pos S : " + posx + " ; " +posy);
    }

    private void deplacement(){
        int nbchemin = (colonnes+lignes);
        boolean randDirect;

        for (int i = 0; i < nbchemin; i++){
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
        }
    }

    private boolean moveLeft() {
        int  rand = (int) (Math.random()*((colonnes/2)))+1;
        for (int i = 0; i < colonnes-rand; i ++) {
            if (posx > 0 && grille[posx-1][posy] != thesee) {
                posx --;
                grille[posx][posy] = chemin;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean moveRight() {
        int rand = (int) (Math.random()*((colonnes/2)))+1;
        for (int i = 0; i < colonnes-rand; i ++) {
            if (posx < colonnes-1 && grille[posx+1][posy] != thesee) {
                posx++;
                grille[posx][posy] = chemin;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean moveUp() {
        int  rand = (int) (Math.random()*((colonnes/2)))+1;
        for (int i = 0; i < colonnes-rand; i ++) {
            if (posy > 0 && grille[posx][posy-1] != thesee) {
                posy--;
                grille[posx][posy] = chemin;
            }
            else {
                return false;
            }
        }
        return true;
    }

    private boolean moveDown() {
        int rand = (int) (Math.random()*((colonnes/2)))+1;
        for (int i = 0; i < colonnes-rand; i ++) {
            if (posy < colonnes-1 && grille[posx][posy+1] != thesee) {
                posy++;
                grille[posx][posy] = chemin;
            }
            else {
                return false;
            }
        }
        return true;
    }
}