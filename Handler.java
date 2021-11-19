/**
 * @author Suraj Madhukar Suryawanshi
 * @version 1.0
 * @Date 18/11/2021
 */

import java.util.Observable;

public abstract class Handler extends Observable {
	
	//next element in chain or responsibility
	protected Handler nextHandler;
	
	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}
	
	public abstract void handleRequest(int type);
}
