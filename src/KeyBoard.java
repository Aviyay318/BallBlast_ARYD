import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
    private GamePanel gamePanel;

    public KeyBoard(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT -> {this.gamePanel.getCannon().setLeft(true);}
            case KeyEvent.VK_RIGHT -> {this.gamePanel.getCannon().setRight(true);}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT -> {this.gamePanel.getCannon().setLeft(false);}
            case KeyEvent.VK_RIGHT -> {this.gamePanel.getCannon().setRight(false);}
        }
    }
}
