package com.example.fatcarbon;


import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Class DataReader
 */
public class UserDataReader {

    private Context context;

    //
    // Fields
    //

    
    //
    // Constructors
    //
    UserDataReader(Context ctx) {
        context = ctx;
    };
    
    //
    // Methods
    //

    public Object readItem(String username) {

        FileInputStream fis = null;
        try {
            fis = context.openFileInput(username + ".dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object item = (Object) ois.readObject();
            if (fis != null) {
                fis.close();
            }
            return item;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    //
    // Accessor methods
    //

    //
    // Other methods
    //

}
