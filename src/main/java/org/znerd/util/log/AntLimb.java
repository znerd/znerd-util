// See the COPYRIGHT file for copyright and license information
package org.znerd.util.log;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/**
 * Limb implementation that sends output via the Ant logging mechanism.
 * <p>
 * Levels are mapped as follows:
 * <ul>
 * <li>{@link LogLevel#DEBUG} is mapped to the Ant {@link Project#MSG_DEBUG} level.
 * <li>{@link LogLevel#INFO} is mapped to the Ant {@link Project#MSG_VERBOSE} level.
 * <li>{@link LogLevel#NOTICE} is mapped to the Ant {@link Project#MSG_INFO} level.
 * <li>{@link LogLevel#WARNING} is mapped to the Ant {@link Project#MSG_WARN} level.
 * <li>{@link LogLevel#ERROR} and {@link LogLevel#FATAL} are mapped to the Ant {@link Project#MSG_ERR} level.
 */
public class AntLimb extends Limb {

    public AntLimb(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("task == null");
        }
        _task = task;
    }

    private final Task _task;

    @Override
    protected void logImpl(LogLevel level, String message, Throwable exception) {
        final int antLevel = convertToAntLevel(level);
        logViaAnt(message, exception, antLevel);
    }

    private static int convertToAntLevel(LogLevel level) {
        int antLevel;
        switch (level) {
            case DEBUG:
                antLevel = Project.MSG_DEBUG;
                break;
            case INFO:
                antLevel = Project.MSG_VERBOSE;
                break;
            case NOTICE:
                antLevel = Project.MSG_INFO;
                break;
            case WARNING:
                antLevel = Project.MSG_WARN;
                break;
            default:
                antLevel = Project.MSG_ERR;
        }
        return antLevel;
    }

    private void logViaAnt(String message, Throwable exception, int antLevel) {
        if (exception == null) {
            _task.log(message, antLevel);
        } else {
            _task.log(message, exception, antLevel);
        }
    }
}
