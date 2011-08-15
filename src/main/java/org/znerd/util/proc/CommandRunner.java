// Copyright 2011, Ernst de Haan
package org.znerd.util.proc;

public interface CommandRunner {
    CommandRunResult runCommand(String command, String... arguments);
}
