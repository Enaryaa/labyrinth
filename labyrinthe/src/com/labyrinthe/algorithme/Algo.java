package com.labyrinthe.algorithme;

import com.labyrinthe.bouton.Bouton;
import com.labyrinthe.element.*;
import com.labyrinthe.grille.Cell;
import com.labyrinthe.grille.GrilleInterface;
import com.labyrinthe.listener.Choix;
import com.labyrinthe.listener.ValidationChoix;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/**
 * la classe <code>Algo</code> est la classe qui controle les algorithmes déterministe et aléatoire
 * ainsi que les comportements manuel et automatique
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class Algo {
    /**
     *
     */
    private static final int NB_ALEA = 100;
    /**
     *
     */
    private int alea;
    /**
     *
     */
    private GrilleInterface grille;
    /**
     *
     */
    private int deplacementPourMoyenne;
    /**
     *
     */
    private int nbDeplacement;
    /**
     *
     */
    private List<Cell> cells;
    /**
     *
     */
    private List<Cell> visited_cells;
    /**
     *
     */
    private Stack<Cell> treated_cells;
    /**
     *
     */
    public Timer timer;
    /**
     *
     */
    private Position positionThesee;
    /**
     *
     */
    private Position positionSortie;
    /**
     *
     */
    private boolean sortieTrouvee = false;

    /**
     *
     * @param grille GrilleInterface
     */
    public Algo(GrilleInterface grille) {
        this.grille = grille;
        deplacementPourMoyenne = 0;
        nbDeplacement = 0;
        alea = 0;
        this.cells = grille.getCells(); // contient les com.labyrinthe.grille.Cell (avec element) du labyrinthe
        this.visited_cells = new ArrayList<Cell>();
        this.treated_cells = new Stack<Cell>(); // contient les com.labyrinthe.grille.Cell traitées (pour retenir l'ordre de traitement)
        generatePosition();
    }

    /**
     *crée des positions pour les cellules dans le labyrinth pour générer les deplacements de thesee
     */
    private void generatePosition() {
        int compteur = 0;
        for (int x = 0; x < grille.getTaille(); x ++) {
            for (int y = 0; y < grille.getTaille(); y ++) {
                cells.get(compteur).setPosition(new Position(x, y));
                compteur ++;
            }
        }
    }

    /**
     *Permet, si le choix de l'algorithme est aléatoire de faire le comportement
     * correspondant soit à Automatique soit à Manuel.
     * Les fonctions sont alors différentes
     */
    private void aleatoire() {
        //debut de l'algo aleatoire
        if (grille.getMethode().equals(Choix.AUTO)) {
            grille.cacherFenetre();
            exitMazeAuto().start();
            //on ferme la fenetre dans la simuation automatique
        }
        else if (grille.getMethode().equals(Choix.MANUEL)){
            //si le choix est manuel
            exitMazeManuel().start();
        }
    }

    /**
     *Permet, si le choix de l'algorithme est déterministe de faire le comportement
     * correspondant soit à Automatique soit à Manuel.
     *
     */
    private void deterministe() {
        if (grille.getMethode().equals(Choix.AUTO)) {
            grille.cacherFenetre();
            mazeDeterAuto().start();

        }
        else if (grille.getMethode().equals(Choix.MANUEL)){
            mazeDeterManuel().start();
        }
    }

    /**
     *
     * @return Timer
     */
    private Timer mazeDeterAuto() {
        //conditions pour sortir du labyrinth
        Cell thesee = searchThesee();
        if (thesee != null) {
            positionThesee = thesee.getPosition();
        }
        //ici récupère la position de thesee après l'avoir cherché donc il est au debut
        Cell sortie = searchSortie();
        if (sortie != null) {
            positionSortie = sortie.getPosition();
        }
        //récupère la position de la sortie après l'avoir cherchée

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cell thesee = searchThesee();
                //recherche la position de thesee dans la grille a chaque deplacement
                // (pour mettre à jour sa position)
                //sinon refait un deplacement
                if (sortieTrouvee == true) {
                    goToSortie(thesee);
                    popupFin();
                    timer.stop();
                } else {
                    deplacementManuelDeter(thesee);
                    
                }
            }
        });

        return timer;
    }

    /**
     *
     * @return Timer
     */
    public Timer mazeDeterManuel() {
        //conditions pour sortir du labyrinth
        Cell thesee = searchThesee();
        if (thesee != null) {
            positionThesee = thesee.getPosition();
        }
        //ici récupère la position de thesee après l'avoir cherché donc il est au debut
        Cell sortie = searchSortie();
        if (sortie != null) {
            positionSortie = sortie.getPosition();
        }
        //récupère la position de la sortie après l'avoir cherchée

        timer = new Timer(60, new ActionListener() { //nouvelle instance de timer
            @Override
            public void actionPerformed(ActionEvent e) {
                Cell thesee = searchThesee();
                //recherche la position de thesee dans la grille a chaque deplacement
                // (pour mettre à jour sa position)
                //sinon refait un deplacement
                if (sortieTrouvee) {
                    goToSortie(thesee);
                    JOptionPane.showMessageDialog(null, "Le nombre de deplacement est de "+deplacementPourMoyenne,
                        "Fin", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    deplacementManuelDeter(thesee);
                }
                timer.stop();
            }
        });

        return timer;
    }

    /**
     *
     * @param thesee Cell
     */
    private void deplacementManuelDeter(Cell thesee) {
        Cell nextChemin = getCellNextChemin();
        if (nextChemin != null)
        {
            nextChemin.setPropriete(thesee.getPropriete());
            thesee.setPropriete(new Chemin());
            thesee = nextChemin;
            this.visited_cells.add(thesee);
            this.treated_cells.push(thesee);
            deplacementPourMoyenne += 1;
        }
        Position.Direction direction = getDeterministDirection(thesee);
        if (direction!=null)
        {
            Cell sortie = searchSortie();
            if (deplacementValide(thesee, direction))
            {
                Cell next = getCellByPosition(thesee.getPosition().getNextPosition(direction));
                if (next.getPosition().equals(sortie.getPosition()))
                {
                    sortieTrouvee = true;
                }
                else
                {
                    next.setPropriete(new NextChemin());
                }
            }
            else
            {
                deplacementManuelDeter(thesee);
            }
        }
        else
        {
            thesee = treated_cells.pop();
            if (treated_cells.empty()==true)
            {
                JFrame fenetre_coince = new JFrame();
                fenetre_coince.setSize(500, 100);
                JLabel message = new JLabel("Il n'y a pas de sortie, com.labyrinthe.element.Thesee est condamné !");
                fenetre_coince.add(message);
                fenetre_coince.setVisible(true);
            }
            Cell next = treated_cells.pop();
            next.setPropriete(new NextChemin());
        }
    }


    /**
     *
     * @param thesee Cell
     * @return Position.Direction
     */
    private Position.Direction getDeterministDirection(Cell thesee) {
        if (thesee.getPosition().getY() > 0 && thesee.getCardinal(0) == false && this.visited_cells.indexOf(getCellByPosition(thesee.getPosition().getNextPosition(Position.Direction.NORD)))==-1)
        {
            thesee.setCardinal(0,true);
            return Position.Direction.NORD;
        }
        thesee.setCardinal(0,true);
        if (thesee.getPosition().getX() > 0 && thesee.getCardinal(1) == false && this.visited_cells.indexOf(getCellByPosition(thesee.getPosition().getNextPosition(Position.Direction.OUEST)))==-1)
        {
            thesee.setCardinal(1,true);
            return Position.Direction.OUEST;
        }
        thesee.setCardinal(1,true);
        if (thesee.getPosition().getY() < (grille.getTaille()-1) && thesee.getCardinal(2) == false && this.visited_cells.indexOf(getCellByPosition(thesee.getPosition().getNextPosition(Position.Direction.SUD)))==-1)
        {
            thesee.setCardinal(2,true);
            return Position.Direction.SUD;
        }
        thesee.setCardinal(2,true);
        if (thesee.getPosition().getX() < (grille.getTaille()-1) && thesee.getCardinal(3) == false && this.visited_cells.indexOf(getCellByPosition(thesee.getPosition().getNextPosition(Position.Direction.EST)))==-1)
        {
            thesee.setCardinal(3,true);
            return Position.Direction.EST;
        }
        thesee.setCardinal(3,true);
        return null;
    }

    /**
     *permet de savoir si le prochain déplacement de Thésée choisi,
     * en fonction de la direction, est possible ou non
     * @param thesee Cell
     * @param direction Position.Direction
     * @return boolean
     */
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

    /**
     *
     * @return Timer
     */
   private Timer exitMazeAuto() {
        //conditions pour sortir du labyrinth
    Cell thesee = searchThesee();
    if (thesee != null) {
        positionThesee = thesee.getPosition();
    }
        //ici récupère la position de thesee après l'avoir cherché donc il est au debut
    Cell sortie = searchSortie();
    if (sortie != null) {
        positionSortie = sortie.getPosition();
    }
        //récupère la position de la sortie après l'avoir cherchée

       timer = new Timer(1, new ActionListener() { //nouvelle instance de timer
           @Override
           public void actionPerformed(ActionEvent e) {
               Cell thesee = searchThesee();
               //recherche la position de thesee das la grille a chaque deplacementAlea
               // (pour mettre à jour sa position)
               if (thesee.getPosition().equals(sortie.getPosition())) {
                   //si thesee est sur la sortie alors stop le timer
                   alea ++;
                   if (alea != NB_ALEA){
                    replacerThesee(thesee, sortie);
                       //reinitialiser les positions de sortie et de thesee
                    nbDeplacement += deplacementPourMoyenne;
                       //sinon stock le nb de deplacementAlea dans un tableau ou liste pour en faire la moyenne
                    deplacementPourMoyenne = 0;
                       //et remettre à 0 le deplacementpourmoyenne
                }
                else {
                       popupFin();
                  timer.stop();
              }
          } else {
                   //sinon refait un deplacementAlea
           deplacementAlea(thesee);
       }
   }
});

       return timer;
   }

    /**
     *
     * @return Timer
     */
    public Timer exitMazeManuel() {
        //conditions pour sortir du labyrinth
    Cell thesee = searchThesee();
    if (thesee != null) {
        positionThesee = thesee.getPosition();
    }
        //ici récupère la position de thesee après l'avoir cherché donc il est au debut
    Cell sortie = searchSortie();
    if (sortie != null) {
        positionSortie = sortie.getPosition();
    }
        //récupère la position de la sortie après l'avoir cherchée

        timer = new Timer(60, new ActionListener() { //nouvelle instance de timer
            @Override
            public void actionPerformed(ActionEvent e) {
                Cell thesee = searchThesee();
                //recherche la position de thesee das la grille a chaque deplacementAlea
                // (pour mettre à jour sa position)
                //sinon refait un deplacementAlea
                if (sortieTrouvee) {
                    goToSortie(thesee);
                    JOptionPane.showMessageDialog(null, "Le nombre de deplacement est de "+deplacementPourMoyenne,"Fin", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    deplacementManuelAlea(thesee);
                }
                timer.stop();
            }
        });

        return timer;
    }

    /**
     *méthode qui créer une popup de fin avec le nombre de déplacement
     * le bouton permet de fermer le programme
     */
    private void popupFin(){
        int moyenne = (nbDeplacement/100);
        JFrame popup = new JFrame();
        popup.setSize(350, 100);
        popup.setLocationRelativeTo(null);
        popup.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        popup.setResizable(false);
        JPanel panneau = new JPanel();
        if(grille.getAlgo().equals(Choix.ALEA)) {
            JLabel message = new JLabel("Le nombre de deplacement moyen est de " + moyenne);
            panneau.add(message);
        }
        else {
            JLabel message = new JLabel("Le nombre de deplacement est de " + deplacementPourMoyenne);
            panneau.add(message);
        }
        Bouton quitter = new Bouton(20);
        quitter.setText("Fermer");
        quitter.addMouseListener(new ValidationChoix(grille));
        panneau.add(quitter);
        popup.add(panneau);
        popup.setVisible(true);
    }

    /**
     *Permet de faire le déplacement  de l'algorithme aléatoire en manuel
     * la prochaine position choisie par Thésée sera alors affichée en Cyan
     * @param thesee Cell
     */
    private void deplacementManuelAlea(Cell thesee) {
        Cell nextChemin = getCellNextChemin();
        if (nextChemin != null) {
            nextChemin.setPropriete(thesee.getPropriete());
            thesee.setPropriete(new Chemin());
            thesee = nextChemin;
            deplacementPourMoyenne += 1;
        }
        Position.Direction direction = getRandomDirection(thesee);
        Cell sortie = searchSortie();
        if (deplacementValide(thesee, direction)) {
            Cell next = getCellByPosition(thesee.getPosition().getNextPosition(direction));
            if (next.getPosition().equals(sortie.getPosition())) {
                sortieTrouvee = true;
            } else {
                next.setPropriete(new NextChemin());
            }
        } else {
            deplacementManuelAlea(thesee);
        }
    }

    /**
     *permet de mettre Thésée à la position sortie,
     * son ancienne position est redevenu un chemin et la simulation s'arrête
     * @param thesee Cell
     */
    private void goToSortie(Cell thesee) {
        Cell sortie = searchSortie();
        sortie.setPropriete(thesee.getPropriete());
        thesee.setPropriete(new Chemin());
    }

    /**
     *appelle la direction random (nord, sud,est,ouest), si  la methode deplacementAlea est possible
     *récupère la prochaine cellule où thesee se rend,lui donne les propriété de thesee
     * (donc thesee se trouve sur la cellule)
     *   l'ancienne position de thesee redevient un chemin
     * @param thesee Cell
     */
    private void deplacementAlea(Cell thesee) {
        Position.Direction direction = getRandomDirection(thesee);
        if (deplacementValide(thesee, direction)) {
            Cell next = getCellByPosition(thesee.getPosition().getNextPosition(direction));
            next.setPropriete(thesee.getPropriete());
            thesee.setPropriete(new Chemin());
            deplacementPourMoyenne += 1;
        } else {
            deplacementAlea(thesee);
        }
    }

    /**
     * en fonction du resultat du rand (1,2,3 ou 4) et test la position de thesee
     * pour savoir si le deplacementAlea est possible
     * @param thesee Cell
     * @return Position.Direction
     */
    private Position.Direction getRandomDirection(Cell thesee) {

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

    /**
     *permet de choisir la méthode appelée en fonction du choix
     * de l'algorithme
     */
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

    /**
     * recupère la position initiale de thesee
     * et remet  la propriete de thesee sur la cellule
     * @param thesee Cell
     * @param sortie Cell
     */
    private void replacerThesee(Cell thesee, Cell sortie){
        for (Cell cell : cells){
            if (cell.getPosition().equals(positionThesee)){
                cell.setPropriete(thesee.getPropriete());
                sortie.setPropriete(new Sortie());
            }
        }
    }

    /**
     *Permet de rechercher la sortie dans la grille
     * @return Cell
     */
    private Cell searchSortie() {
        for (Cell cell : cells) {
            if (cell.getPropriete() instanceof Sortie) {
                return cell;
            }
        }
        return null;
    }

    /**
     * Permet de rechercher thesee dans la grille
     * @return Cell
     */
    private Cell searchThesee() {
        for (Cell cell : cells) {
            if (cell.getPropriete() instanceof Thesee) {
                return cell;
            }
        }
        return null;
    }

    /**
     * recupère la cellule en fonction de la position donnée par la direction (nord, sud , est , ouest)
     * @param position Position
     * @return Cell
     */
    private Cell getCellByPosition(Position position) {
        for (Cell cell : cells) {
            if (cell.getPosition().equals(position)) {
                return cell;
            }
        }
        return null;
    }

    /**
     * recupère la cellule qui sera la prochaine case où Thésée se rendra
     * @return Cell
     */
    private Cell getCellNextChemin() {
        for (Cell cell : cells) {
            if (cell.getPropriete() instanceof NextChemin) {
                return cell;
            }
        }
        return null;
    }
}
