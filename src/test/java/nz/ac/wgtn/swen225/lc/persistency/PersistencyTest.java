package nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.domain.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link Persistency}
 */
public class PersistencyTest {

    @Test
    void testGameDataToAndFromJson() throws Exception {
        Game game = GameDataGenerator.genGameData();
        String json = GameDataConverter.gameDataToJson(game);
        assertEquals(game, GameDataConverter.gameDataFromJson(json));
    }

    @Test
    void testSaveAndLoadGameData() throws Exception {
        Game game = GameDataGenerator.genGameData();
        String filePath = FileUtil.getTmpPath("swen225_lc.json").toString();
        Persistency.saveGame(game, filePath);
        assertEquals(game, Persistency.loadGame(filePath));
    }

}
