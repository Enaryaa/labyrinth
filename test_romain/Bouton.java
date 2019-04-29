import javax.swing.*;
import java.awt.*;

public class Bouton extends JButton
{
	private int id;

	public Bouton(int id)
	{
		this.id = id;
		//this.setPreferredSize(new Dimension(120,50));

		// Ajout du texte du bouton
		if(this.id==0)
		{
			this.setText("Commencer");
		}
		else if(this.id==1)
		{
			this.setText("Quitter");
		}
	}

	// Méthode pour récupérer l'id du bouton
	public int getId()
	{
		return id;
	}
}