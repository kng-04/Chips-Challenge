package nz.ac.wgtn.swen225.lc.domain;

public record Location (int x, int y) {
    /**
     * Adds the x and y of another location to this one
     * Used with the direction() method in Direction
     */
    public Location add(Location other) {return new Location(this.x()+other.x(), this.y()+other.y());}

    /**
     * @param other point to compare to
     * @return whether the points are equal
     */
    public boolean equals(Location other){
        return this.x() == other.x() && this.y == other.y();
    }

}
