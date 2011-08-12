// See the COPYRIGHT file for copyright and license information
package org.znerd.util.text;

import org.junit.Test;
import static org.junit.Assert.*;
import org.znerd.util.test.TestFailedException;
import org.znerd.util.test.TestUtils;

public class TextUtilsTest {
    @Test
    public void testUtilityClassConstructor() throws TestFailedException {
        TestUtils.testUtilityClassConstructor(TextUtils.class);
    }

    @Test
    public void testQuoteNull() {
        assertEquals("(null)", TextUtils.quote(null));
    }

    @Test
    public void testQuoteEmptyString() {
        assertEquals("\"\"", TextUtils.quote(""));
    }

    @Test
    public void testQuoteNonEmptyStringWithoutQuotes() {
        assertEquals("\"bla\"", TextUtils.quote("bla"));
    }

    @Test
    public void testQuoteNonEmptyStringWithQuotes() {
        assertEquals("\"bla \"yet another bla\"\"", TextUtils.quote("bla \"yet another bla\""));
    }
    
    @Test
    public void testIsEmptyNull() {
        assertTrue(TextUtils.isEmpty(null));
    }
    
    @Test
    public void testIsEmptyEmptyString() {
        assertTrue(TextUtils.isEmpty(""));
    }
    
    @Test
    public void testIsEmptyWhitespace() {
        assertTrue(TextUtils.isEmpty(" \t\n\r"));
    }
    
    @Test
    public void testIsEmptyNonEmptyString() {
        assertFalse(TextUtils.isEmpty("b \t\n\r"));
    }}
