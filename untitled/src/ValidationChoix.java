import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class ValidationChoix implements MouseListener {
    /**
     *
     */
    private GrilleInterface grille;
    /**
     *
     */
    private Bouton bouton;
    /**
     *
     */
    private int bout;
    /**
     *
     */
    private Algo algo;

    /**
     *
     * @param grille GrilleInterface
     */
    public ValidationChoix(GrilleInterface grille){
        this.grille = grille;
        bout = 0;
        algo = new Algo(grille);
    }

    /**
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     *
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //que se soit deterministe ou aleatoire -> appelle le bon algo (premier if)
        //choisis la methode d'appelle,  si automatique -> affiche nb deplacement sinon appuie
        //sur espace pour faire avancer l'algo
        this.bouton = (Bouton ) e.getComponent();
        this.bout = bouton.getId();
        if (bout == 15){
            if(bouton.isEnabled()) {
                algo.start();
                bouton.setEnabled(false);
            }
        }
        if(grille.getMethode().equals(Choix.MANUEL)){
            if (bout == 16) {
                algo.exitMazeManuel().start();
            }
        }
        else {
            bouton.setEnabled(false);
        }
    }

    /**
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
