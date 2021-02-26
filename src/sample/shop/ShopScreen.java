package sample.shop;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import sample.ContentScreen;

public class ShopScreen implements ContentScreen {
    private final Pane root;

    public ShopScreen() {
        this.root = new FlowPane();
        this.root.getChildren().add(new Button("Buy normal box"));
        this.root.getChildren().add(new Button("Buy rare box"));
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public void exit() {

    }
}
