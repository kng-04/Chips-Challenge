package nz.ac.wgtn.swen225.lc.persistency;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
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

    /**
     * Write string to file under system tmp dir.
     *
     * @param fileName file name
     * @param content  file content
     * @return Path
     * @throws IOException IOException
     */
    public static Path writeToTmpFile(String fileName, String content) throws IOException {
        Path path = getTmpPath(fileName);
        Files.write(path, content.getBytes());
        return path;
    }

    /**
     * Read file under system tmp dir as string.
     *
     * @param fileName file name
     * @throws IOException IOException
     */
    public static String readFromTmpFile(String fileName) throws IOException {
        Path path = getTmpPath(fileName);
        return Files.readString(path);
    }


    public static File[] listFiles(String dir) {
        File file = new File(String.valueOf(Paths.get(dir)));
        return file.listFiles();
    }

    public static Image readImage(File file) throws IOException {
        try (FileInputStream input = new FileInputStream(file)) {
            return new Image(input);
        }
    }
}
