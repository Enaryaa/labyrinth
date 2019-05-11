import java.util.List;

/**
 *
 */
public interface GrilleInterface {
    /**
     *
     * @return
     */
     int getTaille();

    /**
     *
     * @return
     */
     List<Cell> getCells();

    /**
     *
     * @return
     */
     String getAlgo();

    /**
     *
     * @return
     */
     String getMethode();

    /**
     *
     * @param s
     */
     void setAlgo(String s);

    /**
     *
     * @param s
     */
     void setMethode(String s);

    /**
     *
     */
    void cacherFenetre();

    /**
     *
     * @return
     */
	 byte getTheseeX();

    /**
     *
     * @return
     */
	 byte getTheseeY();

    /**
     *
     * @return
     */
	 byte getSortieX();

    /**
     *
     * @return
     */
	 byte getSortieY();

    /**
     *
     * @param i
     * @return
     */
	 char getEtat(int i);
}
