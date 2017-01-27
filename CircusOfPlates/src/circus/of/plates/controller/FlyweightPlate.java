package circus.of.plates.controller;

import java.util.ArrayList;
import circus.of.plates.model.*;
import circus.of.plates.model.Character;
import circus.of.plates.view.*;

public class FlyweightPlate {
    private ArrayList<String> colours = new ArrayList<>();
    private ArrayList<Plate> leftplates = new ArrayList<>();
    private ArrayList<Plate> rightplates = new ArrayList<>();
    private boolean d;
    private  PlateFactory plateMaker = PlateFactory.getInstance();
    public Plate CreateleftPlate(final String color,
            final int x, final int y, final int shielfWidth, final Character clown,
            final Game panel, final int speed, final int movement) {

        if (!colours.contains(color)){
          colours.add(color);
          leftplates.add(colours.indexOf(color),
                  plateMaker.makePlate(color, x, y, shielfWidth, clown, panel,speed,movement,"Left"));
          rightplates.add(colours.indexOf(color),
                  plateMaker.makePlate(color, x, y, shielfWidth, clown, panel,speed,movement,"Right"));
          System.out.println(color);
      }
          return leftplates.get(colours.indexOf(color));
      }

    public Plate CreaterightPlate(final String color,
            final int x, final int y, final int shielfWidth, final Character clown,
            final Game panel, final int speed, final int movement) {

        if (!colours.contains(color)){
              colours.add(color);
              rightplates.add(colours.indexOf(color),
                      plateMaker.makePlate(color, x, y, shielfWidth, clown, panel,speed,movement,"Right"));
              leftplates.add(colours.indexOf(color),
                      plateMaker.makePlate(color, x, y, shielfWidth, clown, panel,speed,movement,"Left"));
             // System.out.println(color);
        }
              return rightplates.get(colours.indexOf(color));
          }
}