import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Yunhan Qiao-1222800436
 * @version 1.0
 * <p>This class is the component of component</p>
 * @Date 18/11/2021
 */
public abstract class AbstractCity {
    public Rectangle bounds;
    public String label;
    public int width = 20;
    public int height = 20;
    public int x;
    public int y;
    public int square = 0;
    public int circle = 0;

    @Override
    public String toString() {
        return "City [label=" + label
                + ", clusterIndex=" + clusterIndex
                + "]";
    }

    // for K-Cluster
    public int clusterIndex = -1;
    public double centroidDistance = Double.MAX_VALUE;

    public Color color = Color.BLACK;


    public AbstractCity(String name, int x, int y){
        bounds = new Rectangle(x, y, width, height);
        this.x = x;
        this.y = y;
        this.label = name;
    }


    /**
     * This method is for changing the coordinates of the object
     *
     * @param x  X-coordinate
     * @param y  Y-coordinate
     */
    public void move(int x, int y){
        bounds.x = x;
        bounds.y = y;
    }

    private Point center(){
        return new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
    }

    /**
     * This method is for drawing a line between to city objects
     *
     * @param b  City_2 object
     * @param g  Graphics object
     */
    public void drawConnect(AbstractCity b, Graphics2D g){
        g.drawLine(center().x, center().y, b.center().x, b.center().y);
    }

    /**
     * This method is for checking the coordinates are within the city bounds
     *
     * @param x  X-coordinate
     * @param y  Y-coordinate
     */
    public boolean contain(int x, int y){
        return bounds.contains(x, y);
    }

    public abstract void draw(Graphics g);

}
