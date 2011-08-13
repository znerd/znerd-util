// See the COPYRIGHT file for copyright and license information
package org.znerd.test.util.test;

import org.junit.Test;
import static org.junit.Assert.*;

import org.znerd.util.test.TestFailedException;
import org.znerd.util.test.TestUtils;

public class TestUtilsTest {

    @Test
    public void testUtilityClassConstructor() throws Exception {
        TestUtils.testUtilityClassConstructor(TestUtils.class);
    }

    @Test
    public void testNullArg() throws Exception {
        boolean ok = false;
        try {
            TestUtils.testUtilityClassConstructor(null);
        } catch (IllegalArgumentException cause) {
            ok = true;
        }
        assertTrue("Expected IllegalArgumentException for TestUtils.testUtilityClassConstructor(null).", ok);
    }

    @Test
    public void testMultipleConstructors() throws Exception {
        boolean ok = false;
        try {
            TestUtils.testUtilityClassConstructor(MultipleConstructors.class);
        } catch (TestFailedException cause) {
            ok = true;
        }
        assertTrue("Expected TestFailedException for multiple constructors.", ok);
    }

    private static class MultipleConstructors {
        private MultipleConstructors() {
        }

        private MultipleConstructors(String s) {
        }
    }

    @Test
    public void testPublicConstructor() throws Exception {
        boolean ok = false;
        try {
            TestUtils.testUtilityClassConstructor(PublicConstructor.class);
        } catch (TestFailedException cause) {
            ok = true;
        }
        assertTrue("Expected TestFailedException for public constructor.", ok);
    }

    private static class PublicConstructor {
        @SuppressWarnings("unused")
        public PublicConstructor() {
        }
    }

    @Test
    public void testExceptionFromConstructor() throws Exception {
        boolean ok = false;
        try {
            TestUtils.testUtilityClassConstructor(ExceptionFromConstructor.class);
        } catch (TestFailedException cause) {
            ok = true;
        }
        assertTrue("Expected TestFailedException for exception thrown by constructor.", ok);
    }

    private static class ExceptionFromConstructor {
        private ExceptionFromConstructor() {
            throw new RuntimeException();
        }
    }

    @Test
    public void testProperUtilityClassConstructor() throws Exception {
        TestUtils.testUtilityClassConstructor(ProperUtilityClass.class);
    }

    private static class ProperUtilityClass {
        private ProperUtilityClass() {
        }
    }
}
