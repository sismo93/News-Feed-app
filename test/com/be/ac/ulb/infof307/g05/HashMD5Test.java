package com.be.ac.ulb.infof307.g05;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.be.ac.ulb.g05.HashMD5.hashFunct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @borsalinoK
 * @mousbx
 * Class that test the hashing
 */
public class HashMD5Test extends ApplicationTest {

    /**
     * Test if the length of the hashed password
     */
    @Test
    public void HashFunctTest() {
        String test = "test";

        String hashTest = hashFunct(test);

        assertEquals(hashTest.length(),32);

    }

    /**
     * Test if the hash contains letter
     */
    @Test
    public  void ContainsLetterTest(){

        String test = "test";

        String hashTest = hashFunct(test);


        assertTrue(stringContainsLetter(hashTest));
    }

    /**
     * Test if the hash contains number
     */
    @Test
    public  void ContainsNumberTest(){

        String test = "test";

        String hashTest = hashFunct(test);

        assertTrue(stringContainsNumber(hashTest));

    }

    @Test
    public void ValidHashTest() {
        String test = "test";

        String generatedHash = "098F6BCD4621D373CADE4E832627B4F6";

        assertEquals(generatedHash, hashFunct(test).toUpperCase());
    }

    /**
     * @param s
     * @return true if the hashed password contains Letter
     */
    public boolean stringContainsLetter( String s )
    {
        return Pattern.compile( "[a-z]" ).matcher( s ).find();
    }

    /**
     * @param s
     * @return true if the hashed password contains Number
     */
    public boolean stringContainsNumber( String s )
    {
        Pattern p = Pattern.compile( "[0-9]" );
        Matcher m = p.matcher( s );

        return m.find();
    }
}