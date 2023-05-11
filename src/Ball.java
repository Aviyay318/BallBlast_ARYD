import java.awt.*;
import java.util.Random;

public class Ball extends Entity{
    private int xSpeed;
    private boolean shouldGoDown;
    private boolean shouldGoRight;
    private  Random random ;
    private int health;
    private static int score;
    public Ball(){
        this.random = new Random();
        setImage(Constants.BALLS_PATH[random.nextInt(0,Constants.BALLS_PATH.length)]);
        createBall();
        this.shouldGoDown = true;
        this.xSpeed = 1;
        this.entityRectangle.setBounds(this.x,this.y,this.width,this.height);
        //this.health = random.nextInt(1,4);
        this.health = 10;
        score=0;
    }

    private void createBall() {
        setSize();
        setXPosition();
        setYPosition();
    }

    private void setYPosition() {
        this.y = random.nextInt(0,100);
    }

    private void setXPosition() {
        int state = random.nextInt(1,3);
        switch (state){
            case 1-> {this.x = Constants.LEFT_POSITION; this.shouldGoRight =true;}
            case 2-> {this.x =  Constants.RIGHT_POSITION; this.shouldGoRight =false;}
        }

    }

    private void setSize() {
        int size = random.nextInt(50,150);
        this.width = size;
        this.height = size;
    }

    public void update(){
     moveBall(); setRectangle();
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
            this.x-=this.xSpeed;
        }else {
            this.shouldGoRight = true;
        }
    }

    private void moveRight() {
        if (this.x>=0&&this.x<Constants.RIGHT_POSITION){
            this.x+=this.xSpeed;
        }else {
            this.shouldGoRight = false;
        }
    }


    private void moveUp() {
         if (this.y>0){
            this.y-=speed;
        }else {
             this.shouldGoDown = true;
         }
    }

    private void moveDown() {
        if (this.y<Constants.GRASS_HEIGHT){
            this.y+=speed;
        }else {
            this.shouldGoDown= false;
        }
    }
    public void destroy(){
        if (this.health==0){
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

    public void draw(Graphics2D graphics2D){
        super.draw(graphics2D);
        graphics2D.setFont(new Font("arial",0,this.width/2));
        graphics2D.setColor(Color.white);
        graphics2D.drawString(Integer.toString(this.health),this.x+40,this.y+80);

    }
    protected void setRectangle(){
        this.entityRectangle.setBounds(this.x,this.y,this.width/2,this.height/2);
    }


    public void setBall() {
        createBall();
        this.health = random.nextInt(1,4);
    }
}
