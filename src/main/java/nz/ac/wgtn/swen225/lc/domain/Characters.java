package nz.ac.wgtn.swen225.lc.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(Chap.class),
        @JsonSubTypes.Type(BlindMan.class)
})
public abstract class Characters extends CoordinateEntity{

    /*
    abstract method move used to move both chap and characters within the game
     */
    public abstract void move(int dx, int dy, Game game);
}
