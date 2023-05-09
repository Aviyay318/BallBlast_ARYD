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
    private ArrayList<Shot> shots;
    private int index;
    private static boolean isShooting;
    private boolean isUp;

    public GamePanel(){
        createBackGround();
        this.setLayout(null);
        this.isUp = false;
        this.setBackground(Color.lightGray);
        this.setFocusable(true);
        this.requestFocus(true);
        this.addKeyListener(new KeyBoard(this));
        this.cannon = new Cannon(410,Constants.CANNON_Y_POSITION,100,100);
        this.ball = new Ball();
        isShooting = false;
        this.shots = new ArrayList<>();
        this.shot  = new Shot(this.cannon);
        createShots();
        this.index = 0;
        this.isOver = false;
        this.showScore = new JLabel(Integer.toString(0),JLabel.CENTER);
        this.showScore.setFont(new Font("arial",Font.BOLD,50));
        this.showScore.setForeground(Color.white);
        this.showScore.setBounds(Constants.WIDTH/3+30,0,200,100);
        this.add(this.showScore);

    }

    private void createShots() {
        for(int i = 0; i<30; i++){
            this.shots.add(new Shot(this.cannon));
        }
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

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public void newShot(){
        if (isShooting){
            this.shot = new Shot(this.cannon);
            this.shot.update();
        }


    }
    public void update(){
        this.cannon.update();
        this.ball.update();
        this.shots.get(this.index).update();
        updateIndex();
        checkCollision();
        updateScore();

    }

    private void updateIndex() {
        if (isShooting){
            if (this.index<29){
                if (this.shots.get(this.index).y==0){
                    this.index++;
                    isShooting= false;
                }
            }else if(this.index==29) {
                this.index  = 0;
                for (Shot shot: this.shots) {
                    shot.setShot();
                }
            }

        }

    }

    private void updateScore() {
        this.showScore.setText(Integer.toString(Ball.getScore()));
    }

    public Shot getShot() {
        return this.shots.get(this.index);
    }

    private void checkCollision(){
        if (this.ball.entityRectangle!=null){
            if (this.shots.get(this.index).entityRectangle!=null){
                if (Utils.collision(this.ball.entityRectangle,this.shots.get(this.index).entityRectangle)){
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
        this.shots.get(index).destroy();
        this.ball.destroy();
        System.out.println(true);
    }



    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(this.backGround,0,0,Constants.WIDTH,Constants.HEIGHT,null);
//        for (Shot shot: this.shots) {
//            shot.draw(graphics2D);
//        }
        this.shots.get(this.index).draw(graphics2D);
        this.cannon.draw(graphics2D);
        this.ball.draw(graphics2D);

    }


}
