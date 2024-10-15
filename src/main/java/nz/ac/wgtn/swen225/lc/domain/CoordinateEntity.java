package nz.ac.wgtn.swen225.lc.domain;

import java.util.Objects;
import java.util.StringJoiner;


/*
Coordinate Entity for both characters and tiles
Contains getter and setter methods applicable to both Tiles and Characters
 */
public class CoordinateEntity {
    protected int x; //x value for this.tile or this.character
    protected int y; //y value for this.tile or this.character
    public int getX() { //get x value of this
        return x;
    }

    public void setX(int x) { //set x value of this
        this.x = x;
    }

    public int getY() { //get y value of this
        return y;
    }

    public void setY(int y) { //set y value of this
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateEntity entity = (CoordinateEntity) o;
        return x == entity.x && y == entity.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Characters.class.getSimpleName() + "[", "]")
                .add("x=" + x)
                .add("y=" + y)
                .toString();
    }
}
