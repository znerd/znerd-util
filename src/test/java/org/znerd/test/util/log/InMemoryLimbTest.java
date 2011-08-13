package org.znerd.test.util.log;

import static org.junit.Assert.*;

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
    public void testSingleEntry() {
        LogLevel level = LogLevel.FATAL;
        String message = "Log message for testing purpose.";
        Throwable exception = new Error();

        long before = System.currentTimeMillis();
        Limb.log(level, message, exception);
        long after = System.currentTimeMillis();

        InMemoryLimb.Entry entry = getSingleEntry();
        assertTrue(entry.getTimestamp() >= before);
        assertTrue(entry.getTimestamp() <= after);
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
    public void testNullLevelIsDebugLevel() {
        Limb.log(null, "test");
        InMemoryLimb.Entry entry = getSingleEntry();
        assertEquals(LogLevel.DEBUG, entry.getLevel());
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
