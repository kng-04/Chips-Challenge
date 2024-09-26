package nz.ac.wgtn.swen225.lc.persistency;


import nz.ac.wgtn.swen225.lc.domain.Game;
import org.junit.jupiter.api.Test;

import static nz.ac.wgtn.swen225.lc.persistency.GameDataConverter.*;
import static nz.ac.wgtn.swen225.lc.persistency.MapConstant.DEFAULT_TIME_SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link GameDataConverter}
 */
public class GameConverterTest {

    /**
     * Generate random GameData, covert it to be json,
     * and then covert the json back to GameData,
     * the two GameData instances are expected to be equal
     */
    @Test
    void testGameDataToAndFromJson() throws Exception {
        Game game = GameDataGenerator.genGameData();
        String json = gameDataToJson(game);
        Game gameLoaded = GameDataConverter.gameDataFromJson(json);
        assertEquals(game, gameLoaded);
    }


}
