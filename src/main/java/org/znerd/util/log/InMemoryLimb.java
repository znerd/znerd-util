// See the COPYRIGHT file for copyright and license information
package org.znerd.util.log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryLimb extends Limb {
    protected void logImpl(LogLevel level, String message, Throwable exception) {
        _entries.add(new Entry(level, message, exception));
    }

    private List<Entry> _entries = new ArrayList<Entry>();

    public static class Entry {
        Entry(LogLevel level, String message, Throwable exception) {
            _timestamp = System.currentTimeMillis();
            _level = level;
            _message = message;
            _exception = exception;
        }

        private long _timestamp;
        private LogLevel _level;
        private String _message;
        private Throwable _exception;

        public long getTimestamp() {
            return _timestamp;
        }
        
        public LogLevel getLevel() {
            return _level;
        }

        public String getMessage() {
            return _message;
        }

        public Throwable getException() {
            return _exception;
        }
    }
    
    public List<Entry> getEntries() {
        return Collections.unmodifiableList(_entries);
    }
}
