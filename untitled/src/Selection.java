import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Selection implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        final DefaultBtn defaultbtn = (DefaultBtn) e.getComponent();
        defaultbtn.setEleminAction();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
