import java.awt.*;
import java.util.Random;
public class Ball extends Entity{
    static Random random = new Random();
    public static final int LEFT_POSITION = 0;
    public static final int RIGHT_POSITION = 800;
    public static final int LEVEL_ONE_BALL = 1;
    public static final int LEVEL_TWO_BALL = 2;
    public static final int LEVEL_ONE_BALL_SIZE = 100;
    public static final int LEVEL_TWO_BALL_SIZE = 150;
    public static final Font LEVEL_ONE_BALL_FONT = new Font("arial",Font.BOLD, 70);
    public static final Font LEVEL_TWO_BALL_FONT = new Font("arial",Font.BOLD, 90);
    public static final int SPEED = 3;

    private int health;
    private int level;
    private int speed;
    private Font font;
    private int stringX;
    private int stringY;
    private boolean shouldGoDown;
    private boolean shouldGoRight;
    private static int score;
    public Ball() {
        this.speed = SPEED;
        setImage(Constants.BALLS_PATH[random.nextInt(0, Constants.BALLS_PATH.length)]);
        createBall();
        this.shouldGoDown = true;
        this.entityRectangle.setBounds(this.x, this.y, this.width, this.height);
        score = 0;
    }
    private void setLevel(){
        int randomLevel = random.nextInt(1,3);
        switch (randomLevel){
            case 1-> {
                this.level = LEVEL_ONE_BALL;
            }
            case 2-> {
                this.level = LEVEL_TWO_BALL;
            }
        }
    }
    private void setXPosition() {
        int state = random.nextInt(1,3);
        switch (state){
            case 1-> {this.x = LEFT_POSITION; this.shouldGoRight = true;}
            case 2-> {this.x = RIGHT_POSITION; this.shouldGoRight = false;}
        }
    }
    private void setYPosition() {
        this.y = random.nextInt(40,100);
    }
    protected void setRectangle(){
        this.entityRectangle.setBounds(this.x,this.y,this.width-10,this.height-10);
    }
    private void setProperties(){
        switch(this.level){
            case LEVEL_ONE_BALL ->
            {
                this.width = LEVEL_ONE_BALL_SIZE;
                this.height=LEVEL_ONE_BALL_SIZE;
                this.health = random.nextInt(1,9);
                this.font = LEVEL_ONE_BALL_FONT;
            }
            case LEVEL_TWO_BALL ->
            {
                this.width = LEVEL_TWO_BALL_SIZE;
                this.height=LEVEL_TWO_BALL_SIZE;
                this.health = random.nextInt(10,20);
                this.font = LEVEL_TWO_BALL_FONT;
            }
        }
    }
    public void stringPosition(){
        switch(this.level){
            case LEVEL_ONE_BALL -> {
                this.stringX = this.x+30;
                this.stringY = this.y+70;
            }
            case LEVEL_TWO_BALL -> {
                if(this.health>=10){
                    this.stringX = this.x+25;
                }
                else{
                    this.stringX = this.x+50;
                }
                this.stringY = this.y+105;
            }
        }
    }
    private void createBall() {
        setLevel();
        setXPosition();
        setYPosition();
        setProperties();
       // this.sound.loadPopSound();
    }
    public void update(){
        stringPosition();
        moveBall();
        setRectangle();
    }
    public void draw(Graphics2D graphics2D){
        super.draw(graphics2D);
        graphics2D.setFont(this.font);
        graphics2D.setColor(Color.white);
        graphics2D.drawString(Integer.toString(this.health),this.stringX,this.stringY);
    }
    private void moveBall() {
        if (this.shouldGoDown){
            moveDown();
        }else {
            moveUp();
        }
        if (this.shouldGoRight){
            moveRight();
        }else {
            moveLeft();
        }
    }
    private void moveLeft() {
        if (this.x<=Constants.RIGHT_POSITION&&this.x>0){
            this.x-=this.speed-2;
        }else {
            this.shouldGoRight = true;
        }
    }
    private void moveRight() {
        if (this.x>=0&&this.x<Constants.RIGHT_POSITION){
            this.x+=this.speed-2;
        }else {
            this.shouldGoRight = false;
        }
    }
    private void moveUp() {
        if (this.y>0){
            this.y-=this.speed;
        }else {
            this.shouldGoDown = true;
        }
    }
    private void moveDown() {
        if (this.y<Constants.GRASS_HEIGHT){
            this.y+=this.speed;
        }else {
            this.shouldGoDown= false;
        }
    }
    public void destroy(){
        if (this.health==0){
           // this.sound.playPopSound();
            this.height=0;
            this.width=0;
        }else {
            this.health--;
            score++;
        }
    }
    public static int getScore() {
        return score;
    }
    public int getHealth() {
        return health;
    }
    public void setBall() {
        createBall();
        //this.health = random.nextInt(1,4);
    }
}