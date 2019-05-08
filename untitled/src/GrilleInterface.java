import java.util.List;

//pour faciliter l'utilisation du listener, l'interface va representer grille ou grillerand
//car similaire et différente donc il va prendre les similitudes pour les utiliser
public interface GrilleInterface {
     int getTaille();
     List<Cell> getCells();
     String getAlgo();
     String getMethode();
     void setAlgo(String s);
     void setMethode(String s);
     void cacherFenetre();
}
