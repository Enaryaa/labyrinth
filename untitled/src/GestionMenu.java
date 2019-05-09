import javax.swing.*;
import java.awt.*;
import javax.naming.ldap.Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionMenu implements ActionListener {

    private GrilleRand grillr;
    private GrilleChargee grilleC;
    private GrilleInterface grille;
    private Grille gril;
    private JPanel grillep;

    public GestionMenu(GrilleRand grillr){
        /*this.grillr = grillr;
        this.grillep = grillep;*/
        this.grille = grillr;
    }

    public GestionMenu(GrilleChargee grilleC){
        /*this.grilleC = grilleC;
        this.grillep = grillep;*/
        this.grille = grilleC;
    }

    public GestionMenu(Grille gril){
        /*this.gril = gril;
        this.grillep = grillep;*/
        this.grille = gril;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nom = e.getActionCommand();

        if (nom == "Sauvegarder")
        {
            Fenetre fenetre_sauvegarde = new Fenetre();
            fenetre_sauvegarde.setSize(500, 200);
            //fenetre_sauvegarde.setLocationRelativeTo(griller);
            FlowLayout gestionnaire = new FlowLayout(FlowLayout.CENTER,0,0);
            fenetre_sauvegarde.setLayout(gestionnaire);
            JLabel choix = new JLabel("Nom du fichier de sauvegarde :");
            choix.setPreferredSize(new Dimension(fenetre_sauvegarde.getWidth()-20,(fenetre_sauvegarde.getHeight()-34)/2));
            JTextField champ = new JTextField();
            champ.setPreferredSize(new Dimension(fenetre_sauvegarde.getWidth()-20,(fenetre_sauvegarde.getHeight()-34)/2));
            ObservateurSauvegarde sauvegarde = new ObservateurSauvegarde(fenetre_sauvegarde,grille);
            champ.addActionListener(sauvegarde);
            fenetre_sauvegarde.add(choix);
            fenetre_sauvegarde.add(champ);
            fenetre_sauvegarde.setVisible(true);
        }
        if (nom == "Effacer"){
            grille = new Grille(gril.getTaille(),gril.getTaille());
        }

        if (nom == "Rafraichir"){
           grille = new GrilleRand(grillr.getTaille(),grillr.getTaille());
        }
    }
}
