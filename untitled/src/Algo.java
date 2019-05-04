import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Algo {

    private static final int NB_ALEA = 100;
    private int alea = 0;

    private GrilleInterface grille;
    private int deplacementPourMoyenne;
    private List<Cell> cells;

    private Position.Direction lastDirectionSelected;

    private Timer timer;

    private Position positionThesee;
    private Position positionSortie;

    public Algo(GrilleInterface grille) {
        this.grille = grille;
        deplacementPourMoyenne = 0;
        alea = 0;
        this.cells = grille.getCells(); // contient les Cell (avec element) du labyrinthe
        generatePosition();
    }

    private void generatePosition() {
        int compteur = 0;
        for (int x = 0; x < grille.getTaille(); x ++) {
            for (int y = 0; y < grille.getTaille(); y ++) {
                cells.get(compteur).setPosition(new Position(x, y));
                compteur ++;
                //crée des positions pour les cellules dans le labyrinth pour générer les deplacements de thesee
            }
        }
    }

    public void aleatoire() {
        //debut de l'algo aleatoire
        if (grille.getMethode().equals(Choix.AUTO)) {
            exitMaze().start();
            //timer est récupéré et lancé directement grace a start();
            //et stopper dans le timer directement -> stop();
        }
    }

    private void moyennealea() {
        //renvoi la moyenne des deplacements de thesees dans l'algo alea
        System.out.println(deplacementPourMoyenne);
    }

    private Timer exitMaze() {
        //conditions pour sortir du labyrinth
        Cell thesee = searchThesee();
        positionThesee = thesee.getPosition();
        //ici récupère la position de thesee après l'avoir cherché
        Cell sortie = searchSortie();
        positionSortie = sortie.getPosition();
        //récupère la position de la sortie après l'avoir cherchée

       timer = new Timer(60, new ActionListener() { //nouvelle instance de timer
           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println("debut timer");
               Cell thesee = searchThesee();
               //recherche la position de thesee das la grille a chaque deplacement
               // (pour mettre à jour sa position)
               if (thesee.getPosition().equals(sortie.getPosition())) {
                   //si thesee est sur la sortie alors stop le timer
                   System.out.println("sortie trouvée");
                   alea ++;
                  /* if (alea != 100){
                       //reinitialiser les positions de sortie et de thesee
                       //sinon stock le nb de deplacement dans un tableau ou liste pour en faire la moyenne
                       //et remettre à 0 le deplacementpourmoyenne
                   }*/
                   moyennealea();
                   timer.stop();
               } else {
                   //sinon refait un deplacement
                   System.out.println("deplacement");
                   deplacement(thesee);
               }
               System.out.println("fin timer");
           }
       });

       return timer;
    }

    private void deplacement(Cell thesee) {
        Position.Direction direction = getRandomDirection(thesee);
        //appelle la direction random (nord, sud,est,ouest)
        if (deplacementValide(thesee, direction)) {
            //si deplacement possible
            Cell next = getCellByPosition(thesee.getPosition().getNextPosition(direction));
            //récupère la prochaine cellule où thesee se rend
            next.setPropriete(thesee.getPropriete());
            //lui donne les propriété de thesee (donc thesee se trouve sur la cellule)
            thesee.setPropriete(new Chemin());
            //l'ancienne position de thesee redevient un chemin
            deplacementPourMoyenne += 1;
        } else {
            deplacement(thesee);
        }
    }

    private boolean deplacementValide(Cell thesee, Position.Direction direction) {
        //check si la direction choisi est possible
        Cell next = getCellByPosition(thesee.getPosition().getNextPosition(direction));
        if (next != null && !(next.getPropriete() instanceof Mur)) {
            //si null == en dehors de la grille
            return true;
        } else {
            return false;
        }
    }

    private Position.Direction getRandomDirection(Cell thesee) {
        //en fonction du resultat du rand (1,2,3 ou 4) et test la position de thesee
        //pour savoir si le deplacement est possible
        int rand = (int) (Math.random() * (4)) +1;
        if (rand == 1 && thesee.getPosition().getY() > 0 ) {
            return Position.Direction.NORD;
        } else if (rand == 2 && thesee.getPosition().getY() < grille.getTaille() ) {
            return Position.Direction.SUD;
        } else if (rand == 3 && thesee.getPosition().getX() < grille.getTaille()) {
            return Position.Direction.EST;
        } else if (rand == 4 && thesee.getPosition().getX() > 0) {
            return Position.Direction.OUEST;
        } else {
            return getRandomDirection(thesee);
        }
    }

    public void deterministe() {
        // a completer pour l'algo deterministe
    }

    public void start() {
        if (grille.getAlgo().equals(Choix.ALEA)) {
            aleatoire();
            //le choix de l'algo est aléatoire
        } else if (grille.getAlgo().equals(Choix.DETER)) {
            deterministe();
            //choix algo deterministe
        } else {
            aleatoire();
        }
    }

    private Cell searchSortie() {
        //recherche la sortie dans la grille
        for (Cell cell : cells) {
            if (cell.getPropriete() instanceof Sortie) {
                return cell;
            }
        }
        return null;
    }

    private Cell searchThesee() {
        //recherche thesee dans la grille
        for (Cell cell : cells) {
            if (cell.getPropriete() instanceof Thesee) {
                return cell;
            }
        }
        return null;
    }

    private Cell getCellByPosition(Position position) {
        //recupère la cellule en fonction de la position donnée par la direction (nord, sud , est , ouest)
        for (Cell cell : cells) {
            if (cell.getPosition().equals(position)) {
                return cell;
            }
        }
        return null;
    }
}
