import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel
{
	private int id_menu;
	private String type_menu;
	private Fenetre fenetre;

	public Menu(int id, Fenetre f)
	{
		super();
		this.fenetre = f;
		this.id_menu = id; // l'id sert à définir quel menu afficher
		this.setOpaque(true);
		GridLayout gestionnaire = new GridLayout(2,1); // Organisation en 2 parties par choix graphique
      	this.setLayout(gestionnaire);
      	
      	// Texte affiché dans la partie supérieure
      	// Texte de bienvenue
		if(this.id_menu==0)
		{
			this.type_menu="Bienvenue";
		}

		// Ajout de l'étiquette 
		JLabel etiquette_presentation = new JLabel(this.type_menu);
		etiquette_presentation.setHorizontalAlignment(JLabel.CENTER);
      	etiquette_presentation.setVerticalAlignment(JLabel.CENTER);
      	this.add(etiquette_presentation);
      	
      	// Partie basse du menu
      	JPanel panneau_bas = new JPanel();
      	GridLayout apparence_panneau_bas = new GridLayout(2,1);
      	panneau_bas.setLayout(apparence_panneau_bas);

      	// Partie qui contient les boutons de choix
      	JPanel panneau_boutons = new JPanel();

      	// Bouton de démarrage
      	if(this.id_menu==0)
      	{
      		Bouton commencer = new Bouton(0);
      		panneau_boutons.add(commencer,BorderLayout.CENTER);
      		Observateur_bouton observateur_bouton = new Observateur_bouton(fenetre);
      		commencer.addMouseListener(observateur_bouton);
      	}
      	panneau_bas.add(panneau_boutons);


      	// Partie qui contient le bouton Quitter
      	JPanel panneau_quitter = new JPanel();
      	Bouton quitter = new Bouton(1);
      	panneau_quitter.add(quitter,BorderLayout.SOUTH);
      	panneau_bas.add(panneau_quitter);

      	// Ajout de la partie basse du menu
      	this.add(panneau_bas);
	}

	public int getId()
	{
		return id_menu;
	}
}