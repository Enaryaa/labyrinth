import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GrilleRand extends Fenetre {
    private int colonnes;
    private int lignes;
    private Element e;
    private List<Element> box;
    private Chemin chemin;
    private Mur mur;
    private Thesee thesee;
    private Sortie sortie;


    public GrilleRand(int c, int l) {
        super();

        box = new ArrayList<>();
        box.add(chemin);
        box.add(mur);
        box.add(thesee);
        box.add(sortie);

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

}

