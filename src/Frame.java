import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
private Image icon;
    public Frame(TopPanel topPanel,GamePanel game){
        createIcon();
       this.setSize(Constants.WIDTH,Constants.HEIGHT);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setLayout(new BorderLayout());
       this.setResizable(false);
       this.setTitle("Ball Blast");
       this.setLocationRelativeTo(null);
       this.add(topPanel,BorderLayout.NORTH);
        this.add(game,BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void createIcon() {
        try {
            this.icon = ImageIO.read(new File("res/icon.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        this.setIconImage(this.icon);
    }

}
