package nz.ac.wgtn.swen225.lc.persistency;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static nz.ac.wgtn.swen225.lc.persistency.GameDataConverter.txtToJson;


// This is not actually a test case,
// run this to generate json level file from txt map design file when needed.
@Disabled
public class MapGeneratorTest {

    @Test
    void genLevel1() throws Exception {
        String txt = FileUtil.readFileAsString("levels/level2.txt");
        String json = txtToJson(txt);
        FileUtil.writeStringToFile("levels/level2.json", json);
    }

    @Test
    void genLevel11() throws Exception {
        String txt = FileUtil.readFileAsString("src/test/resources/converter-test-1.txt");
        String json = txtToJson(txt);
        FileUtil.writeStringToFile("src/test/resources/converter-test-1.json", json);
    }

}
