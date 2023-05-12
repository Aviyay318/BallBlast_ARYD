import javax.swing.*;
import java.util.ArrayList;

public class Cannon extends Entity{
    private boolean left;
    private boolean right;
    private ArrayList<Shot> shots;
    private int shotIndex;
    private boolean isMoving;
    public Cannon(int x,int y,int width,int height){
        super(x,y,width,height);
        this.right = false;
        this.left  =false;
        setImage(Constants.CANNON_PATH);
        this.shots = new ArrayList<>();
        createShots();
        this.shotIndex = 0;
    }


    public void setRight(boolean right) {
        this.right = right;
    }
    private void createShots() {
        for(int i = 0; i<6; i++){
            this.shots.add(new Shot(this.x));
        }
    } private void updateShotIndex() {
        if (this.isMoving){
            if (this.shotIndex <5){
                if (this.shots.get(this.shotIndex).y==0){
                    System.out.println(this.shots.get(this.shotIndex)+"   "+this.shotIndex);
                }
            }else if(this.shotIndex==5) {
                this.shotIndex = 0;
                for (Shot shot: this.shots) {
                    shot.setShot();
                }
            }

        }

    }

    public void update(){
        moveCannon();
        isMoving();
        if (this.shots.size()>0){
            this.shots.get(this.shotIndex).update(this.isMoving,this.x);
        }
        updateShotIndex();
        setRectangle();

    }
    public Shot getShot() {
        return this.shots.get(this.shotIndex);
    }

    private void moveCannon(){
        if (this.left){
            if (this.x>=-10){
                this.x-=speed;
            }
        }else if (this.right){
            if (this.x<=Constants.WIDTH-100){
                this.x+=speed;
            }
        }
    }
    public void isMoving(){

        if (this.left||this.right){
            this.isMoving = true;
        }else {
            this.isMoving = false;
        }
    }
    public void destroy(){
       // System.out.println("you lost bitch");
    }

    public void setLeft(boolean left) {
        this.left = left;
    }


    protected void setRectangle(){
        this.entityRectangle.setBounds(this.x,this.y,this.width/3,this.height/2);
    }

    public void restart() {
        this.x = 410;
        this.y=Constants.CANNON_Y_POSITION;
        this.right = false;
        this.left  =false;
        this.shotIndex = 0;


    }
}
