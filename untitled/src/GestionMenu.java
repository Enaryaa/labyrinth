import javax.naming.ldap.Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionMenu implements ActionListener {

    private GrilleRand grillr;
    private GrilleChargee grilleC;
    private GrilleInterface grille;
    private Grille gril;

    public GestionMenu(GrilleRand grillr){
        this.grillr = grillr;
    }

    public GestionMenu(GrilleChargee grilleC){
        this.grilleC = grilleC;
    }

    public GestionMenu(Grille gril){
        this.gril = gril;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nom = e.getActionCommand();

        if (nom == "Sauvegarder"){

        }
        if (nom == "Effacer"){
            grille = new Grille(gril.getTaille(),gril.getTaille());
        }

        if (nom == "Rafraichir"){
           grille = new GrilleRand(grillr.getTaille(),grillr.getTaille());
        }
    }
}
