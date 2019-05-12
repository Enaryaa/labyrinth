# makefile begins
# define a variable for compiler flags (JFLAGS)
# define a variable for the compiler (JC)  
# define a variable for the Java Virtual Machine (JVM)

JFLAGS = -g -implicit:none -encoding UTF8
JC = javac
JVM = java 

.SUFFIXES: .java .class

.java.class:
		$(JC) $(JFLAGS) $(CLASSES)


CLASSES = \
		Main.java \
        Fenetre.java \
        bouton/Bouton.java \
        bouton/SortieBtn.java \
        bouton/MurBtn.java \
        bouton/DefaultBtn.java \
        listener/Selection.java \
        bouton/ChemBtn.java \
        bouton/TheseBtn.java \
        bouton/Action.java \
        listener/Coloration.java \
        grille/Cell.java \
        algorithme/Position.java \
        listener/ValidationChoix.java \
        element/Element.java \
        element/Mur.java \
        element/NextChemin.java \
 		element/Sortie.java \
        element/Thesee.java \
        element/Chemin.java \
        fichier/ChoixFichier.java \
        fichier/FiltreFichier.java \
        listener/Observateur.java \
        listener/ControleTaille.java \
        grille/GrilleInterface.java \
        grille/Grille.java \
        grille/GrilleRand.java \
        grille/GrilleChargee.java \
        listener/ObservateurSauvegarde.java \
        algorithme/Algo.java \
        listener/Choix.java \
        listener/GestionMenu.java \


# MAIN is a variable with the name of the file containing the main method

MAIN = com.labyrinthe Main

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