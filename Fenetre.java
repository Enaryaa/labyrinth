import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{
	
	public Fenetre(){
		this.setSize(500, 500);
		this.setLocation(100, 100);
		this.drawGrid();
		
		GridLayout gestionnaire = new GridLayout(2, 1);
		this.setLayout(gestionnaire);
		
		JPanel panneau = new JPanel();
		JLabel etiquette = new JLabel("Algorithme Ariane");
		
		etiquette.setHorizontalAlignment(JLabel.CENTER);
	    etiquette.setVerticalAlignment(JLabel.BOTTOM);
		this.add(etiquette);

		panneau.add(new JButton("Grille existante"));
		//goto etape 2
		panneau.add(new JButton("Nouvelle Grille"));
		//choix grille vide ou aléatoire

	
		this.add(panneau, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void drawGrid(){
	GridLayout grille = new GridLayout(8,8);
	this.setLayout(grille);
	for (int i = 0; i < 8*8 ; i++ ) {
		Cell cell = new Cell(i);
		this.getContentPane().add(cell);
		}
		//fait la grille avec les JPanel
	}
}