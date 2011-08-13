// See the COPYRIGHT file for copyright and license information
package org.znerd.test.util;

import org.junit.Test;
import static org.junit.Assert.*;
import org.znerd.util.ArrayUtils;
import org.znerd.util.test.TestUtils;

public class ArrayUtilsTest {
    @Test
    public void testUtilityClassConstructor() throws Exception {
        TestUtils.testUtilityClassConstructor(ArrayUtils.class);
    }

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
