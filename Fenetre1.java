import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre1 extends Base implements ActionListener{

    private static final int COL = 5;
    private static final int LIGNE = 5;

    public Fenetre1(){
        super();
        JPanel panneau = new JPanel();
        this.add(panneau, BorderLayout.CENTER);

        JButton generer = new JButton("Generer la grille");
        JTextField zone = new JTextField();

 

        this.add(zone, BorderLayout.CENTER);
        panneau.add(generer);


    }

@Override
    public void actionPerformed(ActionEvent e) {
        String bouton = e.getActionCommand();

        if (bouton.equals("Generer la grille")){
            Fenetre1 f1  = new Fenetre1();
            f1.setVisible(true);
            this.dispose();
        }
    }


}
