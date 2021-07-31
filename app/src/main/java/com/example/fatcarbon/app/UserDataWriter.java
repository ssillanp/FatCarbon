package com.example.fatcarbon.app;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import android.content.Context;

import java.io.*;


/**
 * Class DataWriter for writing User class object into storage
 */
public class UserDataWriter {

    private Context context;

    public UserDataWriter(Context ctx) {
        context = ctx;
    }

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
