import java.awt.*;

/**
 * @author William Tolley, Yunhan Qiao
 * @version 1.0
 * @Date 18/11/2021
 */

public class CityDecorator extends AbstractCity{

    protected AbstractCity abstractCity;

    public CityDecorator(String name, int x, int y) {
        super(name, x, y);
    }

    public void setAbstractCity(AbstractCity abstractCity){
        this.abstractCity = abstractCity;
    }

    @Override
    public void draw(Graphics g) {
        if(abstractCity != null){
            abstractCity.draw(g);
        }
    }
}
