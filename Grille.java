import javax.swing.*;
import java.awt.*;

public class Grille extends Base {
    
    public Grille(int ligne, int col){
        super();
        GridLayout grille = new GridLayout(ligne,col);
        this.setLayout(grille);
        for (int i = 0; i < ligne*col ; i++ )
        {
            Cell cell = new Cell(i);
            this.add(cell);     
        }
    }
}