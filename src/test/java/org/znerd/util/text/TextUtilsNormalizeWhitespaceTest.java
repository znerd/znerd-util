// See the COPYRIGHT file for copyright and license information
package org.znerd.util.text;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TextUtilsNormalizeWhitespaceTest {
    @Test
    public void testNull() {
        assertEquals("", TextUtils.normalizeWhitespace(null));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", TextUtils.normalizeWhitespace(""));
    }
    
    @Test
    public void testWhitespaceOnly() {
        assertEquals("", TextUtils.normalizeWhitespace("\t\r\n   \t\t\n\r"));
    }
    
    @Test
    public void testNonWhitespaceOnly() {
        String s = "sldfjasdjfhgakufyg28t1234e8agdfjkhbxc,nm";
        assertEquals(s, TextUtils.normalizeWhitespace(s));
    }
    
    @Test
    public void testTrailingWhitespace() {
        String out = "sldfjasdjfhgakufyg28t1234e8agdfjkhbxc,nm"; 
        String in = out + "\t\r\n   \n";
        assertEquals(out, TextUtils.normalizeWhitespace(in));
    }
    
    @Test
    public void testPrefixingWhitespace() {
        String out = "sldfjasdjfhgakufyg28t1234e8agdfjkhbxc,nm"; 
        String in = "\t\r\n   \n" + out;
        assertEquals(out, TextUtils.normalizeWhitespace(in));
    }
    
    @Test
    public void testInnerWhitespace() {
        String word = "Hello";
        String out = word + " " + word; 
        String in = word + "\t\r\n" + word;
        assertEquals(out, TextUtils.normalizeWhitespace(in));
    }
    
    
    @Test
    public void testCombination() {
        String word = "Hello";
        String out = word + " " + word + " " + word; 
        String in = " \t\n\t" + word + "\t\r\n" + word + "\n \n" + word + "\n\n\n\r\t";
        assertEquals(out, TextUtils.normalizeWhitespace(in));
    }
}
