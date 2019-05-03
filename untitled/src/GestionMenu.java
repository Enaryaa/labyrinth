import javax.naming.ldap.Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionMenu implements ActionListener {

    private Fenetre cible;
    private Grille grille;
    private GrilleRand grillerand;

    public GestionMenu(Fenetre cible){
        this.cible = cible;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nom = e.getActionCommand();

        if (nom == "Sauvegarder"){

        }
        if (nom == "Effacer") /*grille.createGrid(cible)*/;

        if (nom == "Regenerer"){

        }
    }
}
