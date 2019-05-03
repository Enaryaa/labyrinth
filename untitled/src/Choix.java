import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choix implements ActionListener {

    private GrilleInterface grille;
    public static final  String DETER = "Déterministe";
    public static final String ALEA = "Aléatoire";
    public static final String MANUEL = "Manuel";
    public static final String AUTO = "Automatique";

    public Choix(GrilleInterface grille){
        this.grille = grille;
    }

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
