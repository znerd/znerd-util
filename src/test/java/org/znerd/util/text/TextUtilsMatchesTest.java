// See the COPYRIGHT file for copyright and license information
package org.znerd.util.text;

import org.junit.Test;
import static org.junit.Assert.*;
import org.znerd.util.text.TextUtils;

public class TextUtilsMatchesTest {
    @Test
    public void testMatchesNull() {
        assertIllegalArgumentException(null, null);
        assertIllegalArgumentException(null, ".*");
        assertIllegalArgumentException("bla", null);
    }
    
    private void assertIllegalArgumentException(String s, String regex) {
        boolean ok = false;
        try {
            TextUtils.matches(s, regex);
        } catch (IllegalArgumentException cause) {
            ok = true;
        }
        assertTrue("Expected IllegalArgumentException.", ok);
    }

    @Test
    public void testMatches() {
        assertTrue(TextUtils.matches("here you go", "go$"));
        assertTrue(TextUtils.matches("here you go", "^here"));
        assertTrue(TextUtils.matches("here you go", "you"));
        assertFalse(TextUtils.matches("here you go", "^you"));
        assertFalse(TextUtils.matches("here you go", "^here go$"));
    }
}