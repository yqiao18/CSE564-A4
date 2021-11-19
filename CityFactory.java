/**
 * @author Yunhan Qiao
 * @version 1.0
 * @Date 18/11/2021
 */

public class CityFactory extends AbstractFactory{

    @Override
    public City getCity(String name, int x, int y) {
        return new City(name, x, y);
    }
}
