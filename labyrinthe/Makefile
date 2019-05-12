# makefile begins
# define a variable for compiler flags (JFLAGS)
# define a variable for the compiler (JC)  
# define a variable for the Java Virtual Machine (JVM)
# define a variable for a parameter. When you run make, you could use:


JFLAGS = -g -implicit:none -encoding UTF8
JC = javac
JVM = java 

.SUFFIXES: .java .class

.java.class:
		$(JC) $(JFLAGS) $(CLASSES)


# CLASSES is a macro consisting of N words (one for each java source file)
# When a single line is too long, use \<return> to split lines that then will be
# considered as a single line.

CLASSES = \
		com.labyrinthe.Main.java \
		com.labyrinthe.Fenetre.java \
		com.labyrinthe.bouton.Bouton.java \
		com.labyrinthe.bouton.SortieBtn.java \
		com.labyrinthe.bouton.MurBtn.java \
		com.labyrinthe.bouton.DefaultBtn.java \
		com.labyrinthe.listener.Selection.java \
		com.labyrinthe.bouton.ChemBtn.java \
		com.labyrinthe.bouton.TheseBtn.java \
		com.labyrinthe.bouton.Action.java \
		com.labyrinthe.listener.Coloration.java \
		com.labyrinthe.grille.Cell.java \
		com.labyrinthe.algorithme.Position.java \
		com.labyrinthe.listener.ValidationChoix.java \
		com.labyrinthe.element.Element.java \
		com.labyrinthe.element.Mur.java \
		com.labyrinthe.element.NextChemin.java \
		com.labyrinthe.element.Sortie.java \
		com.labyrinthe.element.Thesee.java \
		com.labyrinthe.element.Chemin.java \
		com.labyrinthe.fichier.ChoixFichier.java \
		com.labyrinthe.fichier.FiltreFichier.java \
		com.labyrinthe.listener.Observateur.java \
		com.labyrinthe.listener.ControleTaille.java \
		com.labyrinthe.grille.GrilleInterface.java \
		com.labyrinthe.grille.Grille.java \
		com.labyrinthe.grille.GrilleRand.java \
		com.labyrinthe.grille.GrilleChargee.java \
		com.labyrinthe.listener.ObservateurSauvegarde.java \
		com.labyrinthe.algorithme.Algo.java \
		com.labyrinthe.listener.Choix.java \
		com.labyrinthe.listener.GestionMenu.java \



# MAIN is a variable with the name of the file containing the main method

MAIN = com.labyrinthe.Main

# the default make target entry
# for this example it is the target classes

default: classes


# Next line is a target dependency line
# This target entry uses Suffix Replacement within a macro: 
# $(macroname:string1=string2)
# In the words in the macro named 'macroname' replace 'string1' with 'string2'
# Below we are replacing the suffix .java of all words in the macro CLASSES 
# with the .class suffix

classes: $(CLASSES:.java=.class)


# Next two lines contain a target for running the program
# Remember the tab in the second line.
# $(JMV) y $(MAIN) are replaced by their values

run: 
	$(JVM) $(MAIN)

# this line is to remove all unneeded files from
# the directory when we are finished executing(saves space)
# and "cleans up" the directory of unneeded .class files
# RM is a predefined macro in make (RM = rm -f)

clean:
	$(RM) *.class