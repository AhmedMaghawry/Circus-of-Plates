package circus.of.plates.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Shots {
    private List<SnapShot> mementoList = new ArrayList<SnapShot>();

    public void add(SnapShot state) throws Exception{
     // Write to disk with FileOutputStream
        FileOutputStream f_out = new 
            FileOutputStream("Save.data");

        // Write object with ObjectOutputStream
        ObjectOutputStream obj_out = new
            ObjectOutputStream (f_out);

        // Write object out to disk
        obj_out.writeObject ( state );
       mementoList.add(state);
    }

    public SnapShot get() throws Exception{
     // Read from disk using FileInputStream
        FileInputStream f_in = new 
            FileInputStream("Save.data");

        // Read object using ObjectInputStream
        ObjectInputStream obj_in = 
            new ObjectInputStream (f_in);

        // Read an object
        Object obj = obj_in.readObject();
        
            SnapShot vec = (SnapShot) obj;
       return vec;
    }
}
