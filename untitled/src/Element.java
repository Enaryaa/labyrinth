import java.awt.*;

public abstract class Element {
    protected Color couleur = Color.darkGray;
    protected boolean isTraversable = false;
    protected boolean isMovable = false;

    public Color getCouleur() {
        return couleur;
    }

    public boolean isTraversable() {
        return isTraversable;
    }

    public boolean isMovable() {
        return isMovable;
    }


}
