package sample.shop;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class ShopScreen {
    private final Pane root;

    public ShopScreen() {
        this.root = new FlowPane();
        this.root.getChildren().add(new Button("Buy normal box"));
        this.root.getChildren().add(new Button("Buy rare box"));
    }

    public Pane getRoot() {
        return root;
    }
}
