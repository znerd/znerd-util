// See the COPYRIGHT file for copyright and license information
package org.znerd.test.util.log;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.znerd.util.log.InMemoryLimb;
import org.znerd.util.log.Limb;
import org.znerd.util.log.LogLevel;

public class InMemoryLimbTest {

    @Before
    public void setUp() {
        _limb = new InMemoryLimb();
        Limb.setLogger(_limb);
    }

    private InMemoryLimb _limb;

    @Test
    public void testNoEntries() {
        List<InMemoryLimb.Entry> entries = _limb.getEntries();
        assertNotNull(entries);
        assertEquals(0, entries.size());
    }

    @Test
    public void testTwoArgLog() {
        LogLevel level = LogLevel.INFO;
        String message = "Log message for testing purpose.";

        Limb.log(level, message);

        InMemoryLimb.Entry entry = getSingleEntry();
        assertEquals(level, entry.getLevel());
        assertEquals(message, entry.getMessage());
        assertEquals(null, entry.getException());
    }

    @Test
    public void testThreeArgLog() {
        LogLevel level = LogLevel.FATAL;
        String message = "Log message for testing purpose.";
        Throwable exception = new Error();

        Limb.log(level, message, exception);

        InMemoryLimb.Entry entry = getSingleEntry();
        assertEquals(level, entry.getLevel());
        assertEquals(message, entry.getMessage());
        assertEquals(exception, entry.getException());
    }

    private InMemoryLimb.Entry getSingleEntry() {
        List<InMemoryLimb.Entry> entries = _limb.getEntries();
        assertNotNull(entries);
        assertEquals(1, entries.size());

        InMemoryLimb.Entry entry = entries.get(0);
        assertNotNull(entry);
        return entry;
    }

    @Test
    public void testAllLogLevels() {
        Limb.log(LogLevel.DEBUG, "Message 1");
        Limb.log(LogLevel.INFO, "Message 2", new RuntimeException());
        Limb.log(LogLevel.NOTICE, "Message 3");
        Limb.log(LogLevel.ERROR, "Message 4");
        Limb.log(LogLevel.FATAL, "Message 5");

        Iterator<InMemoryLimb.Entry> entries = _limb.getEntries().iterator();
        assertEquals(LogLevel.DEBUG, entries.next().getLevel());
        assertEquals(LogLevel.INFO, entries.next().getLevel());
        assertEquals(LogLevel.NOTICE, entries.next().getLevel());
        assertEquals(LogLevel.ERROR, entries.next().getLevel());
        assertEquals(LogLevel.FATAL, entries.next().getLevel());
    }

    @Test
    public void testTimestamp() {
        long before1 = System.currentTimeMillis();
        Limb.log(LogLevel.DEBUG, "Message 1");
        long before2 = System.currentTimeMillis();
        Limb.log(LogLevel.INFO, "Message 2", new RuntimeException());
        long after = System.currentTimeMillis();

        List<InMemoryLimb.Entry> entries = _limb.getEntries();
        long timestamp1 = entries.get(0).getTimestamp();
        assertTrue(timestamp1 >= before1);
        assertTrue(timestamp1 <= before2);

        long timestamp2 = entries.get(1).getTimestamp();
        assertTrue(timestamp2 >= before2);
        assertTrue(timestamp2 <= after);
    }

    @Test
    public void testNullLevelIsDebugLevel() {
        Limb.log(null, "test");
        InMemoryLimb.Entry entry = getSingleEntry();
        assertEquals(LogLevel.DEBUG, entry.getLevel());
    }

    @Test
    public void testNullMessageIsEmptyMessage() {
        Limb.log(LogLevel.INFO, null, new Error());
        InMemoryLimb.Entry entry = getSingleEntry();
        assertEquals("", entry.getMessage());
    }

    @Test
    public void testEntriesListUnmodifiable() {
        Limb.log(null, "test");
        List<InMemoryLimb.Entry> entries = _limb.getEntries();

        boolean ok = false;
        try {
            entries.remove(0);
        } catch (UnsupportedOperationException cause) {
            ok = true;
        }

        assertTrue("Expected UnsupportedOperationException, since list of entries should be unmodifiable.", ok);
    }
}
