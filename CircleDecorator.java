import java.awt.*;

/**
 * @author Yunhan Qiao-1222800436
 * @version 1.0
 * <p>This class is the decorator of component</p>
 * @Date 18/11/2021
 */

public class CircleDecorator extends CityDecorator{


    public CircleDecorator(String name, int x, int y) {
        super(name, x, y);
    }

    /**
     * <p>Draw the decorator.</p>
     * @param g
     */
    public void draw(Graphics g){
        super.draw(g);
        g.setColor(Color.WHITE);
        g.drawOval(super.x,super.y, super.height, super.width);
        g.fillOval(super.x, super.y, super.height, super.height);
    }
}
