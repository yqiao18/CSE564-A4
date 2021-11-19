import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;
import java.util.Map.Entry;

/**
 * <p>This class is for maintaining the cities list made of object of City class </p>
 *
 * @author  Suraj Suryawanshi
 * @version 2.0
 * @since   2021-10-08
 */

public class CitiesList {

    public ArrayList<City> clusterCentroids;
    public LinkedHashMap<String, City> citiesList;
    public double pathDist;
    public boolean mutex;
    private static CitiesList object;


    private CitiesList() {
        citiesList =  new LinkedHashMap<String, City>();
        clusterCentroids = new ArrayList<City>();
        pathDist = 0;
        mutex = false;
    }

    /**
     * This method is for getting the instance of this class object
     *
     * @return CitiesList
     */
    public static CitiesList getInstance() {
        if(CitiesList.object == null)
            CitiesList.object = new CitiesList();
        return CitiesList.object;
    }

    /**
     * This method is for saving the file to the CWD
     *
     */
    public void saveFile() {
        String path = System.getProperty("user.dir");
        StringBuilder sb = new StringBuilder();
        for(String s: CitiesList.object.citiesList.keySet()) {
            sb.append(s+" "+CitiesList.object.citiesList.get(s).bounds.x
                    +" "+CitiesList.object.citiesList.get(s).bounds.y+"\n");
        }
        try {
            File file = new File("testCities_"+Math.round(Math.random()*1000)+".txt");
            FileWriter writer = new FileWriter(file);
            writer.append(sb.toString());
            writer.close();
            System.out.println("Save as file: " + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error while writing the file");
            e.printStackTrace();
        }
    }

    /**
     * This method is for adding a new city to the list
     *
     * @param city  Object of the city class
     */
    public void addNewCity(City city) {
        if(!CitiesList.getInstance().citiesList.containsKey(city.label)) {
            CitiesList.getInstance().citiesList.put(city.label, city);
//			Flag.getInstance().val = true;
        }
        else {
            System.out.println("City name already exists in the list");
        }
    }

    /**
     * This method is for updating the city movement
     *
     * @param city  Object of the city class
     */
    public void updateCity(City city) {
        CitiesList.getInstance().citiesList.put(city.label, city);
//		Flag.getInstance().val = true;
    }

    /**
     * This method is for importing an existing file
     *
     * @param file  file to import
     */
    public void readInputCoords(File file) {
        String label;
        CitiesList.getInstance().citiesList = new LinkedHashMap<String, City>();
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String arr[] = sc.nextLine().trim().split(" ");

                if(arr.length<3) {
                    System.out.println("Incorrect input format");
                    break;
                }
                label = arr[0];
                CitiesList.getInstance().citiesList.put(label,
                        new City(label, Integer.parseInt(arr[1]), Integer.parseInt(arr[2])));
            }
//			Flag.getInstance().val = true;
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error while reading the file");
            e.printStackTrace();
        }
    }

    /**
     * This method is clearing the cities list
     */
    public void clearCities() {
        CitiesList.getInstance().citiesList = new LinkedHashMap<String, City>();
//		Flag.getInstance().val = true;
        System.out.println("clearing cities list "+CitiesList.getInstance().citiesList.size());
    }


    /**
     * This method is for adding the cluster paths
     *
     */
    public void addClusterPaths() {
        // clear
        PathsList.getInstance().clearPath();

        for(String s: CitiesList.object.citiesList.keySet()) {
            City currentCity = CitiesList.object.citiesList.get(s), nextCity;
            if(currentCity.clusterIndex == -1)
                nextCity = currentCity;
            else
                nextCity = CitiesList.object.clusterCentroids.get(currentCity.clusterIndex);
//			System.out.println(currentCity.toString()+": "+nextCity.toString());
            PathsList.getInstance().path.add(new City[] {currentCity, nextCity});
        }
    }

    /**
     * This method is for adding the TSP path
     *
     */
    public void addTSPPath() {
        City startCity;

        Iterator<Entry<String, City>> iter = this.citiesList .entrySet().iterator();
        if(iter.hasNext()) {
            City currentCity = iter.next().getValue(), nextCity;
            startCity = currentCity;

            while(iter.hasNext()) {
                nextCity = iter.next().getValue();
                PathsList.getInstance().path.add(new City[] {
                        currentCity,nextCity});
//				System.out.println(currentCity.toString()+": "+nextCity.toString());
                currentCity = nextCity;
            }

            if(this.citiesList.size()>2) {
                PathsList.getInstance().path.add(new City[] {
                        currentCity,startCity});
            }
        }
    }

    /**
     * Returns set of the centroid city names
     *
     * @return hashset of city names
     *
     */
    public HashSet<String> getCentroidCityNames() {
        HashSet<String> centroidCities = new HashSet<String>();

        CitiesList.getInstance().clusterCentroids.forEach(city->
                centroidCities.add(city.label)
        );
        return centroidCities;
    }

    /**
     * resets centroid city names
     *
     */
    public void resetClusters() {
        CitiesList.object.clusterCentroids = new ArrayList<City>();
        for(String s: CitiesList.object.citiesList.keySet()) {
            CitiesList.object.citiesList.get(s).clusterIndex = -1;
            CitiesList.object.citiesList.get(s).centroidDistance = Double.MAX_VALUE;
        }
    }

}
