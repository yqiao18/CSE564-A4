import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Yunhan Qiao, Kunj Viral Kumar Mehta, Suraj Suryawanshi, William Tolley
 * @version 1.0
 * <p>This class creates the graphic user interface, which is for drawing the cities.</p>
 * @Date 08/10/2021
 */
public class Main extends JFrame {

    private JMenuBar jMenuBar;
    private Workspace workSpace;
    private JMenuItem New, save, load;
    private JMenu fileMenu;
    private JMenu connectionsMenu;
    private JMenu actionMenu;
    private JMenuItem TSP, TspPro, Clustering, UserConnect;
    private JMenuItem move, create, connect;
    private AbstractFactory cityFactory;
    private JLabel status;

    /**
     * <p>This is the constructor</p>
     */
    public Main() {
        jMenuBar = new JMenuBar();
        workSpace = new Workspace();
        cityFactory = new CityFactory();
        getContentPane().add(workSpace);
        
        status = new JLabel("status: idle");
        workSpace.add(status);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fileMenu = new JMenu("File");
        connectionsMenu = new JMenu("Connections");
        actionMenu = new JMenu("Action");
        New = new JMenuItem("new");
        save = new JMenuItem("save");
        load = new JMenuItem("load");
        TSP = new JMenuItem("TSP");
        TspPro = new JMenuItem("TSP-Pro");
        Clustering = new JMenuItem("Cluster");
        UserConnect = new JMenuItem("User Connect");
        move = new JMenuItem("Move");
        create = new JMenuItem("create");
        connect = new JMenuItem("connect");
        jMenuBar.add(fileMenu);
        jMenuBar.add(connectionsMenu);
        jMenuBar.add(actionMenu);
        fileMenu.add(save);
        fileMenu.add(load);
        fileMenu.add(New);
        connectionsMenu.add(TSP);
        connectionsMenu.add(TspPro);
        connectionsMenu.add(Clustering);
        connectionsMenu.add(UserConnect);
        actionMenu.add(move);
        actionMenu.add(create);
        actionMenu.add(connect);
        this.setJMenuBar(jMenuBar);
        this.setVisible(true);
    }

    /**
     * <p>This function is the entrance to run the function</p>
     * @param args
     */
    public static void main(String args[]){
        new Main().init();
    }


    /**
     * This is the function to add the menulistener to delete, save and load.
     */
    public void init(){
        TSP.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
            	status.setText("TSP running..");
                workSpace.setCanConnect(true);
                workSpace.setCanMove(true);
                workSpace.setCanCreate(true);
                workSpace.handleRequest(1);
                status.setText("TSP completed");
            }
        });

        TspPro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	status.setText("TSP Pro running..");
                workSpace.setCanConnect(true);
                workSpace.setCanMove(true);
                workSpace.setCanCreate(true);
                workSpace.handleRequest(2);
                status.setText("TSP Pro completed");
            }
        });

        Clustering.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	status.setText("Clustering running..");
                workSpace.setCanConnect(true);
                workSpace.setCanMove(true);
                workSpace.setCanCreate(true);
                workSpace.handleRequest(3);
                status.setText("Clustering completed");
            }
        });

        UserConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Workspace.canUserConnect = true;
            	status.setText("User Connect running..");
                workSpace.handleUserConnect();
           
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	status.setText("saving file..");
                CitiesList.getInstance().saveFile();
                status.setText("file saved");
            }
        });

        New.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workSpace.newPanel();
                status.setText("screen cleared");
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		status.setText("loading file..");
                    System.out.println("Loading file...");
                    JFileChooser chooser = new JFileChooser();
                    chooser.showOpenDialog(null);
                    File inputFile = chooser.getSelectedFile();
                    System.out.println("File: "+inputFile.getAbsolutePath());
                    CitiesList.getInstance().readInputCoords(inputFile);
                    workSpace.handleRequest(1);
                    status.setText("load function completed");
                }
        });

        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Workspace.canUserConnect = false;
            	status.setText("current mode: move");
                workSpace.moveAbled();
            }
        });

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	status.setText("current mode: connect");
                workSpace.connectAbled();
            }
        });

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Workspace.canUserConnect = false;
            	status.setText("current mode: create");
                workSpace.createAbled();
            }
        });


        }
}
