package nz.ac.wgtn.swen225.lc.recorder;

import javax.swing.JPanel;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.event.ActionListener;

public class PlaybackController {
    private final Recorder recorder;
    public PlaybackController(Recorder r){
        this.recorder = r;
    }    

    JButton print = new JButton("Print Recording");
    ActionListener printAction = (e) ->{};

    public void printAction(ActionListener new_Action){
        if(print.getActionListeners().length > 0) print.removeActionListener(this.printAction);
        print.addActionListener(new_Action);
        printAction = new_Action;
    }

    public void linkAction(){

    }
}
