// See the COPYRIGHT file for copyright and license information
package org.znerd.util.text;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TextUtilsFuzzyEqualsTest {
    
    @Test
    public void testTwoNulls() {
        String s1 = null;
        String s2 = null;
        assertTrue(TextUtils.fuzzyEquals(s1, s2));
    }

    @Test
    public void testCompareNullAndEmptyString() {
        String s1 = null;
        String s2 = "";
        assertTrue(TextUtils.fuzzyEquals(s1, s2));
    }
    
    @Test
    public void testCompareNullAndWhitespace() {
        String s1 = null;
        String s2 = "\t\r\n   \n";
        assertTrue(TextUtils.fuzzyEquals(s1, s2));
    }

    @Test
    public void testCompareEmptyStringAndNull() {
        String s1 = "";
        String s2 = null;
        assertTrue(TextUtils.fuzzyEquals(s1, s2));
    }

    @Test
    public void testCompareWhitespaceAndNull() {
        String s1 = "\t\r\n   \n";
        String s2 = null;
        assertTrue(TextUtils.fuzzyEquals(s1, s2));
    }

    @Test
    public void testSameString() {
        String s = "\t\r\nBL A  theRe\n";
        assertTrue(TextUtils.fuzzyEquals(s, s));
    }
    
    @Test
    public void testCompareFuzzilyEqual() {
        String s1 = "\t\r\nBLA  theRe\n";
        String s2 = "BLA there";
        assertTrue(TextUtils.fuzzyEquals(s1, s2));
    }
    
    @Test
    public void testCompareFuzzilyEqualExceptExtraWhitespace() {
        String s1 = "\t\r\nBL A  theRe\n";
        String s2 = "BLA there";
        assertFalse(TextUtils.fuzzyEquals(s1, s2));
    }
}
