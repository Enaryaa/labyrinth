import javax.naming.ldap.Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionMenu implements ActionListener {

    private Fenetre cible;

    public GestionMenu(Fenetre cible){
        this.cible = cible;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String nom = e.getActionCommand();
       /* Controletaille controle = new Controletaille();
        int taille = controle.getTaille();
        */

        if (nom == "Sauvegarder"){

        }
        if (nom == "Effacer"){
           /* cible.clearContent();
            Grille grille = new Grille(taille,taille);
            */
        }
        if (nom == "Regenerer"){

        }
    }
}
