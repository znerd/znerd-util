package org.znerd.util;

public class Preconditions {
    public static void checkArgument(boolean cond, String errorMessage) throws IllegalArgumentException {
        if (cond) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
    
    private Preconditions() {
    }
}
