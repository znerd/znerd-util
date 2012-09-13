// See the COPYRIGHT file for copyright and license information
package org.znerd.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.znerd.util.ArrayUtils;

public class ArrayUtilsCountElementsTest {
    @Test
    public void testNull() {
        assertEquals(0, ArrayUtils.countElements(null));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, ArrayUtils.countElements(new String[0]));
    }

    @Test
    public void testOneSizeArray() {
        assertEquals(1, ArrayUtils.countElements(new String[] { "Hi there" }));
    }

    @Test
    public void testMultipleArrayElements() {
        assertEquals(19, ArrayUtils.countElements(new String[19]));
    }
}
