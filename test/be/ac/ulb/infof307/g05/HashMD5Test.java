package be.ac.ulb.infof307.g05;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static be.ac.ulb.infof307.g05.HashMD5.hashFunct;

/**
 * @borsalinoK
 * @mousbx
 * Class that test the hashing
 */
public class HashMD5Test extends TestCase {

    /**
     * Test if the length of the hashed password
     */
    @Test
    public void testHashFunct() {
        String test = "test";

        String hashTest = hashFunct(test);

        assertEquals(hashTest.length(),32);

    }

    /**
     * Test if the hash contains letter
     */
    @Test
    public  void testContainsLetter(){

        String test = "test";

        String hashTest = hashFunct(test);


        assertTrue(stringContainsLetter(hashTest));
    }

    /**
     * Test if the hash contains number
     */
    @Test
    public  void testContainsNumber(){

        String test = "test";

        String hashTest = hashFunct(test);

        assertTrue(stringContainsNumber(hashTest));

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