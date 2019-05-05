import java.awt.desktop.SystemSleepEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ObservateurTouche implements KeyListener {

    private Algo algo;

    public ObservateurTouche(){

    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("3");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("2");
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_A){
            algo.exitMazeManuel().start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("3");
    }
}