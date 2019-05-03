import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ValidationChoix implements MouseListener {
    /*des grilles
     */
    private GrilleInterface grille;

    public ValidationChoix(GrilleInterface grille){
        this.grille = grille;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //que se soit deterministe ou aleatoire -> appelle le bon algo (premier if)
        //choisis la methode d'appelle,  si automatique -> affiche nb deplacement sinon appuie
        //sur espace pour faire avancer l'algo
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
