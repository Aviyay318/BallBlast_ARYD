import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Entity {
    protected int x;
    protected  int y;
    protected  int width;
    protected int height;
    protected static int speed;
    protected BufferedImage image;
    protected Rectangle entityRectangle;
    protected Sound sounds;
    public Entity(){ this.entityRectangle = new Rectangle();}
    public Entity(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        speed = 2;
        this.entityRectangle = new Rectangle(this.x,this.y,this.width,this.height);
        this.sounds = new Sound();
    }


    protected void setImage(String path){
        try {
           this.image  = ImageIO.read(new File(path));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void destroy(){
        this.height=0;
        this.width=0;
    }
   protected abstract void setRectangle();
    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(this.image,this.x,this.y,this.width,this.height,null);

    }
}
