// See the COPYRIGHT file for copyright and license information
package org.znerd.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Test;
import static org.junit.Assert.*;

public final class ExceptionUtilsTest {

    @Test
    public void testUtilityClassConstructor() throws Exception {
        testUtilityClassConstructor(ExceptionUtils.class);
    }

    // TODO: Move to different class/package
    private static final void testUtilityClassConstructor(Class<?> clazz) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String className = clazz.getName();
        Constructor<?>[] cons = clazz.getDeclaredConstructors();
        assertNotNull("Expected at least 1 constructor in class " + className + '.', cons);

        int constructorCount = cons.length;
        assertEquals("Expected 1 constructor in class " + className + " instead of " + constructorCount + '.', 1, constructorCount);

        final Constructor<?> con = cons[0];
        assertEquals(Modifier.PRIVATE, con.getModifiers());
        
        con.setAccessible(true);
        con.newInstance((Object[]) null);
    }

    @Test
    public void testGetRootCauseOfNull() {
        assertEquals(null, ExceptionUtils.getRootCause(null));
    }

    @Test
    public void testGetRootCauseOfExceptionWithoutCause() {
        Exception e = new Exception();
        assertEquals(e, ExceptionUtils.getRootCause(e));
    }

    @Test
    public void testGetRootCauseOfExceptionWithCauses() {
        Exception root = new Exception();
        Exception e2 = new Exception(root);
        Exception e3 = new Exception(e2);
        Exception e4 = new Exception(e3);
        Exception e5 = new Exception(e4);

        assertEquals(root, ExceptionUtils.getRootCause(e5));
    }
}
