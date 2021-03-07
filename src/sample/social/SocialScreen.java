package sample.social;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.ContentScreen;

public class SocialScreen implements ContentScreen {
    private final Pane root;

    public SocialScreen() {
        this.root = new VBox();


        HBox serverBox = new HBox();
        serverBox.getChildren().add(new Button("view servers"));
        serverBox.getChildren().add(new Button("create server"));
        this.root.getChildren().add(serverBox);

        this.root.getChildren().add(new Label("Players found:"));
        this.root.getChildren().add(new Label("The social screen has not yet been implemented. The purpose " +
                "of this was to let players trade items over a LAN connection."));
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public void exit() {

    }
}
