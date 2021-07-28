package com.example.fatcarbon.app;

/**
 * Class PasswordValidator
 */
public class PasswordValidator {

    //
    // Fields
    //
    private final int min_length = 12;
    private final String specialChars = " \"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    //
    // Constructors
    //
    public PasswordValidator() {

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

    /**
     * @param password
     */
    public boolean validatePassword(String password) {
        int specialChr = 0;
        int numbers = 0;
        int upper = 0;
        int lower = 0;
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
                if (specialChr + numbers + upper + lower == 4) {
                    return true;
                }
            }

        }
        return false;
    }


}
