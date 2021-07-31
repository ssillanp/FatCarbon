package com.example.fatcarbon.app;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import android.content.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Class DataReader for reading an User object from storage
 */
public class UserDataReader {

    private Context context;

    public UserDataReader(Context ctx) {
        context = ctx;
    };

    public Object readItem(String username) {

        FileInputStream fis = null;
        try {
            fis = context.openFileInput(username + ".dat");
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


}
