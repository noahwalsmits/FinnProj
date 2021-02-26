package sample.inventory;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import sample.ContentScreen;

public class InventoryScreen implements ContentScreen {
    private final Pane root;

    public InventoryScreen() {
        this.root = new FlowPane();
        this.root.getChildren().add(new Button("View Item"));
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public void exit() {

    }
}
