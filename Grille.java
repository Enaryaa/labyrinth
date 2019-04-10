import javax.swing.*;
import java.awt.*;

public class Grille extends JFrame {
    
    public Grille(int ligne, int col){
        
        GridLayout grille = new GridLayout(ligne,col);
        this.setLayout(grille);
        for (int i = 0; i < ligne*col ; i++ ) {
            Cell cell = new Cell(i);
            this.getContentPane().add(cell);
            cell.addMouseListener(cell);
        }
        //fait la grille avec les JPanel
    }
}