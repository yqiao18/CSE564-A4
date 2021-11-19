import java.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.util.logging.Logger;

import javax.swing.*;

/**
 * @author Kunj Viral Kumar Mehta
 * @version 2.0
 * @Date 18/11/2021
 */
public class Workspace extends JPanel implements Observer, MouseListener, MouseMotionListener {

    private String pressedKey;
    private Boolean pressOut;
    private AbstractFactory cityFactory;
    private Handler tsp;
    private Handler tspPro;
    private Handler kCluster;
    private Handler userConnect;
    
    private int type;
    private boolean canCreate = true;
    private boolean canConnect = true;
    private boolean canMove = true;
    public static boolean canUserConnect = false;
    public City userConnectCity1;
    public City userConnectCity2;
    

    /**
     * <p>This function is constructor.</p>
     */
    public Workspace() {
    	userConnect = new UserConnect();
        cityFactory = new CityFactory();
        tsp= new TSP();
        tspPro = new TSPpro();
        kCluster = new KClusters();
        tsp.setNextHandler(tspPro);
        tspPro.setNextHandler(kCluster);
        kCluster.setNextHandler(userConnect);
        tsp.addObserver(this);
        tspPro.addObserver(this);
        kCluster.addObserver(this);
        userConnect.addObserver(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * <p>This function is to paint components.</p>
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        int n = PathsList.getInstance().getLength();
        System.out.println("size of path: "+ n);
        System.out.println("size of City: "+CitiesList.getInstance().citiesList.size());
        if (PathsList.getInstance() != null && n >= 1) {
            System.out.println(n);
            for (int i = 0; i < n; i++) {
                City city1 = PathsList.getInstance().path.get(i)[0];
                City city2 = PathsList.getInstance().path.get(i)[1];
                city1.drawConnect(city2, graphics2D);
            }
        }
        for (Map.Entry<String, City> entry : CitiesList.getInstance().citiesList.entrySet()) {
            City city = entry.getValue();
            if(city.square ==0 && city.circle == 1){
                CircleDecorator circleDecorator = new CircleDecorator(city.label, city.x, city.y);
                circleDecorator.setAbstractCity(city);
                circleDecorator.draw(g);
            }else if(city.circle == 0&&city.square == 1){
                SquareDecorator squareDecorator = new SquareDecorator(city.label, city.x, city.y);
                squareDecorator.setAbstractCity(city);
                squareDecorator.draw(g);
            }else if(city.square == 1&& city.circle == 1){
                CircleDecorator circleDecorator = new CircleDecorator(city.label, city.x, city.y);
                SquareDecorator squareDecorator = new SquareDecorator(city.label, city.x, city.y);
                circleDecorator.setAbstractCity(city);
                squareDecorator.setAbstractCity(circleDecorator);
                squareDecorator.draw(g);
            }else {
                city.draw(g);
            }
        }
    }

    /**
     * <p>This function is to receive message from observable and response.</p>
     * @param obs
     * @param obj
     */
    public void update(Observable obs, Object obj) {
        System.out.println("The workspace will repaint");
        repaint();
    }

    /**
     * <p>This function is to create new city.</p>
     * @param x
     * @param y
     */
    private void setNewCity(int x, int y) {

        String name = JOptionPane.showInputDialog("ENTER CITY NAME");
        if(name == null){
            JOptionPane.showMessageDialog(null, "NAME CANNOT BE NULL");
            return;
        }
        City city = cityFactory.getCity(name, x, y);
        CitiesList.getInstance().addNewCity(city);
        repaint();
    }

    /**
     * <p>This function will be triggered when we click on the panel.</p>
     * @param e
     */
    public void mouseClicked(MouseEvent e) {
    	
        if(canCreate==true) {
            int x = e.getX();
            int y = e.getY();
            LinkedHashMap<String, City> map = CitiesList.getInstance().citiesList;
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, City> entry = (Map.Entry) iter.next();
                City city = entry.getValue();
                CitiesName.getInstance().cityNames.add(entry.getKey());
                boolean isContained = city.contain(x, y);
                
                
                
                if (isContained) {
                	
                	if(canUserConnect) {
                		System.out.println("hereee!!!");
                		if(this.userConnectCity1 == null) {
                			this.userConnectCity1 = city;
                			
                		}
                		else {
                			this.userConnectCity2 = city;
                			new UserConnect(this.userConnectCity1, this.userConnectCity2);
                			this.userConnectCity1 = null;
                			this.userConnectCity2 = null;
                			repaint();
                			
                		}
                		
                	}
                	else {
	                    JFrame parent = (JFrame)SwingUtilities.getWindowAncestor(this);
	                    ColorChooser colorChooser = new ColorChooser(parent);
	                    colorChooser.setVisible(true);
	                    Color color = colorChooser.getColorChanged();
	                    int size = colorChooser.getSizeChanged();
	                    int circleOption = colorChooser.getCircleButton();
	                    int squareOption = colorChooser.getShapeButton();
	                    city.height = size;
	                    city.width = size;
	                    System.out.println(city.label+"size: "+city.height);
	                    city.color = color;
	                    if(circleOption==0&&squareOption==1){
	                        city.square = 1;
	                        city.circle = 0;
	                    }else if(circleOption == 1 && squareOption ==0){
	                        city.circle = 1;
	                        city.square = 0;
	                    }else if(circleOption == 1 && squareOption ==1){
	                        city.circle = 1;
	                        city.square = 1;
	                    }else{
	                        city.circle = 0;
	                        city.square = 0;
	                    }
	                    repaint();
	                    return;
                	}
                }//isContained

            }
            if(!canUserConnect) 
            	setNewCity(x, y);
        }else {
            return;
        }
    }

