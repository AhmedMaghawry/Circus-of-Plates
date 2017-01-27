package circus.of.plates.model;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Hand implements Serializable {

    private List<Shape> plates;
    private Character clown;
    private int hight = 0;
    private int plateWidth = 20;
    private int handWidth = 20;
    private int firstShape;
    private boolean flag = true;

    public Hand(Character clown) {
        plates = new ArrayList<>();
        this.clown = clown;
    }

    private boolean iscontinuous(JPanel panal) {
        Itrator itrator = getItrator();
        itrator.setLast();
        int i = 0;
        if (itrator.getIndex() >= 3) {
            itrator.prev();
            for (Itrator itra = itrator; itra.hasPrev() && i < 2; i++) {
                if (!itra.getCurrent().getColor().equals(itra.prev().getColor())) {
                    return false;
                }
            }
            removePlates(panal);
            return true;
        } else 
            return false;
        
         /*if (plates.size() >= 3) { int startPlate = plates.size() - 1; for
         (int i = startPlate-1; i > startPlate - 3; i--) { if
         (plates.get(i+1).getColor() != plates.get(i).getColor()) return
         false; } removePlates(panal); return true; } else {
         return false; }*/
    }

    private void removePlates(JPanel panel) {
        Itrator itrator = getItrator();
        itrator.setLast();
        int i = 0;
        while(i < 3) {
            itrator.setLast();
            panel.remove((Component) itrator.prev());
            hight -= itrator.getCurrent().getHight();
            plates.remove(itrator.getIndex());
            i++;
        }
        /*
        for (int i = startPlate; i > startPlate - 3; i--) {
            panel.remove((Component) plates.get(i));
            hight -= plates.get(i).getHight();
            plates.remove(i);
        }*/
    }

    public boolean addPlate(Shape plate, JPanel panal) {
        plate.stopMove();
        if (flag) {
            firstShape = plate.getHight();
            flag = false;
        }
        plates.add(plate);
        hight += plate.getHight();
        return iscontinuous(panal);
    }

    public int platesHight() {
        return hight;
    }

    public boolean inRangeLeft(Shape plate) {
        if (plate.getXcoord() >= clown.getXCoordinatesLeft() - plateWidth
                && plate.getXcoord() <= clown.getXCoordinatesLeft() + handWidth) {
            return true;
        } else
            return false;
    }

    public boolean inRangeRight(Shape plate) {
        if (plate.getXcoord() < clown.getXCoordinatesRight()
                && plate.getXcoord() >= clown.getXCoordinatesRight() - handWidth - plateWidth - 10) {
            return true;
        } else
            return false;
    }

    public Itrator getItrator() {
        return new ShapeItrator(plates);
    }
    
    public int getFirstShapeHight() {
        return firstShape;
    }
}
