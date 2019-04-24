import javax.swing.*;

public class DefaultBtn extends JRadioButton {
    private Element elem;

    public DefaultBtn(Element elem){
        this.elem = elem;
        //ajout action (ou element selecteur)apres, qui garde en mémoire quel element mettre sur la grille
    }
}
