package com.example.fatcarbon.app;

/**************************************
 LUT Olio-ohjelmointi Harjoitustyö
 @author Sami Sillanpää
 @copyright Sami Sillanpää 2021
 @licence GNU GPL3.0
 **************************************/


/**
 * Class PasswordValidator, validates that a password meets set criteria
 * Criteria: min 12 char, min 1 uppercase, 1 lowercase, 1 number and 1 special char.
 */
public class PasswordValidator {


    private final int min_length = 12;
    // list of special characters
    private final String specialChars = " \"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~!";

    public PasswordValidator() {

    }


    public boolean validatePassword(String password) {
        /**
         * Method checks the given password against set criteria
         * returns true if criteria is fulfilled, false in not
         */
        // initialize a counter for each validation term
        int specialChr = 0;
        int numbers = 0;
        int upper = 0;
        int lower = 0;

        // check that lenght meets the criteria
        if (password.length() >= min_length) {
            for (Character c : password.toCharArray()) {
                if (specialChr == 0 & specialChars.contains(c.toString())) {
                    specialChr += 1;
                } else if (numbers == 0 & Character.isDigit(c)) {
                    numbers += 1;
                } else if (upper == 0 & Character.isUpperCase(c)) {
                    upper += 1;
                } else if (lower == 0 & Character.isLowerCase(c)) {
                    lower += 1;
                }
                // check that all required are found
                if (specialChr + numbers + upper + lower == 4) {
                    return true;
                }
            }

        }
        return false;
    }


}
