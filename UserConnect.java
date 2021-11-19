import java.util.ArrayList;

/**
 * @author Kunj Viral Kumar Mehta
 * @version 1.0
 * @Date 18/11/2021
 */

public class UserConnect extends Handler{
	private City userConnectCity1;
    private City userConnectCity2;
    
    public UserConnect(City city1, City city2) {
    	userConnectCity1 = city1;
    	userConnectCity2 = city2;
    	handleRequest(0);
    }
    
    
    

	public UserConnect() {
	}




	@Override
	public void handleRequest(int type) {
		City []arr = new  City [2];
		arr[0] = userConnectCity1;
		arr[1] = userConnectCity2;
		
		ArrayList<City[]> temp = PathsList.getInstance().path;
		temp.add(arr);
		PathsList.getInstance().path = temp;
		setChanged();
		notifyObservers();
		System.out.println("updating "+PathsList.getInstance().path.size());
	}

	
	
}
