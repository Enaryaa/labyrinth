/**
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class SortieBtn extends DefaultBtn {
    /**
     *
     * @param a Action
     */
    public SortieBtn(Action a){
        super(new Sortie(),a);
        this.setText("Sortie");
    }
}