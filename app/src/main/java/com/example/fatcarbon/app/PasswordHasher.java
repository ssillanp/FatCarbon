package com.example.fatcarbon.app;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Class implements the password hashing and validation of the hashed passwords
 */

public class PasswordHasher implements Serializable {

    private final String hashedPassword;
    private final byte[] salt;

    public PasswordHasher(String password) {
            //get the salt
            salt = getSalt();
            // hash and store the password
            hashedPassword = getHashedPassword(password, salt);
    }

    private static String getHashedPassword(String password, byte[] salt) {
        /**
         * Method creates a password Hash using SHA-256 and salt.
         * @parameter String password to hash
         * @return String hashed password
         */
        String generatedPassword = null;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (md != null) {
            md.update(salt);
        }
        byte[] bytes = new byte[0];
        if (md != null) {
            bytes = md.digest(password.getBytes());
        }
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
            generatedPassword = sb.toString();

        return generatedPassword;
    }

    private static byte[] getSalt() {
        /**
         * Method calcs a random salt for hashing
         * @return byte[] salt
         */
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public boolean validatePassword(String password){
        /**
         * Method compares the given password against the the hashed password
         * @return boolean true if the password matches, false if not
         */
        return hashedPassword.equals(getHashedPassword(password, salt));
    }

}
