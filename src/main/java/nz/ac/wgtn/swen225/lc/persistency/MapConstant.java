package nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.domain.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Constants related to Game map.
 */
public class MapConstant {
    public static final int DEFAULT_TIME_SECONDS = 60;
    public static final String DOMAIN_PKG_PREFIX = "nz.ac.wgtn.swen225.lc.domain.";
    public static final String[] STATIC_TILE_NAMES = new String[]{"ExitTile", "ExitLockTile", "FreeTile", "InfoFieldTile", "KeyTile", "LockedDoorTile", "TreasureTile", "WallTile"};
    public static final Map<Integer, Class<? extends CoordinateEntity>> CHAR_MAP = new HashMap<>();

    static {
        CHAR_MAP.put(129302, Chap.class);//🤖
        CHAR_MAP.put(128123, Enemy.class);//👻
        CHAR_MAP.put(128681, ExitTile.class);//🚩
        CHAR_MAP.put(127538, ExitLockTile.class);//🈲
        CHAR_MAP.put(128307, FreeTile.class);//🔳
        CHAR_MAP.put(128161, InfoFieldTile.class);//💡
        CHAR_MAP.put(128273, KeyTile.class);//Key Green 🔑
        CHAR_MAP.put(128274, LockedDoorTile.class);//LockedDoor Green 🔒
        CHAR_MAP.put(128477, KeyTile.class);//Key Yellow 🗝️
        CHAR_MAP.put(128271, LockedDoorTile.class);//LockedDoor Yellow 🔏
        CHAR_MAP.put(128142, TreasureTile.class);//💎
        CHAR_MAP.put(129521, WallTile.class);//🧱
    }
}
