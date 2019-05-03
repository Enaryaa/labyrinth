import java.util.ArrayList;
import java.util.List;

public class Algo {

    private static final int NB_ALEA = 100;

    private GrilleInterface grille;
    private int deplacementPourMoyenne;
    private List<Cell> cells;

    public Algo(GrilleInterface grille) {
        this.grille = grille;
        deplacementPourMoyenne = 0;
        this.cells = grille.getCells();// contient les Cell (avec element) du labyrinthe
        geneatePosition();
    }

    public void aleatoire() {
        if (grille.getMethode().equals(Choix.AUTO)) {
            showAverage();
        }
    }

    private void geneatePosition() {
        int compteur = 0;
        for (int x = 0; x < grille.getTaille() -1; x ++) {
            for (int y = 0; y < grille.getTaille() -1; y ++) {
                cells.get(compteur).setPosition(new Position(x, y));
                compteur ++;
            }
        }
    }

    private void showAverage() {
        for (int i = 0; i < 100 ; i ++) {
            exitMaze();
        }
        int result = deplacementPourMoyenne / 100;
        //ici affichier la moyenne
    }

    private void exitMaze() {
        Cell thesee = searchThesee();
        Cell sortie = searchSortie();
        if (thesee == null || sortie == null) {
            return;
        }
        int nbDeplacement = 0;
        while (!thesee.getPosition().equals(sortie.getPosition())) {
            //ici faire algo déplacement
        }
        deplacementPourMoyenne += nbDeplacement;
    }

    private Cell searchSortie() {
        for (Cell cell : cells) {
            if (cell.getPropriete() instanceof Sortie) {
                return cell;
            }
        }
        return null;
    }

    private Cell searchThesee() {
        for (Cell cell : cells) {
            if (cell.getPropriete() instanceof Thesee) {
                return cell;
            }
        }
        return null;
    }

    public void deterministe() {

    }

    public void start() {
        if (grille.getAlgo().equals(Choix.ALEA)) {
            aleatoire();
        } else if (grille.getAlgo().equals(Choix.DETER)) {
            deterministe();
        } else {
            aleatoire();
        }
    }
}
