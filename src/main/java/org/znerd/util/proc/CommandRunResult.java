// Copyright 2011, Ernst de Haan
package org.znerd.util.proc;

public class CommandRunResult {

    public void setDuration(long duration) {
        _duration = duration;
    }

    private long _duration;

    public long getDuration() {
        return _duration;
    }

    public void setException(Throwable exception) {
        _exception = exception;
    }

    private Throwable _exception;

    public Throwable getException() {
        return _exception;
    }

    public void setExitCode(int exitCode) {
        _exitCode = exitCode;
    }

    private int _exitCode = 0;

    public int getExitCode() {
        return _exitCode;
    }

    public boolean isSucceeded() {
        return _exitCode == 0 && _exception == null;
    }

    public boolean isFailed() {
        return !isSucceeded();
    }

    public void setStdoutString(String outString) {
        _stdoutString = outString;
    }

    private String _stdoutString;

    public String getStdoutString() {
        return _stdoutString;
    }

    public void setStderrString(String stderrString) {
        _stderrString = stderrString;
    }

    private String _stderrString;

    public String getStderrString() {
        return _stderrString;
    }
}
