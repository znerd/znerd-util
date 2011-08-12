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

    @Test
    public void testCheckDirCreateDirectory() throws IOException {
        testCheckDirCreateDirectory(false, false);
        testCheckDirCreateDirectory(true, false);
        testCheckDirCreateDirectory(false, true);
        testCheckDirCreateDirectory(true, true);
    }

    private void testCheckDirCreateDirectory(boolean mustBeReadable, boolean mustBeWritable) throws IOException {
        File path = createTempFileObject();
        assertFalse(path.exists());
        DirectoryUtils.checkDir("Temporary dir for unit test.", path, mustBeReadable, mustBeWritable, true);
        assertTrue(path.exists());
    }

    private File createTempFileObject() {
        File path = new File(TEMPDIR_PATH, "" + System.nanoTime() + "-" + Math.random());
        path.deleteOnExit();
        return path;
    }
    
    private static final String TEMPDIR = System.getProperty("java.io.tmpdir");
    private static final File TEMPDIR_PATH = new File(TEMPDIR);

    @Test
    public void testCheckDirFailCreateDirectory() throws IOException {
        testCheckDirFailCreateDirectory(false, false);
        testCheckDirFailCreateDirectory(true, false);
        testCheckDirFailCreateDirectory(false, true);
        testCheckDirFailCreateDirectory(true, true);
    }

    private void testCheckDirFailCreateDirectory(boolean mustBeReadable, boolean mustBeWritable) throws IOException {
        File path = createTempFileObject();
        path.createNewFile();
        File subpath = new File(path, "sub");
        boolean ok = false;
        try {
            DirectoryUtils.checkDir("Temporary dir for unit test.", subpath, mustBeReadable, mustBeWritable, true);
        } catch (IOException e) {
            ok = true;
        }
        assertTrue("Expected IOException.", ok);
    }
    
    @Test
    public void testCheckDirDontCreateDirectory() throws IOException {
        File path = createTempFileObject();
        testCheckDirDontCreateDirectory(path, false, false);
        testCheckDirDontCreateDirectory(path, true, false);
        testCheckDirDontCreateDirectory(path, false, true);
        testCheckDirDontCreateDirectory(path, true, true);
    }

    private void testCheckDirDontCreateDirectory(File path, boolean mustBeReadable, boolean mustBeWritable) throws IOException {
        boolean ok = false;
        try {
            DirectoryUtils.checkDir("Temporary dir for unit test.", path, mustBeReadable, mustBeWritable, false);
        } catch (IOException e) {
            ok = true;
        }
        
        assertTrue("Expected IOException.", ok);
    }
}
