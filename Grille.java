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
<<<<<<< HEAD
            this.getContentPane().add(cell);
            cell.addMouseListener(cell);
=======
            this.add(cell);     
>>>>>>> 21785ea838139857141f573f191bb7b354e78e67
        }
    }
}