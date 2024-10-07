package nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.domain.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static nz.ac.wgtn.swen225.lc.persistency.FileUtil.readImage;
import static nz.ac.wgtn.swen225.lc.persistency.FileUtil.listFiles;
import static nz.ac.wgtn.swen225.lc.persistency.GameDataConverter.gameDataFromJson;
import static nz.ac.wgtn.swen225.lc.persistency.GameDataConverter.gameDataToJson;

/**
 * Responsible for loading and saving GameData.
 * This class is exposed for other modules to use.
 */
public class Persistency {

    public static final Map<String, BufferedImage> IMAGE_MAP = new HashMap<>();

    // Load GameData from json file, could be level file or saved game
    // also load images
    public static Game loadGame(String jsonFileName) throws IOException {
        loadImages();
        return gameDataFromJson(FileUtil.readFileAsString(jsonFileName));
    }

    // Save GameData to json file.
    public static void saveGame(Game game, String jsonFileName) throws IOException {
        FileUtil.writeStringToFile(jsonFileName, gameDataToJson(game));
    }

    public static Map<String, BufferedImage> loadImages() throws IOException {
        if (!IMAGE_MAP.isEmpty()) {
            return IMAGE_MAP;
        }
        File[] files = listFiles("images");
        for (File file : files) {
            BufferedImage image = readImage(file);
            IMAGE_MAP.put(file.getName().substring(0, file.getName().lastIndexOf(".")), image);
        }
        return IMAGE_MAP;
    }

}