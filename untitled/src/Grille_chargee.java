import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grille_chargee extends JPanel implements GrilleInterface
{
	public static final int BITS_PER_BYTE = 8;
	private static final int CHEMIN = 0;
    private static final int MUR = 1;
    private static final int THESEE = 2;
    private static final int SORTIE = 3;
	private Fenetre fenetre;
    private Element e;
    private String methode;
    private String algo;
    private List<Element> box;
    private List<Cell> cells;
	private byte taille;
	private byte[] thesee;
	private byte[] sortie;
	private char[] etats;

	public Grille_chargee()
	{
		super();
		box = new ArrayList<>();
		box.add(new Chemin());
        // int = 0
		box.add(new Mur());
        // int = 1
		box.add(new Thesee());
        // int = 2
		box.add(new Sortie());
        // int = 3
		fenetre = new Fenetre(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT+200);
		FlowLayout div = new FlowLayout(FlowLayout.CENTER,0,0);
		fenetre.setLayout(div);


		this.thesee = new byte[2];
		this.sortie = new byte[2];
		int etats_int = 0;
		byte etats_byte = 0;
		int etats_unsigned = 0;
		fenetre.clearContent();
			// a ce moment, ajouter les etapes de choix pour arriver à la grille
		String fichier = "";
		Choix_fichier choix = new Choix_fichier(".");
		File repertoire_courant = new File(".");
		choix.setCurrentDirectory(repertoire_courant);
		choix.showOpenDialog(null);
		while(fichier.equals(""))
		{
			fichier = choix.getSelectedFile().getAbsolutePath();
		}
			// Ci-après, le code pour lire le fichier contenant le format de la grille
			// Ce code est à placer au moment où l'utilisateur a choisi de prendre une grille préexistante avec le filechooser
		try
		{
			FileInputStream flux = new FileInputStream(fichier);
			DataInputStream data = new DataInputStream(flux);
			taille = data.readByte();
			this.etats = new char[taille*taille];
			thesee[0] = data.readByte();
			thesee[1] = data.readByte();
			sortie[0] = data.readByte();
			sortie[1] = data.readByte();

			String etats_string = "";
			while (data.available()!=0)
			{
				etats_byte = data.readByte();
				etats_int = etats_byte & 0xFF;
				String etats_string_tmp = Integer.toBinaryString(etats_int);
				while (etats_string_tmp.length() != BITS_PER_BYTE)
				{
					etats_string_tmp = "0" + etats_string_tmp;
				}
				etats_string = etats_string + etats_string_tmp;
			}
			etats = etats_string.toCharArray();
			char tmp = 0;
			int j = 0;
			for (int i = 0 ; i < taille ; i++)
			{
				j = i;
				while(j < taille)
				{
					tmp = etats[(i*taille)+j];
					etats[(i*taille)+j] = etats[(i+(j*taille))];
					etats[(i+(j*taille))] = tmp;
					j++;
				}
			}
			menuBarre(fenetre);
			drawGrid(taille,thesee,sortie,etats);
			/*Barre_action barre = new Barre_action(fenetre);
			fenetre.add(barre);
			Bouton bouton_sauvegarde = new Bouton(19);
			Observateur_bouton_sauvegarde sauvegarde = new Observateur_bouton_sauvegarde(fenetre,grille);
			bouton_sauvegarde.addMouseListener(sauvegarde);
			bouton_sauvegarde.setPreferredSize(new Dimension(fenetre.getWidth()-100,100));
			fenetre.add(bouton_sauvegarde);*/
			fenetre.repaint();
			fenetre.setVisible(true);
		}
		catch(IOException e)
		{
			System.err.println("Erreur de lecture");
		}
	}

	private void drawGrid(byte taille, byte[] thesee, byte[] sortie, char[] etats)
	{
		cells = new ArrayList<>();
		
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(Fenetre.SCREEN_WIDTH,Fenetre.SCREEN_HEIGHT));
		GridLayout gridLayout = new GridLayout(taille,taille);
		p.setLayout(gridLayout);
    	int index = 0;
    	for (int i = 0 ; i < taille ; i++)
		{
			for (int j = 0 ; j < taille ; j++ , index++)
			{
				if ((j == thesee[1]) && i == thesee[0])
				{
					Cell cell = new Cell(index, box.get(THESEE));
					p.add(cell);
				}
				else if ((j == sortie[1]) && i == sortie[0])
				{
					Cell cell = new Cell(index, box.get(SORTIE));
					p.add(cell);
					cells.add(cell);
				}
				else if (etats[j+(i*taille)] == 1)
				{
					Cell cell = new Cell(index, box.get(MUR));
					p.add(cell);
					cells.add(cell);
				}

				else if (etats[j+(i*taille)] == 0)
				{
					Cell cell = new Cell(index, box.get(CHEMIN));
					p.add(cell);
					cells.add(cell);
				}
			}
		}
		p.setFocusable(true);
		p.requestFocus();
		fenetre.getContentPane().add(p);
	}

	private void menuBarre(Fenetre f)
	{
		/*JMenuBar barre = new JMenuBar();

        //Creation onglet fichier
		JMenu fichier = new JMenu("Fichier");
		barre.add(fichier);

        //contient une sauvegarde et erase et une generation de grille

		JMenuItem save = new JMenuItem("Sauvegarder");
		fichier.add(save);
		JMenuItem repaint = new JMenuItem("Rafraichir");
		fichier.add(repaint);

		save.addActionListener(new GestionMenu(this));
		repaint.addActionListener(new GestionMenu(this));

		f.setJMenuBar(barre);*/
	}

	@Override
    public int getTaille(){
        return this.taille;
    }

    @Override
    public String getAlgo() {
        return algo;
    }

    @Override
    public String getMethode() {
        return methode;
    }

    @Override
    public void setAlgo(String algo) {
        this.algo = algo;
    }

    @Override
    public void setMethode(String methode) {
        this.methode = methode;
    }

    @Override
    public void cacherFenetre() {
        fenetre.setVisible(false);
    }

    @Override
    public void addListener(ObservateurTouche observateurTouche) {
        this.addKeyListener(observateurTouche);
    }

    @Override
    public List<Cell> getCells() {
        return cells;
    }
}