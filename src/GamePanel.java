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
    private boolean isOver;
    private  JLabel showScore;
    private ArrayList<Ball> balls;
    private int ballIndex;
    private Instructions instructions;

    public GamePanel(){
        createBackGround();
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        this.setFocusable(true);
        this.requestFocus(true);
        this.addKeyListener(new KeyBoard(this));
        this.cannon = new Cannon(410,Constants.CANNON_Y_POSITION,100,100);
        this.balls = new ArrayList<>();
        createBalls();
        this.ballIndex = 0;
        this.isOver = false;
        createScore();
        this.instructions = new Instructions(140,100,Constants.INSTRUCTION_WIDTH,Constants.INSTRUCTION_HEIGHT);
        this.add(this.instructions);

    }

    private void createScore() {
        this.showScore = new JLabel(Integer.toString(0),JLabel.CENTER);
        this.showScore.setFont(new Font("arial",Font.BOLD,50));
        this.showScore.setForeground(Color.white);
        this.showScore.setBounds(Constants.WIDTH/3+30,0,200,100);
        this.add(this.showScore);
        this.showScore.setVisible(false);
    }

    private void createBalls() {
        for(int i = 0; i<6; i++){
            this.balls.add(new Ball());
        }
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
        if (this.instructions.isStart()){
            this.cannon.update();
            this.balls.get(this.ballIndex).update();
            updateBallIndex();
            checkCollision();
            updateScore();
            this.showScore.setVisible(true);
        }


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



    private void updateScore() {
        this.showScore.setText(Integer.toString(Ball.getScore()));
    }


    private void checkCollision(){
        if (this.balls.get(this.ballIndex).entityRectangle!=null){
            if (this.cannon.getShot().entityRectangle!=null){
                if (Utils.collision(this.balls.get(this.ballIndex).entityRectangle,this.cannon.getShot().entityRectangle)){
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
        if (this.instructions.isStart()){
            this.cannon.getShot().draw(graphics2D);
            this.cannon.draw(graphics2D);
            this.balls.get(this.ballIndex).draw(graphics2D);
        }



    }


}
