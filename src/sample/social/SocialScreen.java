package sample.social;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class SocialScreen {
    private final Pane root;

    public SocialScreen() {
        this.root = new FlowPane();
        this.root.getChildren().add(new Button("Trade"));
    }

    public Pane getRoot() {
        return root;
    }
}
