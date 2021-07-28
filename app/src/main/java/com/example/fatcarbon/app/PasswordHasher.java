package com.example.fatcarbon.app;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHasher implements Serializable {

    private final String hashedPassword;
    private final byte[] salt;

    public PasswordHasher(String password) {
            salt = getSalt();
            hashedPassword = getHashedPassword(password, salt);
            System.out.println(hashedPassword);
    }

    private static String getHashedPassword(String password, byte[] salt) {

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
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public boolean validatePassword(String password){
        return hashedPassword.equals(getHashedPassword(password, salt));
    }

}
