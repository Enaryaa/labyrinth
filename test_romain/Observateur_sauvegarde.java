import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Observateur_sauvegarde implements ActionListener
{
	private String nom;
	private JFrame fenetre;

	public Observateur_sauvegarde(JFrame f)
	{
		super();
		this.fenetre = f;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.nom = e.getActionCommand();
		try
		{
			FileOutputStream flux = new FileOutputStream(nom);
			DataOutputStream data = new DataOutputStream(flux);
		}
		fenetre.setVisible(false);
	}
}