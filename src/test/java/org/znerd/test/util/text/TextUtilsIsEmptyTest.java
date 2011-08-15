// See the COPYRIGHT file for copyright and license information
package org.znerd.test.util.text;

import org.junit.Test;
import static org.junit.Assert.*;
import org.znerd.util.text.TextUtils;

public class TextUtilsIsEmptyTest {
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
    }
}
