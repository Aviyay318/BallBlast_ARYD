import java.awt.*;

public class Utils {
    public static boolean collision (Rectangle rectangle1, Rectangle rectangle2){
        return rectangle1.intersects(rectangle2);
    }
    public static void sleep (int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
