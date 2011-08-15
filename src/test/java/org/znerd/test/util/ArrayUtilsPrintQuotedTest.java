// See the COPYRIGHT file for copyright and license information
package org.znerd.test.util;

import static org.junit.Assert.*;
import org.junit.Test;
import org.znerd.util.ArrayUtils;

public class ArrayUtilsPrintQuotedTest {

    @Test
    public void testInvalidArguments() {
        boolean ok = false;
        try {
            ArrayUtils.printQuoted(null, "infix", "beforeLast");
        } catch (IllegalArgumentException cause) {
            ok = true;
        }
        assertTrue("Expected IllegalArgumentException", ok);
    }
    
    @Test
    public void testZeroElements() {
        String[] array = new String[0];
        String result = ArrayUtils.printQuoted(array, "infix", "beforeLast");
        assertEquals("", result);
    }
    
    @Test
    public void testOneElement() {
        String[] array = new String[] { "one" };
        String result = ArrayUtils.printQuoted(array, "infix", "beforeLast");
        assertEquals("\"one\"", result);
    }
    
    @Test
    public void testTwoElements() {
        String[] array = new String[] { "one", "two" };
        String result = ArrayUtils.printQuoted(array, ", ", " and ");
        assertEquals("\"one\" and \"two\"", result);
    }
    
    @Test
    public void testThreeElements() {
        String[] array = new String[] { "one", "two", "three" };
        String result = ArrayUtils.printQuoted(array, ", ", " and ");
        assertEquals("\"one\", \"two\" and \"three\"", result);
    }
    
    @Test
    public void testFourElements() {
        String[] array = new String[] { "one", "two", "three", "four" };
        String result = ArrayUtils.printQuoted(array, ", ", " and ");
        assertEquals("\"one\", \"two\", \"three\" and \"four\"", result);
    }
}
