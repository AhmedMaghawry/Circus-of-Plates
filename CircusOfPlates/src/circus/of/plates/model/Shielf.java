package circus.of.plates.model;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Shielf extends JLabel {

    private ImageIcon shielfShape = new ImageIcon(getClass().getResource("/imageResources/FinalShielf.png"));
    private int hight;
    private int width;
    
    public Shielf() {
        setIcon(shielfShape);
        hight = shielfShape.getIconHeight();
        width = shielfShape.getIconWidth();
    }
    
    public int getHight() {
        return hight;
    }
    
    public int getWidth() {
        return width;
    }
}
