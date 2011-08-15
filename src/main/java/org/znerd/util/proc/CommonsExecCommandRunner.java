// Copyright 2011, Ernst de Haan
package org.znerd.util.proc;

import java.io.IOException;
import java.io.File;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.znerd.util.ArrayUtils;

import static org.znerd.util.log.Limb.log;
import static org.znerd.util.log.LogLevel.*;
import static org.znerd.util.text.TextUtils.quote;

public class CommonsExecCommandRunner implements CommandRunner {

    public CommonsExecCommandRunner(long timeOut) {
        _timeOut = timeOut;
    }

    private long _timeOut;

    @Override
    public CommandRunResult runCommand(String command, String... arguments) {
        return runCommand(null, command, arguments);
    }

    @Override
    public CommandRunResult runCommand(File workingDirectory, String command, String... arguments) {
        long start = System.currentTimeMillis();
        ExecuteWatchdog watchdog = createWatchdog();
        CommonsExecProcOutputBuffer buffer = new CommonsExecProcOutputBuffer();
        Executor executor = createExecutor(buffer, watchdog);
        CommandLine commandLine = createCommandLine(command, arguments);
        int exitCode;
        IOException exception;
        log(INFO, "Setting working directory to: " + quote(workingDirectory) + '.');
        String argumentsString = ArrayUtils.printQuoted(arguments, ", ", " and ");
        try {
            if (workingDirectory != null) {
                executor.setWorkingDirectory(workingDirectory); // TODO: Error handling
            }
            log(INFO, "Executing command " + quote(command) + " with argument(s) " + argumentsString + '.');
            exitCode = executor.execute(commandLine);
            exception = null;
        } catch (IOException cause) {
            exitCode = -1;
            exception = cause;
        }

        return createResult(start, buffer, exitCode, exception);
    }

    private ExecuteWatchdog createWatchdog() {
        return (_timeOut > 0L) ? new ExecuteWatchdog(_timeOut) : null;
    }

    private CommandRunResult createResult(long start, CommonsExecProcOutputBuffer buffer, int exitCode, IOException exception) {
        CommandRunResult result = new CommandRunResult();
        result.setDuration(System.currentTimeMillis() - start);
        result.setExitCode(exitCode);
        result.setOutString(buffer.getStdoutAsString());
        result.setErrString(buffer.getStderrAsString());
        result.setException(exception);
        return result;
    }

    private Executor createExecutor(CommonsExecProcOutputBuffer buffer, ExecuteWatchdog watchdog) {
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(buffer);
        executor.setWatchdog(watchdog);
        return executor;
    }

    private CommandLine createCommandLine(String command, String... arguments) {
        CommandLine commandLine = new CommandLine(command);
        commandLine.addArguments(arguments);
        return commandLine;
    }
}
