// See the COPYRIGHT file for copyright and license information
package org.znerd.util;

import org.junit.Test;
import static org.junit.Assert.*;

import org.znerd.util.test.TestFailedException;
import org.znerd.util.test.TestUtils;

public final class ExceptionUtilsTest {

    @Test
    public void testUtilityClassConstructor() throws TestFailedException {
        TestUtils.testUtilityClassConstructor(ExceptionUtils.class);
    }

    @Test
    public void testGetRootCauseOfNull() {
        assertEquals(null, ExceptionUtils.getRootCause(null));
    }

    @Test
    public void testGetRootCauseOfExceptionWithoutCause() {
        Exception e = new Exception();
        assertEquals(e, ExceptionUtils.getRootCause(e));
    }

    @Test
    public void testGetRootCauseOfExceptionWithCauses() {
        Exception root = new Exception();
        Exception e2 = new Exception(root);
        Exception e3 = new Exception(e2);
        Exception e4 = new Exception(e3);
        Exception e5 = new Exception(e4);

        assertEquals(root, ExceptionUtils.getRootCause(e5));
    }
}
