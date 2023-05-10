import javax.swing.*;
import java.awt.*;

public class Instructions extends JPanel {

    public Instructions(int x,int y,int width,int height){
        this.setSize(width,height);
        this.setLocation(x,y);
        this.setLayout(null);
        this.setBackground(Color.red);
        //  this.setOpaque(false);
        this.setVisible(true);
    }

}
