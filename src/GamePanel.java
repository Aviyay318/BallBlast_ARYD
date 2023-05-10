import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private BufferedImage backGround;
    private Cannon cannon;
    private Shot shot;
    private boolean isOver;
    private  JLabel showScore;
    private ArrayList<Shot> shots;
    private ArrayList<Ball> balls;
    private int shotIndex;
    private int ballIndex;
    private static boolean isShooting;


    public GamePanel(){
        createBackGround();
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        this.setFocusable(true);
        this.requestFocus(true);
        this.addKeyListener(new KeyBoard(this));
        this.cannon = new Cannon(410,Constants.CANNON_Y_POSITION,100,100);
        isShooting = false;
        this.shots = new ArrayList<>();
        this.balls = new ArrayList<>();
        createShots();
        createBalls();
        this.shotIndex = 0;
        this.ballIndex = 0;
        this.isOver = false;
        this.showScore = new JLabel(Integer.toString(0),JLabel.CENTER);
        this.showScore.setFont(new Font("arial",Font.BOLD,50));
        this.showScore.setForeground(Color.white);
        this.showScore.setBounds(Constants.WIDTH/3+30,0,200,100);
        this.add(this.showScore);

    }

    private void createBalls() {
        for(int i = 0; i<5; i++){
            this.balls.add(new Ball());
        }
    }

    private void createShots() {
        for(int i = 0; i<30; i++){
            this.shots.add(new Shot(this.cannon));
        }
    }


    public Ball getBall() {
        return balls.get(this.ballIndex);
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
        this.balls.get(this.ballIndex).update();
        this.shots.get(this.shotIndex).update();
        updateShotIndex();
        updateBallIndex();
        checkCollision();
        updateScore();

    }

    private void updateBallIndex() {
        if (this.ballIndex<5){
            if (this.balls.get(this.ballIndex).getHealth()==0){
                this.ballIndex++;
            }
        }else if (this.ballIndex==5){
            this.ballIndex=0;
            for (Ball ball: this.balls) {
                ball.setBall();
            }
        }

    }

    private void updateShotIndex() {
        if (isShooting){
            if (this.shotIndex <29){
                if (this.shots.get(this.shotIndex).y==0){
                    this.shotIndex++;
                    isShooting= false;
                }
            }else if(this.shotIndex ==29) {
                this.shotIndex = 0;
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
        return this.shots.get(this.shotIndex);
    }

    private void checkCollision(){
        if (this.balls.get(this.ballIndex).entityRectangle!=null){
            if (this.shots.get(this.shotIndex).entityRectangle!=null){
                if (Utils.collision(this.balls.get(this.ballIndex).entityRectangle,this.shots.get(this.shotIndex).entityRectangle)){
                    hit();
                }
            }
            if (this.cannon.entityRectangle!=null){
                if (Utils.collision(this.balls.get(this.ballIndex).entityRectangle,this.cannon.entityRectangle)){
                    this.isOver=true;
                }
            }
        }


    }
    public boolean gameOver(){
        boolean gameOver = false;
        if (this.isOver){
            this.cannon.destroy();
            System.out.println("game over bitch");
            gameOver = true;
        }
        return gameOver;
    }
    private void hit(){
        this.shots.get(shotIndex).destroy();
        this.balls.get(this.ballIndex).destroy();
        System.out.println(true);
    }



    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(this.backGround,0,0,Constants.WIDTH,Constants.HEIGHT,null);
//        for (Shot shot: this.shots) {
//            shot.draw(graphics2D);
//        }
        this.shots.get(this.shotIndex).draw(graphics2D);
        this.cannon.draw(graphics2D);
        this.balls.get(this.ballIndex).draw(graphics2D);

    }


}
