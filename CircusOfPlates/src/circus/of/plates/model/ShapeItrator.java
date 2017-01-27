package circus.of.plates.model;

import java.util.List;

public class ShapeItrator implements Itrator {

    private int index = 0;
    private List<Shape> shapes;
    //private static Itrator instance = null;
    
    public ShapeItrator(List<Shape> shapes) {
        this.shapes = shapes;
    }
    @Override
    public boolean hasNext() {
        if (index <= shapes.size())
            return true;
        else
            return false;
    }

    @Override
    public Shape next() {
        index++;
        return shapes.get(index);
    }

    @Override
    public boolean hasPrev() {
        if (index >= 0)
            return true;
        else
            return false;
    }

    @Override
    public Shape prev() {
        index--;
        return shapes.get(index);
    }
    
    @Override
    public Shape getCurrent() {
        return shapes.get(index);
    }
    
    /*public static Itrator getInstance(List<Shape> shapes) {
        if (instance != null)
            return instance;
        else {
            instance = new ShapeItrator(shapes);
            return instance;
        }
            
    }*/
    @Override
    public Shape setFirst() {
        index = -1;
        return null;
    }
    @Override
    public Shape setLast() {
        index = shapes.size();
        return null;
    }
    @Override
    public int getIndex() {
        return index;
    }
}
