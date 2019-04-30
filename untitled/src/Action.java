public interface Action {

    void saveElem(Element e);
    //sauvegarder l'element en cours pour l'utiliser dans le listener

    Element getElem();
    //return l'element en cours associe au bouton

    void theseeLa(boolean bool);

    boolean isTheseeLa();

    void sortieLa(boolean bool);

    boolean isSortieLa();


}
