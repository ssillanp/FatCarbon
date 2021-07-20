package com.example.fatcarbon;

import android.content.Context;

import java.io.*;


/**
 * Class DataWriter
 */
public class UserDataWriter {

    private Context context;

    //
    // Fields
    //


    //
    // Constructors
    //
    UserDataWriter(Context ctx) {
        context = ctx;
    }

    ;

    //
    // Methods
    //


    //
    // Accessor methods
    //

    //
    // Other methods
    //


    public void writeItem(User item) {

        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(item.getUsername() + ".dat", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(item);
            oos.flush();
            if (fos != null) {
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
