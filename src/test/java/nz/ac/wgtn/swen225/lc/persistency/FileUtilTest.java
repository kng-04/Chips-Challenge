package nz.ac.wgtn.swen225.lc.persistency;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link FileUtil}
 */
public class FileUtilTest {
    private Path path;

    @AfterEach
    void afterEach() throws IOException {
        if (path != null) {
            // delete the file after test
            path.toFile().delete();
        }
    }

    @Test
    void testWriteAndReadClassPathFile() throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String fileName = timestamp + ".txt";
        path = FileUtil.writeStringToFile(fileName, timestamp);
        String readStr = FileUtil.readFileAsString(fileName);
        assertEquals(timestamp, readStr);
    }

}
