// Copyright 2011, Ernst de Haan
package org.znerd.util.proc;

import static org.znerd.util.log.Limb.log;
import static org.znerd.util.text.TextUtils.quote;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.taskdefs.ExecuteWatchdog;
import org.apache.tools.ant.taskdefs.condition.Os;
import org.znerd.util.ArrayUtils;
import org.znerd.util.log.LogLevel;

public class AntCommandRunner implements CommandRunner {

    public AntCommandRunner(Project antProject, long commandTimeOut) {
        if (antProject == null) {
            throw new IllegalArgumentException("antProject == null");
        }
        _project = antProject;
        _timeOut = commandTimeOut;
    }

    private Project _project;
    private long _timeOut;

    @Override
    public CommandRunResult runCommand(String command, String... arguments) {
        return runCommand(null, command, arguments);
    }

    public CommandRunResult runCommand(File workingDirectory, String command, String... arguments) {
        long start = System.currentTimeMillis();
        CommandRunResult result = new CommandRunResult();
        AntProcOutputBuffer buffer = new AntProcOutputBuffer();
        int exitCode;
        Throwable exception = null;
        try {
        	exitCode = runCommand(workingDirectory, command, buffer, arguments);
		} catch (Exception cause2) {
			exitCode = -1;
			exception = cause2;
		}

        return enrichResult(start, result, buffer, exitCode, exception);
    }

	private int runCommand(File workingDirectory, String command, AntProcOutputBuffer buffer, String... arguments)
    throws IOException, Exception {
		Execute execute = createExecute(workingDirectory, command, buffer, arguments);
		try {
		    return execute.execute();
		} catch (Exception cause) {
			if (Os.isFamily("windows")) {
				log(LogLevel.DEBUG, "Failed to execute command, retrying with 'CMD'.");
				String cmdCommand = "CMD";
				List<String> cmdArgumentList = new ArrayList<String>(Arrays.asList(arguments));
				cmdArgumentList.add(0, "/c");
				cmdArgumentList.add(1, command);
				String[] cmdArguments = new String[0];
				cmdArguments = (String[]) cmdArgumentList.toArray(cmdArguments);
				Execute cmdExecute = createExecute(workingDirectory, cmdCommand, buffer, cmdArguments);
				return cmdExecute.execute();
			} else {
				throw cause;
			}
		}
	}

    private CommandRunResult enrichResult(long start, CommandRunResult result, AntProcOutputBuffer buffer, int exitCode, Throwable exception) {
        result.setDuration(System.currentTimeMillis() - start);
        result.setExitCode(exitCode);
        result.setStdoutString(buffer.getStdoutString());
        result.setStderrString(buffer.getStderrString());
        result.setException(exception);
        return result;
    }

    private Execute createExecute(File workingDirectory, String command, AntProcOutputBuffer buffer, String... arguments) {
        String[] commandLine = createCommandLine(command, arguments);
        Execute execute = createExecuteFromBuffer(buffer);
        if (workingDirectory != null) {
            execute.setWorkingDirectory(workingDirectory);
        }
        execute.setAntRun(_project);
        execute.setCommandline(commandLine);
        String argumentsString = ArrayUtils.printQuoted(arguments, ", ", " and ");

        log(LogLevel.INFO, "Running command " + quote(command) + " with argument(s): " + quote(argumentsString) + '.');
        return execute;
    }

    private Execute createExecuteFromBuffer(AntProcOutputBuffer buffer) {
        ExecuteWatchdog watchdog = createWatchdog();
        Execute execute = new Execute(buffer, watchdog);
        return execute;
    }

    private ExecuteWatchdog createWatchdog() {
        ExecuteWatchdog watchdog = (_timeOut > 0L) ? new ExecuteWatchdog(_timeOut) : null;
        return watchdog;
    }

    private String[] createCommandLine(String command, String... arguments) {
        final int argumentCount = arguments.length;
        final String[] cmdline = new String[argumentCount + 1];
        cmdline[0] = command;
        System.arraycopy(arguments, 0, cmdline, 1, argumentCount);
        return cmdline;
    }
}
