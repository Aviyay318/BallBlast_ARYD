public  class Shot extends Entity{
    private boolean isShooting;
    private int speedShot;

    public Shot(int x){
        super(x,Constants.CANNON_Y_POSITION,0,0);
        setImage(Constants.SHOT_PATH);
        this.isShooting = false;
        this.speedShot=15;

    }

    public void update(boolean isM,int x){
       updateXPosition(x);
        startShoot(isM);
        setRectangle();
    }

    private void updateXPosition(int x) {
        if (this.y>=Constants.CANNON_Y_POSITION){
            this.x = x+30;
        }

    }
    public void setShotVisible(boolean visible){
        if (visible){
            this.width=Constants.SHOT_WIDTH;
            this.height=Constants.SHOT_HEIGHT;
        } else {
            this.width = 0;
            this.height = 0;
            this.y=Constants.CANNON_Y_POSITION;
        }
    }

    private void startShoot(boolean isM){
        if (isM||this.y<Constants.CANNON_Y_POSITION){
            if (this.y>=0){
                setShotVisible(true);
                this.y-=this.speedShot;
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
