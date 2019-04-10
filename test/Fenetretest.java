import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetretest extends JFrame implements ActionListener{

	public Fenetretest(){
        this.setSize(500, 500);
        this.setLocation(100, 100);
        this.setBackground(Color.PINK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        JButton nouveau = new JButton("nlle grille");
        JButton charger = new JButton("charger grille");
        panel.add(nouveau);
		panel.add(charger);
		nouveau.addActionListener(this);
		charger.addActionListener(this);

		  JButton vide = new JButton("vide");
        JButton rand = new JButton("random grille");
        panel.add(vide);
		panel.add(rand);
		vide.addActionListener(this);
		rand.addActionListener(this);
		vide.setVisible(false);
		rand.setVisible(false);

		 JButton deter = new JButton(" grille deter");
        JButton alea = new JButton(" grille alea");
        panel.add(deter);
		panel.add(alea);
		deter.addActionListener(this);
		alea.addActionListener(this);
		deter.setVisible(false);
		alea.setVisible(false);


		  JButton manuel = new JButton(" manuel");
        JButton auto = new JButton("auto");
        panel.add(manuel);
		panel.add(auto);
		manuel.addActionListener(this);
		auto.addActionListener(this);
		auto.setVisible(false);
		manuel.setVisible(false);

		this.add(panel, BorderLayout.CENTER);



    }

     public JButton getJButton(){
        return vide;
    }
/*s
     public JButton getJButton(){
        return nouveau;
    }
     
     public JButton getJButton1(){
        return charger;
    }*/


    @Override
	public void actionPerformed(ActionEvent e) {
		String bouton = e.getActionCommand();

		if (bouton.equals("nlle grille")){
			 this.getJButton().setVisible(true);

		}

		if (bouton.equals("charger grille")) {
			
		}
	}
}