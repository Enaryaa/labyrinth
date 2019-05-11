import javax.swing.*;

/**
 *@author Anne-Sophie Besnard, Romain Lechartier
 */
public class DefaultBtn extends JRadioButton {
    private Element elem;
    private Action action;

    /**
     *Constructeur destiné à la création des variables publiques
     * @param elem Element
     * @param action Action
     */
    public DefaultBtn(Element elem, Action action){
        this.elem = elem;
        this.action = action;
        this.addMouseListener(new Selection());
    }

    /**
     *Stock l'element du bouton cliqué dans l'action, qui permet de mettre
     * les bons parametres apres
     */
    public void setEleminAction(){
        this.action.saveElem(this.elem);
    }
}
