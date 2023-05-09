import javax.swing.*;

public class Cannon extends Entity{
    private boolean left;
    private boolean right;
    public Cannon(int x,int y,int width,int height){
        super(x,y,width,height);
        this.right = false;
        this.left  =false;
        setImage(Constants.CANNON_PATH);
    }


    public void setRight(boolean right) {
        this.right = right;
    }

    public void update(){
        moveCannon();
        setRectangle();
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
    public void destroy(){
        JLabel dialogMassage = new JLabel();
        JOptionPane.showMessageDialog(dialogMassage,"you lost bitch");
    }

    public void setLeft(boolean left) {
        this.left = left;
    }


    protected void setRectangle(){
        this.entityRectangle.setBounds(this.x,this.y,this.width/3,this.height/2);
    }
}
