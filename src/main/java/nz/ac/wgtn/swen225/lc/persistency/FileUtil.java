package nz.ac.wgtn.swen225.lc.persistency;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * File utility that saves or loads file.
 * This class is exposed for other modules to use.
 */
public class FileUtil {
    public static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    /**
     * Get file path under class path.
     *
     * @param fileName file name
     * @return file path under class path
     */
    public static Path getPath(String fileName) {
        return Paths.get(fileName);
    }

    /**
     * Get file path under tmp dir.
     *
     * @param fileName file name
     * @return file path under tmp dir
     */
    public static Path getTmpPath(String fileName) {
        return Paths.get(TMP_DIR, fileName);
    }


    /**
     * Write string to file under class path.
     *
     * @param path    file path
     * @param content file content
     * @throws IOException IOException
     */
    public static void writeStringToFile(Path path, String content) throws IOException {
        Files.write(path, content.getBytes());
    }

    /**
     * Read file under class path as string.
     *
     * @param path file path
     * @return file content
     * @throws IOException IOException
     */
    public static String readFileAsString(Path path) throws IOException {
        return Files.readString(path);
    }

}
