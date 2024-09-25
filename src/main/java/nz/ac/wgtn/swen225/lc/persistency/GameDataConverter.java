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

}


