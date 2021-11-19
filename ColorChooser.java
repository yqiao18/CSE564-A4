import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yunhan Qiao, William Tolley, Kunj Viral Kumar Mehta
 * @version 2.0
 * <p>This class creates the graphic user interface used for drawing the cities.</p>
 * @Date 18/11/2021
 */
public class ColorChooser extends JDialog{
    private JLabel sizeLabel;
    private JTextField sizeText;
    private JLabel shapeLabel;
    private JCheckBox squareButton;
    private JCheckBox circleButton;
    private JColorChooser colorChooser;
    private JButton applyButton;
    private Color colorChanged;
    private int sizeChanged;
    private int squareOption = 0;
    private int circleOption = 0;

    public ColorChooser(JFrame parent){
        super(parent, "Edited Window", true);
        setLayout(new FlowLayout());
        sizeLabel = new JLabel("size:");
        shapeLabel = new JLabel("shape:");
        squareButton = new JCheckBox("square");
        circleButton = new JCheckBox("circle");
        sizeText = new JTextField(10);
        colorChooser  =  new JColorChooser();
        applyButton = new JButton("Apply");
        add(sizeLabel);
        add(sizeText);
        add(shapeLabel);
        add(squareButton);
        add(circleButton);
        add(colorChooser);
        add(applyButton);
//        setVisible(true);
        setSize(700,500);
        applyButton.addActionListener(e -> {
            colorChanged = colorChooser.getColor();
            System.out.println(colorChanged.getRGB());
            if(sizeText.getText().equals("")){
                sizeChanged = 20;
            }else {
                sizeChanged = Integer.parseInt(sizeText.getText());
            }
            System.out.println(sizeChanged);
            setVisible(false);
        });
        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox jCheckBox = (JCheckBox) e.getSource();
                if(jCheckBox.getText().equals("square")){
                    squareOption = 1;
                }
            }
        });
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox jCheckBox = (JCheckBox) e.getSource();
                if(jCheckBox.getText().equals("circle")){
                    circleOption = 1;
                }
            }
        });
    }


    /**
     * <p>Get the color changed</p>
     * @return
     */
    public Color getColorChanged() {
        System.out.println(colorChanged.toString());
        return colorChanged;
    }

    /**
     * <p>Get the size changed</p>
     * @return
     */
    public int getSizeChanged() {
        System.out.println(sizeChanged);
        return sizeChanged;
    }

    /**
     * <p>Get the circle decorator.</p>
     * @return
     */
    public int getCircleButton(){
        return circleOption;
    }

    /**
     * <p>Get the square decorator</p>
     * @return
     */
    public int getShapeButton(){
        return squareOption;
    }
}
