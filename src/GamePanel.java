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
    private JLabel showScore;
    private ArrayList<Ball> balls;
    private int ballIndex;
    private Instructions instructions;
    private boolean gameOver;
    private KeyBoard keyBoard;
    private static JButton retry;
    private JButton pause;

    private Icon retryIcon;

    public GamePanel(Instructions instructions) {
        createBackGround();
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        this.setFocusable(true);
        this.requestFocus(true);
        this.keyBoard = new KeyBoard(this);
        this.addKeyListener(this.keyBoard);
        this.cannon = new Cannon(Constants.CANNON_X_POSITION, Constants.CANNON_Y_POSITION, Constants.CANNON_SIZE, Constants.CANNON_SIZE);
        this.balls = new ArrayList<>();
        createBalls();
        this.ballIndex = 0;
        this.isOver = false;
        createScore();
       this.instructions=instructions;
        this.add(this.instructions);
        this.gameOver=false;
        retry = new JButton("Retry");

    }

    private void createScore() {
        this.showScore = new JLabel(Integer.toString(0), JLabel.CENTER);
        this.showScore.setFont(new Font("arial", Font.BOLD, 50));
        this.showScore.setForeground(Color.white);
        this.showScore.setBounds(Constants.WIDTH / 3 + 30, 0, 200, 100);
        this.add(this.showScore);
        this.showScore.setVisible(false);
    }

    private void createBalls() {
        for (int i = 0; i < Constants.BALLS_AND_SHOOTS_LIST_SIZE; i++) {
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


    public void update() {
        if (this.instructions.isStart() && !gameOver) {
            this.cannon.update();
            this.balls.get(this.ballIndex).update();
            updateBallIndex();
            checkCollision();
            updateScore();
            this.showScore.setVisible(true);
            retry.setVisible(false);
        } else if (this.gameOver) {
            Retry();
        }
    }

    private void createButtonRetry() {
        retry = new JButton("Retry");
        this.retryIcon = new ImageIcon(Constants.RETRY_ICON_PATH);
        retry.setBounds(300, 300, 200, 200);
        retry.setIcon(this.retryIcon);
        retry.setOpaque(false);
        retry.setContentAreaFilled(false);
        retry.setBorderPainted(false);
        this.add(retry);
        retry.setVisible(false);
    }

    private void Retry() {
        retry.setVisible(true);
        retry.addActionListener((e ->{
         retry.setVisible(false);
            restart();

        }));


    }

    private void createButtonPause() {
        this.retryIcon = new ImageIcon("res/newimag.png");
        this.pause.setBounds(30,30,200,200);
        this.pause.setIcon(this.retryIcon);
        this.pause.setOpaque(false);
        this.pause.setContentAreaFilled(false);
        this.pause.setBorderPainted(false);
        this.add(this.pause);
        this.pause.setVisible(true);
        this.pause.addActionListener((e -> {
            this.gameOver = true;
            System.out.println("?/?00" + this.gameOver);
            restart();

        }));
    }

    private void updateBallIndex() {
        if (this.ballIndex < this.balls.size() - 1) {
            if (this.balls.get(this.ballIndex).getHealth() == 0) {
                this.sounds.loadPopSound();
                this.sounds.playPopSound();
                this.ballIndex++;
            }
        } else if (this.ballIndex == this.balls.size() - 1) {
            this.ballIndex = 0;
            for (Ball ball : this.balls) {
                ball.setBall();
            }
        }

    }


    private void updateScore() {
        this.showScore.setText(Integer.toString(Ball.getScore()));
    }


    private void checkCollision() {
        if (this.balls.get(this.ballIndex).entityRectangle != null) {
            if (this.cannon.getShot().entityRectangle != null) {
                if (Utils.collision(this.balls.get(this.ballIndex).entityRectangle, this.cannon.getShot().entityRectangle)) {
                    hit();

                }
            }
            if (this.cannon.entityRectangle != null) {
                if (Utils.collision(this.balls.get(this.ballIndex).entityRectangle, this.cannon.entityRectangle)) {
                    this.gameOver = true;
                }
            }
        }


    }

    public void restart() {
        this.ballIndex = 0;
        this.balls.get(this.ballIndex).restart();
        this.gameOver = false;
        this.cannon.restart();
        this.setFocusable(true);
        this.requestFocus(true);
        this.keyBoard = new KeyBoard(this);
        this.addKeyListener(this.keyBoard);
        this.sounds.playMusic();
    }

    public boolean isGameOver() {
        return gameOver;
    }


    private void hit() {
        this.cannon.getShot().setShotVisible(false);
        this.balls.get(this.ballIndex).destroy();
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(this.backGround, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
        if (this.instructions.isStart()) {
            this.cannon.getShot().draw(graphics2D);
            this.cannon.draw(graphics2D);
            this.balls.get(this.ballIndex).draw(graphics2D);
        }


    }


}
