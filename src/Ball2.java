import javax.swing.*;
import java.awt.*;

public class Ball2 {
    private final int FONT_SIZE = 70;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int level;
    private int health;
    private JLabel ball;
    private Icon ballImage;

    public Ball2(){
        this.xPos = 0;
        this.yPos = 0;
        this.width = 100;
        this.height = 100;
        this.level = 1;
        this.health = 10;
        this.ball = new JLabel(Integer.toString(this.health), JLabel.CENTER);

        this.ball.setFont(new Font("arial",Font.BOLD, FONT_SIZE));
        this.ball.setBounds(this.xPos,this.yPos,this.width,this.height);
        this.ball.setOpaque(false);
        this.ballImage = new ImageIcon("res/balls/yellow.png");
        this.ball.setIcon(this.ballImage);
    }

    public void update(){
        moveBall();
    }

    private void moveBall() {
        if (this.yPos>=0){ // 9 >= 0?
            moveDown();
        }
        if(this.yPos<720){
            moveUp();
        }
//        if(this.yPos<0) {
//            moveUp();
//        }
//        if (this.xPos>=0){
//            moveRight();
//        }
//        if(this.xPos>800){
//            moveLeft();
//        }
    }

    private void moveLeft() {
            this.xPos -=1;

    }

    private void moveRight() {
        this.xPos +=1;

    }

    private void moveUp() {
        this.yPos -=2;
    }

    private void moveDown() {
        this.yPos +=2;
    }

    public JLabel getBall(){
        return this.ball;
    }
    public void draw(Graphics2D graphics2D){
        this.ball.setBounds(this.xPos,this.yPos,this.width,this.height);
    }
}
