package nz.ac.wgtn.swen225.lc.persistency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nz.ac.wgtn.swen225.lc.domain.*;
import java.util.ArrayList;
import java.util.List;

import static nz.ac.wgtn.swen225.lc.domain.Color.Green;
import static nz.ac.wgtn.swen225.lc.domain.Color.Yellow;

/**
 * Convert GameData to/from txt/json.
 * This class should only be used by persistency module internally.
 */
class GameDataConverter {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static Game gameDataFromJson(String json) throws JsonProcessingException {
        return MAPPER.readValue(json, Game.class);
    }

    static String gameDataToJson(Game game) throws JsonProcessingException {
        return MAPPER.writeValueAsString(game);
    }

    // Read from map design file and build GameData
    static Game gameDataFromTxt(String txt) throws Exception {
        String[] lines = txt.split(System.lineSeparator());
        int width = 0;
        int height = lines.length;
        List<Characters> characters = new ArrayList<>();
        List<Tile> tiles = new ArrayList<>();
        for (int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for (int x = 0, charPos = 0; charPos < line.length(); ) {
                int codePoint = line.codePointAt(charPos);
//                System.out.println("Character: " + new String(Character.toChars(codePoint)) + ", Code point: " + codePoint);
                CoordinateEntity entity = buildCoordinateEntity(codePoint, x, y);
                if (entity == null) {
                    break;
                }
                if (entity instanceof Characters) {
                    characters.add((Characters) entity);
                } else {
                    tiles.add((Tile) entity);
                }
                width = Math.max(width, x + 1);
                x += 1;
                charPos += Character.charCount(codePoint);
            }
        }
        return new Game(width, height, MapConstant.DEFAULT_TIME_SECONDS, characters, List.of(), tiles);
    }

    static String txtToJson(String txt) throws Exception {
        Game game = gameDataFromTxt(txt);
        return gameDataToJson(game);
    }

    private static CoordinateEntity buildCoordinateEntity(int codePoint, int x, int y) throws Exception {
        Color color = null;
        if (codePoint == 128273 || codePoint == 128274) {
            color = Green;
        }
        if (codePoint == 128477 || codePoint == 128271) {
            color = Yellow;
        }
        Class<? extends CoordinateEntity> clazz = MapConstant.CHAR_MAP.get(codePoint);
        if (clazz == null) {
            return null;
        }
        CoordinateEntity entity = clazz.getDeclaredConstructor().newInstance();
        entity.setX(x);
        entity.setY(y);
        if (color != null && entity instanceof Tile) {
            ((Tile) entity).setColor(color);
        }
        return entity;
    }

}


