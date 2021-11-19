import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
/**
 * This class is for maintaining the cities path list made of object of City class
 *
 * @author  Suraj Suryawanshi
 * @version 2.0
 * @since   2021-10-08
 */

public class PathsList {

    public ArrayList<City[]> path;
    public double pathDist;
    private static PathsList object;


    protected PathsList() {
        path = new ArrayList<City[]>();
        pathDist = 0;
    }

    /**
     * This method is for getting the instance of this class object
     *
     * @return PathsList
     */
    public static PathsList getInstance() {
        if (PathsList.object == null)
            PathsList.object = new PathsList();
        return PathsList.object;
    }


    /**
     * This method is clearing the cities path list
     */
    public void clearPath() {
        PathsList.getInstance().path = new ArrayList<City[]>();
//		Flag.getInstance().val = true;
        System.out.println("clearing path list " + PathsList.getInstance().path.size());
    }

    public int getLength(){
        return path.size();
    }
}
