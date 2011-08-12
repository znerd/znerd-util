// See the COPYRIGHT file for copyright and license information
package org.znerd.util.io;

import java.io.File;

import java.io.IOException;

import static org.znerd.util.text.TextUtils.isEmpty;
import static org.znerd.util.text.TextUtils.quote;

import org.znerd.util.log.Limb;
import org.znerd.util.log.LogLevel;

public class DirectoryUtils {

    /**
     * Checks if the specified abstract path name refers to an existing directory and creates the directory if appropriate.
     * 
     * @param description the description of the directory, cannot be <code>null</code>.
     * @param path the abstract path name as a {@link File} object.
     * @param mustBeReadable <code>true</code> if the directory must be readable.
     * @param mustBeWritable <code>true</code> if the directory must be writable.
     * @throws IllegalArgumentException if <code>description == null || description.trim().equals("") || path == null</code>.
     * @throws IOException if <code>! path.exists() || ! path.isDirectory() || (mustBeReadable &amp;&amp; !path.canRead()) || (mustBeWritable &amp;&amp; !path.canWrite())</code>.
     */
    public static final void checkDir(String description, File path, boolean mustBeReadable, boolean mustBeWritable, boolean createIfNonexistent) throws IllegalArgumentException, IOException {
        validateCheckDirPreconditions(description, path);
        createDirectoryIfAppropriate(description, path, createIfNonexistent);
        validatePathIsExistingDir(description, path);
        validatePathReadability(description, path, mustBeReadable);
        validatePathWritability(description, path, mustBeWritable);
    }

    private static void validateCheckDirPreconditions(String description, File path) {
        if (isEmpty(description)) {
            throw new IllegalArgumentException("description is null or empty");
        } else if (path == null) {
            throw new IllegalArgumentException("path == null");
        }
    }

    private static void createDirectoryIfAppropriate(String description, File path, boolean createIfNonexistent) throws IOException {
        if (createIfNonexistent && !path.exists()) {
            Limb.log(LogLevel.INFO, "Creating directory " + quote(path) + '.');
            if (!path.mkdirs()) {
                throw new IOException(description + " (\"" + path + "\") could not be created.");
            }
        }
    }

    private static void validatePathIsExistingDir(String description, File path) throws IOException {
        if (!path.exists()) {
            throw new IOException(description + " (\"" + path + "\") does not exist.");
        } else if (!path.isDirectory()) {
            throw new IOException(description + " (\"" + path + "\") is not a directory.");
        }
    }

    private static void validatePathReadability(String description, File path, boolean mustBeReadable) throws IOException {
        if (mustBeReadable && (!path.canRead())) {
            throw new IOException(description + " (\"" + path + "\") is not readable.");
        }
    }

    private static void validatePathWritability(String description, File path, boolean mustBeWritable) throws IOException {
        if (mustBeWritable && (!path.canWrite())) {
            throw new IOException(description + " (\"" + path + "\") is not writable.");
        }
    }

    public static final File createTempDir() throws IOException {
        return createTempDir(true);
    }

    public static final File createTempDir(boolean deleteOnExit) throws IOException {
        final File path = createTempFile();
        deleteTempFile(path);
        createTempDir(path, deleteOnExit);
        return path;
    }

    private static File createTempFile() throws IOException {
        final File path = File.createTempFile("temp", Long.toString(System.nanoTime()));
        return path;
    }

    private static void deleteTempFile(File path) throws IOException {
        if (!path.delete()) {
            throw new IOException("Failed to delete temporary file " + quote(path.getAbsolutePath()) + '.');
        }
    }

    private static void createTempDir(File path, boolean deleteOnExit) throws IOException {
        if (!path.mkdir()) {
            throw new IOException("Failed to create temporary directory: " + quote(path.getAbsolutePath()) + '.');
        }
        if (deleteOnExit) {
            path.deleteOnExit();
        }
    }

    private DirectoryUtils() {
    }
}
