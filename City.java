import java.awt.*;

/**
 * @author Suraj Madhukar Suryawanshi
 * @version 1.0
 * <p>This class is for providing information of city.</p>
 * @Date 08/10/2021
 */
public class City extends AbstractCity{

    public City(String name, int x, int y){
        super(name, x, y);
    }

    @Override
    public void draw(Graphics g) {
        int x = this.bounds.x, y = this.bounds.y, w = this.width, h = this.height;
        g.setColor(super.color);
        g.drawRect(x, y, w, h);
        g.setColor(super.color);
        g.fillRect(x+1, y+1, w-1, h-1);
        g.setColor(super.color);
        g.setFont(new Font("Courier", Font.PLAIN, 10));
        g.drawString(this.label, x+w, y);
    }
}
