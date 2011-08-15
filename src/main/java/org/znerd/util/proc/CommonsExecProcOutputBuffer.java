// Copyright 2011, Ernst de Haan
package org.znerd.util.proc;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.StreamPumper;

/**
 * An <code>ExecuteStreamHandler</code> implementation that stores all output in a buffer.
 */
class CommonsExecProcOutputBuffer extends Object implements ExecuteStreamHandler {
    
    @Override
    public void setProcessInputStream(OutputStream os) {
        // ignore, we don't send input to the process
    }

    @Override
    public void setProcessOutputStream(InputStream is) {
        _stdoutPumpThread = new Thread(new StreamPumper(is, _stdoutBuffer));
    }
    
    private Thread _stdoutPumpThread;
    private final ByteArrayOutputStream _stdoutBuffer = new ByteArrayOutputStream();

    @Override
    public void setProcessErrorStream(InputStream is) {
        _stderrPumpThread = new Thread(new StreamPumper(is, _stderrBuffer));
    }
    
    private Thread _stderrPumpThread;
    private final ByteArrayOutputStream _stderrBuffer = new ByteArrayOutputStream();

    @Override
    public void start() {
        _stdoutPumpThread.start();
        _stderrPumpThread.start();
    }

    @Override
    public void stop() {
        join(_stdoutPumpThread);
        join(_stderrPumpThread);
    }

    private void join(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public void copyStdoutTo(OutputStream os) throws IOException {
        copyToOutputStream(_stdoutBuffer, os);
    }

    private void copyToOutputStream(ByteArrayOutputStream out, OutputStream os) throws IOException {
        if (os == null) {
            throw new IllegalArgumentException("os == null");
        }
        out.writeTo(os);
    }

    public String getStdoutAsString() {
        return _stdoutBuffer.toString();
    }

    public void copyStderrTo(OutputStream os) throws IOException {
        copyToOutputStream(_stderrBuffer, os);
    }

    public String getStderrAsString() {
        return _stderrBuffer.toString();
    }
}
