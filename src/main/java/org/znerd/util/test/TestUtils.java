// See the COPYRIGHT file for copyright and license information
package org.znerd.util.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.znerd.util.text.TextUtils;

public class TestUtils {

    /**
     * Tests if the specified utility class has a matching constructor. The following is checked:
     * <ul>
     * <li>There must be exactly 1 constructor.
     * <li>This constructor must be marked <code>private<code>.
     * <li>Invoking the constructor should not throw any exception.
     * </ul>
     * 
     * @param clazz The class to check, should not be <code>null</code>.
     * @throws TestFailedException In case an issue is found.
     */
    public static final void testUtilityClassConstructor(Class<?> clazz) throws TestFailedException {
        String className = clazz.getName();
        Constructor<?>[] cons = clazz.getDeclaredConstructors();
        if (cons == null) {
            throw new TestFailedException("Expected exactly 1 constructor in class " + className + ". However, getDeclaredConstructors() returned (null).");
        }

        int constructorCount = cons.length;
        if (constructorCount != 1) {
            throw new TestFailedException("Expected exactly 1 constructor in class " + className + ". However, " + constructorCount + " were found.");
        }

        final Constructor<?> con = cons[0];
        int modifiers = con.getModifiers();
        if (Modifier.PRIVATE != modifiers) {
            throw new TestFailedException("Expected " + className + " constructor to be private (modifiers==" + Modifier.PRIVATE + "). Instead: modifiers==" + modifiers + '.');
        }

        try {
            con.setAccessible(true);
            con.newInstance((Object[]) null);
        } catch (Throwable cause) {
            throw new TestFailedException("Calling the constructor resulted in an exception of class " + cause.getClass().getName() + ". The exception message is: " + TextUtils.quote(cause.getMessage()) + '.', cause);
        }
    }

    private TestUtils() {
    }
}
