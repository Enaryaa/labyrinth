import javax.swing.*;
import java.awt.*;

public abstract class Base extends JFrame {
    public Base(){
        this.setSize(500, 500);
        this.setLocation(100, 100);
        this.setBackground(Color.PINK);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
