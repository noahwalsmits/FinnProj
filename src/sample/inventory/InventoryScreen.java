package sample.inventory;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class InventoryScreen {
    private final Pane root;

    public InventoryScreen() {
        this.root = new FlowPane();
        this.root.getChildren().add(new Button("View Item"));
    }

    public Pane getRoot() {
        return root;
    }
}
