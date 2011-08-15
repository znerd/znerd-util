// Copyright 2011, Ernst de Haan
package org.znerd.util.proc;

import java.io.File;

public interface CommandRunner {
    CommandRunResult runCommand(String command, String... arguments);
    CommandRunResult runCommand(File workingDirectory, String command, String... arguments);
}
