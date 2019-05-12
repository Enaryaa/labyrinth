/**
 * @author Anne-Sophie Besnard, Romain Lechartier
 */
public class MurBtn extends DefaultBtn {
    /**
     *
     * @param a Action
     */
    public MurBtn(Action a){
        super(new Mur(), a);
        this.setText("Mur");
    }
}
