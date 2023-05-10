import java.awt.*;

public class Utils {
    public static boolean collision (Rectangle rectangle1, Rectangle rectangle2){
        return rectangle1.intersects(rectangle2);
    }

}
