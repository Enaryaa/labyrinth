import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChoixAlgo implements MouseListener {

    private Bouton bouton;
    private int bout;
    private Grille grille;
    private GrilleRand grillerand;
    private Fenetre cible;

    public ChoixAlgo (Grille grille, Fenetre cible){
        this.grille = grille;
        this.cible = cible;
        bout = 0;
    }

    public ChoixAlgo(GrilleRand grillerand, Fenetre cible){
        this.grillerand = grillerand;
        this.cible = cible;
        bout = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.bouton = (Bouton ) e.getComponent();
        this.bout = bouton.getId();
        if (bout == 13 || bout == 12){
            cible.setVisible(false);
            Fenetre f = new Fenetre();
            f.menu4();
            f.visible();
        }

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
