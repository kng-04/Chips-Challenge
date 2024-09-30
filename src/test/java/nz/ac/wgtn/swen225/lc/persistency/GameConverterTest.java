package nz.ac.wgtn.swen225.lc.persistency;


import nz.ac.wgtn.swen225.lc.domain.CoordinateEntity;
import nz.ac.wgtn.swen225.lc.domain.Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static nz.ac.wgtn.swen225.lc.persistency.GameDataConverter.*;
import static nz.ac.wgtn.swen225.lc.persistency.MapConstant.DEFAULT_TIME_SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link GameDataConverter}
 */
public class GameConverterTest {

    /**
     * Read GameData from predefined json and txt files,
     * the two GameData instances should be equal,
     * and have expected property values.
     */
    @Test
    void testGameDataFromFiles() throws Exception {
        Game gameJson = gameDataFromJson(readResource("converter-test-1.json"));
        Game gameTxt = gameDataFromTxt(readResource("converter-test-1.txt"));
        assertEquals(gameJson, gameTxt);
        assertEquals(4, gameJson.getWidth());
        assertEquals(3, gameJson.getHeight());
        assertEquals(DEFAULT_TIME_SECONDS, gameJson.getSecondsLeft());
        assertEquals(2, gameJson.getCharacters().size());
        assertEquals(0, gameJson.getInventory().size());
        assertEquals(10, gameJson.getTiles().size());

        List<CoordinateEntity> entities = new ArrayList<>();
        entities.addAll(gameJson.getCharacters());
        entities.addAll(gameJson.getTiles());
        // all tiles should be in map range
        entities.forEach(t -> {
            assertTrue(t.getX() >= 0 && t.getX() <= gameJson.getWidth());
            assertTrue(t.getY() >= 0 && t.getY() <= gameJson.getHeight());
        });
    }

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

    /**
     * Test converting txt content to json content.
     */
    @Test
    void testTxtToJson() throws Exception {
        String txt = readResource("converter-test-1.txt");
        String json = txtToJson(txt);
        String expectedJson = formatJson(readResource("converter-test-1.json"));
        assertEquals(expectedJson, json);
    }

    private String readResource(String resourceName) throws IOException, URISyntaxException {
        Path path = Paths.get(Objects.requireNonNull(GameConverterTest.class.getClassLoader().getResource(resourceName)).toURI());
        return Files.readString(path);
    }
}
