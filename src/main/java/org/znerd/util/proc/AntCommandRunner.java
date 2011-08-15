// Copyright 2011, Ernst de Haan
package org.znerd.util.proc;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Execute;
import org.apache.tools.ant.taskdefs.ExecuteWatchdog;
import static org.znerd.util.log.Limb.log;

import org.znerd.util.ArrayUtils;
import org.znerd.util.log.LogLevel;
import static org.znerd.util.text.TextUtils.quote;

public class AntCommandRunner implements CommandRunner {

    public AntCommandRunner(Project project, long timeOut) {
        if (project == null) {
            throw new IllegalArgumentException("project == null");
        }
        _project = project;
        _timeOut = timeOut;
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
        int exitCode = 0;
        if (workingDirectory != null) {
            try {
                setWorkingDirectory(workingDirectory);
            } catch (IOException cause) {
                result.setException(cause);
                exitCode = -1;
            }
        }
        if (exitCode == 0) {
            String[] cmdline = createCmdline(command, arguments);
            ExecuteWatchdog watchdog = (_timeOut > 0L) ? new ExecuteWatchdog(_timeOut) : null;
            Execute execute = new Execute(buffer, watchdog);
            execute.setAntRun(_project);
            execute.setCommandline(cmdline);
            String argumentsString = ArrayUtils.printQuoted(arguments, ", ", " and ");

            log(LogLevel.INFO, "Running command " + quote(command) + " with argument(s): " + quote(argumentsString) + '.');
            try {
                exitCode = execute.execute();
            } catch (IOException cause) {
                result.setException(cause);
                exitCode = -1;
            }
        }

        result.setDuration(System.currentTimeMillis() - start);
        result.setExitCode(exitCode);
        result.setOutString(buffer.getOutString());
        result.setErrString(buffer.getErrString());

        return result;
    }

    private void setWorkingDirectory(File path) throws IOException {
        // TODO: Implement setWorkingDirectory
    }

    private String[] createCmdline(String command, String... arguments) {
        final int argumentCount = arguments.length;
        final String[] cmdline = new String[argumentCount + 1];
        cmdline[0] = command;
        System.arraycopy(arguments, 0, cmdline, 1, argumentCount);
        return cmdline;
    }
}
