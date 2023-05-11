public class Shot extends Entity{
    private boolean isShooting;
    public Shot(int x){
        super(x,Constants.CANNON_Y_POSITION,0,0);
        setImage(Constants.SHOT_PATH);
        this.isShooting = false;
    }

    public void update(boolean isM,int x){
       updateXPosition(x);
        startShoot(isM);
        setRectangle();
    }

    private void updateXPosition(int x) {
        this.x = x;
    }
    public void setShotVisible(boolean visible){
        if (visible){
            this.width=Constants.SHOT_WIDTH;
            this.height=Constants.SHOT_HEIGHT;
        } else {
            this.width = 0;
            this.height = 0;
            this.y=740;
        }
    }

    private void startShoot(boolean isM){
        if (isM){
            if (this.y>=0){
                setShotVisible(true);
                this.y-=5;
            }else {
                this.y=Constants.CANNON_Y_POSITION;
                this.setShotVisible(false);
            }

        }
    }
    public void setShot(){
        this.y = Constants.CANNON_Y_POSITION;
    }
    protected void setRectangle(){
        this.entityRectangle.setBounds(this.x,this.y,this.width+10,this.height);
    }
    @Override
    public String toString() {
        return "Shot{" +
                "isShooting=" + isShooting +"position: "+this.x+" , "+this.y+
                '}';
    }
}
