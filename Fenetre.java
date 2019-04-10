import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends Base implements ActionListener {

	public Fenetre(){
		super();
		
		GridLayout gestionnaire = new GridLayout(2, 1);
		this.setLayout(gestionnaire);

		JPanel panneau = new JPanel();
		JLabel etiquette = new JLabel("Algorithme Ariane");
		
		etiquette.setHorizontalAlignment(JLabel.CENTER);
	    etiquette.setVerticalAlignment(JLabel.BOTTOM);
		this.add(etiquette);

		JButton exis = new JButton("Grille existante");
		JButton news = new JButton("Nouvelle Grille");

		panneau.add(exis);
		panneau.add(news);
		exis.addActionListener(this);
		//goto etape 2
		news.addActionListener(this);
		//choix grille vide ou aleatoire


		this.add(panneau, BorderLayout.CENTER);

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String bouton = e.getActionCommand();

		if (bouton.equals("Nouvelle Grille")){
			Fenetre1 f1  = new Fenetre1();
			f1.setVisible(true);
			this.dispose();
		}
		if (bouton.equals("Grille existante")) {
			Fenetre f = new Fenetre();
			f.setVisible(true);
			this.dispose();
		}
	}
}