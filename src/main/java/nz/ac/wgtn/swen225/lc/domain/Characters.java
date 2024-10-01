package nz.ac.wgtn.swen225.lc.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(Chap.class),
        @JsonSubTypes.Type(Enemy.class)
})
public abstract class Characters extends CoordinateEntity{

    public abstract void move(int dx, int dy, Tile[][] maze);
}
