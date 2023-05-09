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
        if (!this.isShooting){
            updateXPosition();
        }
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
            this.y-=10;
        }
    }
    public void setShot(){
        this.y = this.cannon.y;
        this.width = Constants.SHOT_WIDTH;
        this.height =Constants.SHOT_HEIGHT;
    }
    @Override
    public String toString() {
        return "Shot{" +
                "isShooting=" + isShooting +
                '}';
    }
}
