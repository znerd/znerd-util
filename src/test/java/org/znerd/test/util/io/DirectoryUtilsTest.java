// See the COPYRIGHT file for copyright and license information
package org.znerd.test.util.io;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.znerd.util.io.DirectoryUtils;
import org.znerd.util.test.TestUtils;

import static org.junit.Assert.*;

public class DirectoryUtilsTest {

    @Test
    public void testUtilityClassConstructor() throws Exception {
        TestUtils.testUtilityClassConstructor(DirectoryUtils.class);
    }

    @Test
    public void testCheckDirIllegalArguments() throws IOException {
        expectIllegalArgumentExceptionFor(null, new File("/tmp"), false, false, false);
        expectIllegalArgumentExceptionFor("", new File("/tmp"), false, false, false);
        expectIllegalArgumentExceptionFor("  ", new File("/tmp"), false, false, false);
        expectIllegalArgumentExceptionFor("Temp folder", null, false, false, false);
    }

    private void expectIllegalArgumentExceptionFor(String description, File path, boolean mustBeReadable, boolean mustBeWritable, boolean createIfNonexistent) throws IOException {
        boolean ok = false;
        try {
            DirectoryUtils.checkDir(description, path, mustBeReadable, mustBeWritable, createIfNonexistent);
        } catch (IllegalArgumentException e) {
            ok = true;
        }
        assertTrue("Expected IllegalArgumentException.", ok);
    }
    
    private void testCheckDirCreateDirectory() throws IOException {
        
    }
}
