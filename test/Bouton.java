import javax.swing.*;
import java.awt.*;


public class Bouton extends JButton{

	private int id;

	public Bouton(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}