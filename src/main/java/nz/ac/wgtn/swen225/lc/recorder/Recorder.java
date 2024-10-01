package nz.ac.wgtn.swen225.lc.recorder;

// The recorder module adds functionality to record game play, and stores the recorded games in a file (in json format).
// It also adds the dual functionality to load a recorded game, and to replay it.

//Importing java library
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import java.io.*;
import javax.swing.JPanel;

//Importing provided library
import nz.ac.wgtn.swen225.lc.app.*;
import nz.ac.wgtn.swen225.lc.domain.Game;
import nz.ac.wgtn.swen225.lc.persistency.*;

public final class Recorder{
    private List<String> replay_data;
    private final PlaybackController buttonController;

    public Recorder(){
        replay_data = new ArrayList<>();

        buttonController = new PlaybackController(this);
        buttonController.printAction((e)->printToConsole());
    }

    public void printToConsole(){
        IntStream.range(0, replay_data.size()).boxed()
        .forEach(i -> System.out.println("\nSnapshot: "+i+"\t"+replay_data.get(i)));
        System.out.println("\nEnd Of Recording");
    }

    public void takeSnapshot(Game game) throws IOException {
//        replay_data.get(Persistency.getLevelJSON());
        Persistency.saveGame(game, "save.json");
    }

    public void saveReplay(String replayName){

    }

    public JPanel getPanel(){
//        return buttonController;
        return null;
    }

    public void replayLevel(String file_name){
        
    }
}