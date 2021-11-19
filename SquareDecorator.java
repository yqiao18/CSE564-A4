import java.awt.*;

/**
 * @author Yunhan Qiao-1222800436
 * @version 1.0
 * <p>This class is the decorator of component</p>
 * @Date 18/11/2021
 */
public class SquareDecorator extends CityDecorator{

    public SquareDecorator(String name, int x, int y) {
        super(name, x, y);
    }

    /**
     * <p>Draw the city</p>
     * @param g
     */
    public void draw(Graphics g){
        super.draw(g);
        int height = super.height;
        int width = super.width;
        int xCo = super.x;
        int yCo = super.y;
        g.setColor(super.color);
        g.drawRect(xCo-height-5, yCo, height, width);
        g.fillRect(xCo-height-5, yCo, height, width);
        g.drawRect(xCo+height+5, yCo, height, width);
        g.fillRect(xCo+height+5, yCo, height, width);
        g.drawRect(xCo, yCo-height-5, height, width);
        g.fillRect(xCo, yCo-height-5, height, width);
        g.drawRect(xCo, yCo+height+5, height, width);
        g.fillRect(xCo, yCo+height+5, height, width);
    }
}
