import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RecupTexte implements ActionListener {
    private JButton bouton;
    private JTextField text;
    private Base base;

    public RecupTexte(JButton bouton, JTextField text, Base base) {
        this.bouton = bouton;
        this.text = text;
        this.base = base;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try{
            String bouton = event.getActionCommand();
            if (bouton.equals("Generer"))
             	Integer.parseInt(text.getText());
          
        }
        catch (NumberFormatException e){
            System.err.println("Taille non valide, veuillez reessayer");
        }
    }
}
