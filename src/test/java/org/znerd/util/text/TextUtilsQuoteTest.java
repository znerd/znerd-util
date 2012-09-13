// See the COPYRIGHT file for copyright and license information
package org.znerd.util.text;

import org.junit.Test;
import static org.junit.Assert.*;
import org.znerd.util.text.TextUtils;

public class TextUtilsQuoteTest {
    @Test
    public void testNull() {
        assertEquals("(null)", TextUtils.quote(null));
    }
    
    @Test
    public void testObjectToStringReturnsNull() {
        Object o = new Object() {
            public String toString() {
                return null;
            }
        };
        assertEquals("(null)", TextUtils.quote(o));
    }

    @Test
    public void testEmptyString() {
        assertEquals("\"\"", TextUtils.quote(""));
    }

    @Test
    public void testStringWithoutQuotes() {
        assertEquals("\"bla\"", TextUtils.quote("bla"));
    }

    @Test
    public void testStringWithQuotes() {
        assertEquals("\"bla \\\"yet another bla\\\"\"", TextUtils.quote("bla \"yet another bla\""));
    }

    @Test
    public void testStringWithBackslashes() {
        assertEquals("\"\\\\ bla \\\\ bla \\\\\"", TextUtils.quote("\\ bla \\ bla \\"));
    }
}
