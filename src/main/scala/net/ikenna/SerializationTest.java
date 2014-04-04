package net.ikenna;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationTest {



    public static void main(String[] args) throws Exception {

        File file = new File("/home/inwaiwu/test.ser");
        file.createNewFile();
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(f);
        out.writeObject(new SomeFunkyClass());
        out.close();
        System.out.println("done");

    }
}

class SomeFunkyClass implements Serializable {
    public final String s = "My great text";
}