import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Retry extends JPanel {



    private JButton retry;
    private ImageIcon retryIcon;

    public Retry(int x,int y,int width,int height) {

        this.setBounds(0, 0, Constants.PANEL_THREE_WIDTH, Constants.PANEL_THREE_HEIGHT);
        this.setLayout(null);
        this.setLocation(x,y);
        this.setBackground(Color.BLUE);
        this.setVisible(true);
    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        try {
            graphics2D.drawImage(ImageIO.read(new File("images/backGround2.png")),0,0,900,1000,null);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}