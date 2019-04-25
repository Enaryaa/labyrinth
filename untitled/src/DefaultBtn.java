import javax.swing.*;

public class DefaultBtn extends JRadioButton {
    private Element elem;
    private Action action;

    public DefaultBtn(Element elem, Action action){
        this.elem = elem;
        this.action = action;
        this.addMouseListener(new Selection());
    }

    public void setEleminAction(){
        this.action.saveElem(this.elem);
        //stock l'element du bouton cliqué dans l'action, qui permet de mettre
        //les bons parametres apres
    }
}
