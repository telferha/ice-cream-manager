/**
 * 
 */
package io.github.pbremer.icecreammanager.testutils;

import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.apache.commons.io.FileUtils.getFile;
import static org.apache.commons.io.FileUtils.getTempDirectory;
import static org.apache.commons.io.FileUtils.getTempDirectoryPath;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Patrick Bremer
 */
public class TestUtils {

    public static final Logger log = LoggerFactory.getLogger(TestUtils.class);

    /**
     * Copys a file to the temp directory and renames it to specified value
     * 
     * @param path
     *            path of file
     * @param name
     *            name to be changed to
     * @return final fully qualified path
     * @throws IOException
     */
    public static String cpToTmp(String path, String name) throws IOException {
	File source = new File(path);
	log.debug("Copying to temp dir: {} and renaming to: {}",
	        getTempDirectoryPath(), name);
	File dest = new File(getTempDirectory(), name);
	deleteQuietly(dest);
	copyFile(source, dest);
	return getFile(getTempDirectory(), name).getAbsolutePath();
    }

    // @Test
    public void cpToTmpTest() throws IOException {
	String path =
	        cpToTmp("/home/patrick/github/ice-cream-manager/src/main/resources/banner.txt",
	                "THIS IS A TEST");

	log.info(path);
    }
}
