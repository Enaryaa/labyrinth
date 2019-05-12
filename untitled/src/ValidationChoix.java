import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * la classe <code>ValidationChoix</code> correspond au listener qui gère
 * les boutons démarrer et déplacer, s'ils sont désactivés ou non
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class ValidationChoix implements MouseListener {
    /**
     *composante GrilleInterface
     */
    private GrilleInterface grille;
    /**
     *composante Bouton
     */
    private Bouton bouton;
    /**
     *composante bout qui correspond à l'id du botou
     */
    private int bout;
    /**
     *composante Algo
     */
    private Algo algo;

    /**
     *Constructeur qui instancie Algo et rend publique GrilleInterface
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
     *methode qui gère l'action au clique de la souris faites par l'utilisateur
     * appelle l'algorithme choisie dans le premier if (déterministe ou aléatoire)
     * puis la méthode, automatique ou manuel
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (grille.getAlgo() == null && grille.getMethode() == null){
            return;
        }
        this.bouton = (Bouton ) e.getComponent();
        this.bout = bouton.getId();
        if (bout == 15){
            if(bouton.isEnabled()) {
                algo.start();
                bouton.setEnabled(false);
            }
        }
        if(grille.getMethode().equals(Choix.MANUEL)){
            if (bout == 16 && grille.getMethode()=="Aléatoire") {
                algo.exitMazeManuel().start();
            }
            else if(bout == 16 && grille.getMethode()=="Manuel") {
                algo.mazeDeterManuel().start();
            }
        }
        else {
            bouton.setEnabled(false);
        }
    }

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
