package nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.domain.Game;

import java.io.IOException;

import static nz.ac.wgtn.swen225.lc.persistency.GameDataConverter.gameDataFromJson;
import static nz.ac.wgtn.swen225.lc.persistency.GameDataConverter.gameDataToJson;

/**
 * Responsible for loading and saving GameData.
 * This class is exposed for other modules to use.
 */
public class Persistency {


    // Load GameData from json file, could be level file or saved game
    // also load images
    public static Game loadGame(String jsonFileName) throws IOException {
        return gameDataFromJson(FileUtil.readFileAsString(jsonFileName));
    }

    // Save GameData to json file.
    public static void saveGame(Game game, String jsonFileName) throws IOException {
        FileUtil.writeStringToFile(jsonFileName, gameDataToJson(game));
    }

}
