package nz.ac.wgtn.swen225.lc.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;
import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(ExitTile.class),
        @JsonSubTypes.Type(ExitLockTile.class),
        @JsonSubTypes.Type(FreeTile.class),
        @JsonSubTypes.Type(InfoFieldTile.class),
        @JsonSubTypes.Type(KeyTile.class),
        @JsonSubTypes.Type(LockedDoorTile.class),
        @JsonSubTypes.Type(TreasureTile.class),
        @JsonSubTypes.Type(WallTile.class),
})
public abstract class Tile extends CoordinateEntity {
    protected Color color;

    public Color getColor() {
        return color;
    } //Returns Color of Tile (Key or LockedDoorTile)

    public void setColor(Color color){
        this.color = color;
    } //Set Tile Color
    protected boolean canMoveInto; // Determines if Chap can move onto the tile

    public abstract boolean canMoveInto(); //return whether or not chap can move into this tile

    public void setCanMoveInto(){this.canMoveInto = true;} //Allows chaps movement into this tile

    public void setCannotMoveInto(){this.canMoveInto = false;} //Limit chaps movement into this tile

    public abstract void interact(Chap chap, Game game); // Handle interactions when Chap steps on the tile


    @Override
    public String toString() {
        return new StringJoiner(", ", Tile.class.getSimpleName() + "[", "]")
                .add("x=" + x)
                .add("y=" + y)
                .add("color=" + color)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return x == tile.x && y == tile.y && color == tile.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, color);
    }
}