    /**
     * <p>This function will be triggered when we press the panel.</p>
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(canMove==true) {
            int x = e.getX();
            int y = e.getY();
            LinkedHashMap<String, City> map = CitiesList.getInstance().citiesList;
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, City> entry = (Map.Entry) iter.next();
                City city = entry.getValue();
                boolean isContained = city.contain(x, y);
                if (isContained) {
                    pressOut = false;
                    System.out.println(city.label);
                    pressedKey = city.label;
                }
            }
        }else {
            return;
        }
    }

    /**
     * <p>This function will be triggered when we drag the city.</p>
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(canMove==true) {
            if (pressOut != null && !pressOut) {
                System.out.println("Move");
                City city = CitiesList.getInstance().citiesList.get(pressedKey);
                System.out.println(city.label);
                city.move(e.getX(), e.getY());
                city.x = e.getX();
                city.y = e.getY();
                if(CitiesList.getInstance().citiesList.size()>1&&PathsList.getInstance().getLength()>=1) {
                    this.handleRequest(this.type);
                }
                repaint();
            }
        }else {
            return;
        }
    }

    /**
     * <p>This function will be triggered when we release the mouse.</p>
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(canMove==true) {
            pressOut = true;
        }else {
            return;
        }
    }

    /**
     * <p>Empty function calls for interface implementation.</p>
     */
    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    /**
     * <p>Handle the user's request.</p>
     * @param type
     */
    public void handleRequest(int type){
        if(canConnect==true) {
            PathsList.getInstance().clearPath();
            this.type = type;
            tsp.handleRequest(type);
        }else {
            return;
        }
    }

    /**
     * <P>Handle user connect.</P>
     */
    public void handleUserConnect(){
        PathsList.getInstance().clearPath();
        repaint();
    }

    /**
     * <p>Clear all cities.</p>
     */
    public void newPanel(){
        PathsList.getInstance().clearPath();
        CitiesList.getInstance().clearCities();
        repaint();
    }

    /**
     * <p>Make user can move.</p>
     */
    public void moveAbled(){
        this.canCreate=false;
        this.canConnect = false;
        this.canMove = true;
    }

    /**
     * <p>Make user can connect.</p>
     */
    public void connectAbled(){
        this.canCreate = false;
        this.canMove = false;
        this.canConnect = true;
    }

    /**
     * <p>Make user can create.</p>
     */
    public void createAbled(){
        this.canCreate = true;
        this.canMove = false;
        this.canConnect = false;
    }

    public void setCanCreate(boolean canCreate) {
        this.canCreate = canCreate;
    }

    public void setCanConnect(boolean canConnect) {
        this.canConnect = canConnect;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
