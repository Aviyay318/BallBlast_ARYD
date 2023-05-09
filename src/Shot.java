import java.awt.*;

public class Shot extends Entity{

    private Cannon cannon;
    private boolean isShooting;

    public Shot(Cannon cannon){
        super(cannon.x+40,cannon.y+10,Constants.SHOT_WIDTH,Constants.SHOT_HEIGHT);
        this.cannon = cannon;
        setImage(Constants.SHOT_PATH);
        this.isShooting = false;
    }
    public void update(){
        updateXPosition();
        startShoot();
        setRectangle();
    }

    private void updateXPosition() {
        this.x = this.cannon.x+40;
    }

    public void setShooting(boolean shooting) {
       this.isShooting = shooting;
    }

    private void startShoot(){

        if (this.isShooting){
            this.y-=speed;
        }

    }


    @Override
    public String toString() {
        return "Shot{" +
                "isShooting=" + isShooting +
                '}';
    }
}
