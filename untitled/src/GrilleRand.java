import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrilleRand extends JPanel {
    private int colonnes;
    private int lignes;
    private Element e;
    private List<Element> box;
    private int[][] grille;
    private boolean[][] visited;
    private static final int CHEMIN = 0;
    private static final int MUR = 1;
    private static final int THESEE = 2;
    private static final int SORTIE = 3;
    private int posx;
    private int posy;
    private int theseex;
    private int theseey;
    private int sortiex;
    private int sortiey;

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

        this.colonnes = c;
        this.lignes = l;

        Fenetre f = new Fenetre(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT+100);
        FlowLayout div = new FlowLayout(FlowLayout.CENTER,0,0);
        f.setLayout(div);
        menuBarre(f);
        drawGrid(f);
        jouer(f);

        f.setVisible(true);
    }

    private void jouer(Fenetre f) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT-100));

        Bouton deter = new Bouton(12);
        deter.setText("Deterministe");
        panel.add(deter);
        Bouton alea = new Bouton(13);
        alea.setText("Aleatoire");
        panel.add(alea);

        ChoixAlgo o = new ChoixAlgo(this, f);
        alea.addMouseListener(o);
        ChoixAlgo c = new ChoixAlgo(this, f);
        deter.addMouseListener(c);

        f.getContentPane().add(panel);
    }

    private void menuBarre(Fenetre f) {
        JMenuBar barre = new JMenuBar();

        //Creation onglet fichier
        JMenu fichier = new JMenu("Fichier");
        barre.add(fichier);

        //contient une sauvegarde et erase et une generation de grille

        JMenuItem save = new JMenuItem("Sauvegarder");
        fichier.add(save);
        JMenuItem repaint = new JMenuItem("Raffraichir");
        fichier.add(repaint);

        save.addActionListener(new GestionMenu(f));
        repaint.addActionListener(new GestionMenu(f));

        f.setJMenuBar(barre);
    }

    private void drawGrid(Fenetre f) {
        grille = arrayFill();

        placerThesee();
        deplacement();
        placerSortie();

        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT));
        GridLayout gridLayout = new GridLayout(colonnes,lignes);
        p.setLayout(gridLayout);

        int index = 0;
        for (int i = 0; i < colonnes; i++){
            for (int j = 0; j < lignes; j++){
                int current = grille[i][j];
                if (current == MUR){
                    p.add(new Cell(index, box.get(MUR)));
                }
                else if(current == CHEMIN){
                    p.add(new Cell(index, box.get(CHEMIN)));
                }
                else if(current == THESEE){
                    p.add(new Cell(index, box.get(THESEE)));
                }
                else if(current == SORTIE){
                    p.add(new Cell(index, box.get(SORTIE)));
                }
                index++;
            }
        }
        f.getContentPane().add(p);
    }

    private int[][] arrayFill() {
        grille = new int[colonnes][lignes];
        visited = new boolean[colonnes][lignes];
        for (int i = 0; i < colonnes; i++){
            for (int j = 0; j < lignes; j++){
                boolean randXouY = new Random().nextBoolean();
                if (randXouY) {
                    grille[i][j] = MUR;
                } else {
                    grille[i][j] = CHEMIN;
                    visited[i][j] = false;
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

    private void placerSortie(){
        posx = (int) (Math.random()*(colonnes-1));
        posy = (int) (Math.random()*(lignes-1));

        if (grille[posx][posy] == CHEMIN && visited[posx][posy]) {
            grille[posx][posy] = SORTIE;
        }
        else{
            placerSortie();
        }
        sortiex = posx;
        sortiey = posy;
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
            if (posx > 0 && grille[posx-1][posy] != THESEE) {
                posx --;
                grille[posx][posy] = CHEMIN;
                visited[posx][posy] = true;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean moveRight() {
        int rand = (int) (Math.random()*((colonnes/2)))+1;
        for (int i = 0; i < colonnes-rand; i ++) {
            if (posx < colonnes-1 && grille[posx+1][posy] != THESEE) {
                posx++;
                grille[posx][posy] = CHEMIN;
                visited[posx][posy] = true;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean moveUp() {
        int  rand = (int) (Math.random()*((colonnes/2)))+1;
        for (int i = 0; i < colonnes-rand; i ++) {
            if (posy > 0 && grille[posx][posy-1] != THESEE) {
                posy--;
                grille[posx][posy] = CHEMIN;
                visited[posx][posy] = true;
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
            if (posy < colonnes-1 && grille[posx][posy+1] != THESEE) {
                posy++;
                grille[posx][posy] = CHEMIN;
                visited[posx][posy] = true;
            }
            else {
                return false;
            }
        }
        return true;
    }
}