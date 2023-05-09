import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private BufferedImage backGround;
    private Cannon cannon;
    private Ball ball;
    private Shot shot;
    private boolean isOver;
    private  JLabel showScore;
    public GamePanel(){
        createBackGround();
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        this.setFocusable(true);
        this.requestFocus(true);
        this.addKeyListener(new KeyBoard(this));
        this.cannon = new Cannon(410,740,100,100);
        this.ball = new Ball();
        this.shot= new Shot(this.cannon);
        this.isOver = false;
        this.showScore = new JLabel(Integer.toString(0),JLabel.CENTER);
        this.showScore.setFont(new Font("arial",Font.BOLD,50));
        this.showScore.setForeground(Color.white);
        this.showScore.setBounds(Constants.WIDTH/3+30,0,200,100);
        this.add(this.showScore);

    }

    public Ball getBall() {
        return ball;
    }

    private void createBackGround() {
        try {
            this.backGround  = ImageIO.read(new File("res/backGround2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Cannon getCannon() {
        return cannon;
    }

    public void update(){
        this.cannon.update();
        this.ball.update();
        this.shot.update();
        checkCollision();
        updateScore();

    }

    private void updateScore() {
        this.showScore.setText(Integer.toString(Ball.getScore()));
    }

    public Shot getShot() {
        return this.shot;
    }

    private void checkCollision(){
        if (this.ball.entityRectangle!=null){
                if (this.shot.entityRectangle!=null){
             if (Utils.collision(this.ball.entityRectangle,this.shot.entityRectangle)){
                hit();
               }
              }
            if (this.cannon.entityRectangle!=null){
                if (Utils.collision(this.ball.entityRectangle,this.cannon.entityRectangle)){
                    this.isOver=true;
                }
            }
        }


    }
    public boolean gameOver(){
        boolean gameOver = false;
        if (this.isOver){
            this.cannon.destroy();
            System.out.println("game over bicth");
            gameOver = true;
        }
        return gameOver;
    }
    private void hit(){
        this.shot.destroy();
        this.ball.destroy();
        System.out.println(true);
    }



    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(this.backGround,0,0,Constants.WIDTH,Constants.HEIGHT,null);
        this.shot.draw(graphics2D);
        this.cannon.draw(graphics2D);
        this.ball.draw(graphics2D);
    }


}
