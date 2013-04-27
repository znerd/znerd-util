// See the COPYRIGHT file for copyright and license information
package org.znerd.util.log;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LogLevelSmallerThanOrEqualToTest {

    @Test
    public void testDebug() {
        final LogLevel level = LogLevel.DEBUG;
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.DEBUG));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.INFO));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.NOTICE));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.WARNING));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.ERROR));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.FATAL));
    }

    @Test
    public void testInfo() {
        final LogLevel level = LogLevel.INFO;
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.DEBUG));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.INFO));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.NOTICE));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.WARNING));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.ERROR));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.FATAL));
    }

    @Test
    public void testNotice() {
        final LogLevel level = LogLevel.NOTICE;
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.DEBUG));
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.INFO));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.NOTICE));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.WARNING));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.ERROR));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.FATAL));
    }

    @Test
    public void testWarning() {
        final LogLevel level = LogLevel.WARNING;
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.DEBUG));
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.INFO));
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.NOTICE));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.WARNING));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.ERROR));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.FATAL));
    }

    @Test
    public void testError() {
        final LogLevel level = LogLevel.ERROR;
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.DEBUG));
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.INFO));
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.NOTICE));
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.WARNING));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.ERROR));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.FATAL));
    }

    @Test
    public void testFatal() {
        final LogLevel level = LogLevel.FATAL;
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.DEBUG));
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.INFO));
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.NOTICE));
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.WARNING));
        assertFalse(level.isSmallerThanOrEqualTo(LogLevel.ERROR));
        assertTrue(level.isSmallerThanOrEqualTo(LogLevel.FATAL));
    }
}
