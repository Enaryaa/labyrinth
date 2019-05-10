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
		Main.java \
		Fenetre.java \
		Bouton.java \
		SortieBtn.java \
		MurBtn.java \
		DefaultBtn.java \
		Selection.java \
		ChemBtn.java \
		TheseBtn.java \
		Action.java \
		Coloration.java \
		Cell.java \
		Position.java \
		ValidationChoix.java \
		Element.java \
		Mur.java \
		NextChemin.java \
		Sortie.java \
		Thesee.java \
		Chemin.java \
		ChoixFichier.java \
		FiltreFichier.java \
		Observateur.java \
		ControleTaille.java \
		GrilleInterface.java \
		Grille.java \
		GrilleRand.java \
		GrilleChargee.java \
		ObservateurSauvegarde.java \
		Algo.java \
		Choix.java \
		GestionMenu.java \



# MAIN is a variable with the name of the file containing the main method

MAIN = Main 

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