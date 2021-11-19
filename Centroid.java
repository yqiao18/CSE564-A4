import java.util.HashSet;

/**
@author Suraj Madhukar Suryawanshi
 * @version 1.0
 * @Date 18/11/2021
 */

public class Centroid implements Runnable {
    int indexInList;
    City centroid;
    public Centroid(City centroid, int index) {
        this.centroid = centroid;
        this.indexInList = index;
    }

    @Override
    public void run() {
        HashSet<String> centroidCities = CitiesList.getInstance().getCentroidCityNames();

        for(String cityName:CitiesList.getInstance().citiesList.keySet()) {

            if(!centroidCities.contains(cityName)) {
                City city = CitiesList.getInstance().citiesList.get(cityName);

                double dx = (city.bounds.x-centroid.bounds.x);
                double dy = (city.bounds.y-centroid.bounds.y);
                double myDistance = Math.sqrt(dx*dx+dy*dy);

                try {
                    while(CitiesList.getInstance().mutex) {
                        Thread.sleep(10);
                    }
                    Thread.sleep((long) (Math.random()*100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // lock list
                CitiesList.getInstance().mutex = true;

                double existingDistance = CitiesList.getInstance().
                        citiesList.get(cityName).centroidDistance;
                if(existingDistance>myDistance) {
                    CitiesList.getInstance().
                            citiesList.get(cityName).centroidDistance = myDistance;
                    CitiesList.getInstance().
                            citiesList.get(cityName).clusterIndex = this.indexInList;
                }

                // unlock list
                CitiesList.getInstance().mutex = false;
            }
        }
    }
}