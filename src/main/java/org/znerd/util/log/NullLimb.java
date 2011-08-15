// See the COPYRIGHT file for copyright and license information
package org.znerd.util.log;

/**
 * Limb implementation that discards all log messages (technically: {@link #logImpl(LogLevel, String, Throwable)} has an empty implementation.
 */
public class NullLimb extends Limb {

    public NullLimb() {
    }
    
    protected void logImpl(LogLevel level, String message, Throwable exception) {
    }
}
