/**
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class ChemBtn extends DefaultBtn {
    /**
     *
     * @param a Action
     */
    public ChemBtn(Action a){

        super(new Chemin(),a);
        this.setText("Chemin");
    }
}