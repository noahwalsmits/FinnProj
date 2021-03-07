package sample.social;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import sample.ContentScreen;

public class SocialScreen implements ContentScreen {
    private final Pane root;

    public SocialScreen() {
        this.root = new FlowPane();
        this.root.getChildren().add(new Button(getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6)+ "/../userdata.json"));
        //TODO placeholder screen
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public void exit() {

    }
}
