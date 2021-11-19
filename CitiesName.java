/**
 * @author Suraj Suryawanshi
 * @version 1.0
 * @Date 18/11/2021
 */

import java.util.ArrayList;

public class CitiesName {

    public ArrayList<String> cityNames;
    private static CitiesName citiesName;

    protected CitiesName(){
        cityNames = new ArrayList<>();
    }

    public static CitiesName getInstance(){
        if(CitiesName.citiesName == null)
            CitiesName.citiesName = new CitiesName();
        return CitiesName.citiesName;
    }
}
