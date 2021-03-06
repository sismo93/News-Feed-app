package com.be.ac.ulb.g05;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.be.ac.ulb.g05.Controller.Router.showAlert;


/**
 * @author @mouscb
 * Class that hash a password
 */
public class HashMD5
{
    /**
     * @param password password
     * @return the hashed password
     */
    public static String hashFunct(String password)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            showAlert("An error has occurred","Error");
        }
        return generatedPassword;
    }
}
