// See the COPYRIGHT file for copyright and license information
package org.znerd.util.test;

public class TestFailedException extends Exception {

    TestFailedException(String message) {
        super(message);
    }

    TestFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    private static final long serialVersionUID = -3717216114160793123L;
}
