// See the COPYRIGHT file for copyright and license information
package org.znerd.test.util.text;

import org.junit.Test;
import static org.junit.Assert.*;
import org.znerd.util.text.TextUtils;

public class TextUtilsQuoteTest {
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
}
