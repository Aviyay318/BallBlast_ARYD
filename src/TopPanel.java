import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private  JLabel showScore;
    public TopPanel(int width,int height){
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(new Color(51,130,254));
         this.showScore = new JLabel("High Score: "+Integer.toString(8423),JLabel.CENTER);
        this.showScore.setFont(new Font("arial",Font.BOLD,30));
        this.showScore.setForeground(Color.white);
        this.showScore.setBounds(15,0,200,100);
        this.add(this.showScore);
    }



}
