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
    /**
     * Write string to file under class path.
     *
     * @param fileName file name
     * @param content  file content
     * @return Path
     * @throws IOException IOException
     */
    public static Path writeStringToFile(String fileName, String content) throws IOException {
        Path filePath = Paths.get(fileName);
        Files.write(filePath, content.getBytes());
        return filePath;
    }

    /**
     * Read file under class path as string.
     *
     * @param fileName file name
     * @return file content
     * @throws IOException IOException
     */
    public static String readFileAsString(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        return Files.readString(filePath);
    }

}
