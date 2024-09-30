package nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static nz.ac.wgtn.swen225.lc.persistency.MapConstant.DOMAIN_PKG_PREFIX;
import static nz.ac.wgtn.swen225.lc.persistency.MapConstant.STATIC_TILE_NAMES;

/**
 * Random GameData for testing.
 */
public class GameDataGenerator {

    private static final Random random = new Random();

    public static Game genGameData() throws Exception {
        return new Game(
                random.nextInt(),
                random.nextInt(),
                random.nextInt(),
                genCharacters(),
                genInventory(),
                genTiles()
        );
    }

    private static List<Characters> genCharacters() {
        Chap chap = new Chap();
        chap.setX(random.nextInt());
        chap.setY(random.nextInt());

        Enemy enemy = new Enemy();
        enemy.setX(random.nextInt());
        enemy.setY(random.nextInt());

        return List.of(chap, enemy);
    }

    private static List<Tile> genInventory() {
        KeyTile keyTileGreen = new KeyTile();
        keyTileGreen.setX(random.nextInt());
        keyTileGreen.setY(random.nextInt());
        keyTileGreen.setColor(Color.Green);

        KeyTile keyTileYellow = new KeyTile();
        keyTileYellow.setX(random.nextInt());
        keyTileYellow.setY(random.nextInt());
        keyTileYellow.setColor(Color.Yellow);

        return List.of(keyTileGreen, keyTileYellow);
    }

    private static List<Tile> genTiles() throws Exception {
        List<Tile> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String className = DOMAIN_PKG_PREFIX + randomTileName();
            list.add((Tile) Class.forName(className).getConstructor().newInstance());
        }
        return list;
    }

    private static String randomTileName() {
        return STATIC_TILE_NAMES[random.nextInt(STATIC_TILE_NAMES.length)];
    }
}