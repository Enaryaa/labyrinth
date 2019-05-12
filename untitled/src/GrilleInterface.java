import java.util.List;

/**
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public interface GrilleInterface {
    /**
     *
     * @return int
     */
     int getTaille();

    /**
     *
     * @return List
     */
     List<Cell> getCells();

    /**
     *
     * @return String
     */
     String getAlgo();

    /**
     *
     * @return String
     */
     String getMethode();

    /**
     *
     * @param s String
     */
     void setAlgo(String s);

    /**
     *
     * @param s String
     */
     void setMethode(String s);

    /**
     *
     */
    void cacherFenetre();

    /**
     *
     * @return byte
     */
	 byte getTheseeX();

    /**
     *
     * @return byte
     */
	 byte getTheseeY();

    /**
     *
     * @return byte
     */
	 byte getSortieX();

    /**
     *
     * @return byte
     */
	 byte getSortieY();

    /**
     *
     * @param i inr
     * @return char
     */
	 char getEtat(int i);

    /**
     *
     * @return Fenetre
     */
     Fenetre getFenetre();
     Bouton getBoutonDemarrer();
     Bouton getBoutonDeplacer();
}
