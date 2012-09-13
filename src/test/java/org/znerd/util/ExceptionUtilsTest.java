// See the COPYRIGHT file for copyright and license information
package org.znerd.util;

import org.junit.Test;
import static org.junit.Assert.*;

import org.znerd.util.ExceptionUtils;
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
        Throwable root = new Exception();
        Throwable e2 = new Exception(root);
        Throwable e3 = new Error(e2);
        Throwable e4 = new RuntimeException(e3);
        Throwable e5 = new AssertionError();
        e5.initCause(e4);
        Throwable e6 = new Throwable(e5);

        assertEquals(root, ExceptionUtils.getRootCause(e6));
    }
}
