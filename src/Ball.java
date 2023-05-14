import java.awt.*;
import java.util.Random;

public class Ball extends Entity {
    static Random random = new Random();

    private int health;
    private int level;
    private Font font;
    private int stringX;
    private int stringY;
    private boolean shouldGoDown;
    private boolean shouldGoRight;
    private static int score;

    public Ball() {
        setImage(Constants.BALLS_PATH[random.nextInt(0, Constants.BALLS_PATH.length)]);
        createBall();
        this.shouldGoDown = true;
        this.entityRectangle.setBounds(this.x, this.y, this.width, this.height);
        score = 0;
    }

    private void setXPosition() {
        int state = random.nextInt(1, 3);
        switch (state) {
            case Constants.LEFT_P -> {
                this.x = Constants.LEFT_POSITION;
                this.shouldGoRight = true;
            }
            case Constants.RIGHT_P -> {
                this.x = Constants.RIGHT_POSITION;
                this.shouldGoRight = false;
            }
        }
    }

    private void setYPosition() {
        this.y = random.nextInt(Constants.Y_POSITION_MIN, Constants.Y_POSITION_MAX);
    }

    protected void setRectangle() {
        this.entityRectangle.setBounds(this.x, this.y, this.width - 10, this.height - 10);
    }

    private void setProperties() {
        switch (this.level) {
            case Constants.LEVEL_ONE_BALL -> {
                this.width = Constants.LEVEL_ONE_BALL_SIZE;
                this.height = Constants.LEVEL_ONE_BALL_SIZE;
                this.health = random.nextInt(1, 9);
                this.font = Constants.LEVEL_ONE_BALL_FONT;
            }
            case Constants.LEVEL_TWO_BALL -> {
                this.width = Constants.LEVEL_TWO_BALL_SIZE;
                this.height = Constants.LEVEL_TWO_BALL_SIZE;
                this.health = random.nextInt(10, 20);
                this.font = Constants.LEVEL_TWO_BALL_FONT;
            }
        }
    }

    // public void
    public void stringPosition() {
        switch (this.level) {
            case Constants.LEVEL_ONE_BALL -> {
                this.stringX = this.x + 30;
                this.stringY = this.y + 70;
            }
            case Constants.LEVEL_TWO_BALL -> {
                if (this.health >= 10) {
                    this.stringX = this.x + 25;
                } else {
                    this.stringX = this.x + 50;
                }
                this.stringY = this.y + 105;
            }
        }
    }

    private void createBall() {
        this.level = random.nextInt(1, 3);
        setXPosition();
        setYPosition();
        setProperties();
    }

    public void update() {
        stringPosition();
        moveBall();
        setRectangle();
    }

    public void draw(Graphics2D graphics2D) {
        super.draw(graphics2D);
        graphics2D.setFont(this.font);
        graphics2D.setColor(Color.white);
        if (this.level == Constants.QUESTION_BALL) {
            graphics2D.drawString("?", this.stringX, this.stringY);

        } else {
            graphics2D.drawString(Integer.toString(this.health), this.stringX, this.stringY);
        }
    }

    private void moveBall() {
        if (this.shouldGoDown) {
            moveDown();
        } else {
            moveUp();
        }
        if (this.shouldGoRight) {
            moveRight();
        } else {
            moveLeft();
        }
    }

    private void moveLeft() {
        if (this.x <= Constants.RIGHT_POSITION && this.x > 0) {
            this.x -= Constants.X_SPEED;
        } else {
            this.shouldGoRight = true;
        }
    }

    private void moveRight() {
        if (this.x >= 0 && this.x < Constants.RIGHT_POSITION) {
            this.x += Constants.X_SPEED;
        } else {
            this.shouldGoRight = false;
        }
    }

    private void moveUp() {
        if (this.y > 0) {
            this.y -= Constants.Y_SPEED;
        } else {
            this.shouldGoDown = true;
        }
    }

    private void moveDown() {
        if (this.y < Constants.GRASS_HEIGHT) {
            this.y += Constants.Y_SPEED;
        } else {
            this.shouldGoDown = false;
        }
    }

    public void destroy() {
        if (this.health == 0) {
            this.height = 0;
            this.width = 0;
        } else {
            setImage(Constants.BALLS_PATH[random.nextInt(0, Constants.BALLS_PATH.length)]);
            score++;
            this.health--;

        }
    }

    public static int getScore() {
        return score;
    }

    public boolean isAlive(){
        boolean isAlive = true;
        if (this.health==0){
            isAlive = false;
        }
        return isAlive;
    }

    public void setBall() {
        createBall();
    }


    public void restart() {
        createBall();
        this.shouldGoDown = true;
        this.entityRectangle.setBounds(this.x, this.y, this.width, this.height);
        score = 0;
    }
}