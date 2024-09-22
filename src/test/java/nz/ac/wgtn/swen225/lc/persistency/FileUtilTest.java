package nz.ac.wgtn.swen225.lc.persistency;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static nz.ac.wgtn.swen225.lc.persistency.FileUtil.getTmpPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link FileUtil}
 */
public class FileUtilTest {

    @Test
    void testWriteAndReadFile() throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String fileName = timestamp + ".txt";
        Path path = getTmpPath(fileName);
        FileUtil.writeStringToFile(path, timestamp);
        String readStr = FileUtil.readFileAsString(path);
        assertEquals(timestamp, readStr);
    }

}
