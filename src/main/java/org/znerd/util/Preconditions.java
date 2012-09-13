// See the COPYRIGHT file for copyright and license information
package org.znerd.util;

public class Preconditions {
    public static void checkArgument(boolean conditionThatSignalsAnError, String errorMessage) throws IllegalArgumentException {
        if (conditionThatSignalsAnError) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
    
    private Preconditions() {
    }
}
