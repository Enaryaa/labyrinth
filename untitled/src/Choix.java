import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * la classe <code>Choix</code>  permet de récupérer l'algorithme que l'utilisateur
 * souhaite appliquer ainsi que la méthode de simulation
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class Choix implements ActionListener {
    /**
     *
     */
    private GrilleInterface grille;
    /**
     *détermine en constante le String Déterministe
     */
    public static final  String DETER = "Déterministe";
    /**
     *défini la constante en aléatoire
     */
    public static final String ALEA = "Aléatoire";
    /**
     *
     */
    public static final String MANUEL = "Manuel";
    /**
     *
     */
    public static final String AUTO = "Automatique";

    /**
     *
     * @param grille GrilleInterface
     */
    public Choix(GrilleInterface grille){
        this.grille = grille;
    }

    /**
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String choix = e.getActionCommand();
        if (choix.equals(DETER) || choix.equals(ALEA)) {
            grille.setAlgo(choix);
        }
        if (choix.equals(MANUEL) || choix.equals(AUTO)){
            grille.setMethode(choix);
        }
    }
}
