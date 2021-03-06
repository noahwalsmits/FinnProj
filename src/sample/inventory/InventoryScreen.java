package sample.inventory;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.ContentScreen;
import sample.inventory.cosmetics.UserData;

public class InventoryScreen implements ContentScreen {
    private final AnchorPane anchorPane;
    private final GridPane itemPane;

    public InventoryScreen() {
        this.anchorPane = new AnchorPane();
        this.itemPane = new CosmeticItemGrid(new UserData().getItems(), 6, 4);
        AnchorPane.setLeftAnchor(this.itemPane, 0.0);
        AnchorPane.setRightAnchor(this.itemPane, 0.0);
        AnchorPane.setTopAnchor(this.itemPane, 0.0);
        AnchorPane.setBottomAnchor(this.itemPane, 0.0);
        this.anchorPane.getChildren().add(itemPane);
        //TODO multi page inventory
    }

    @Override
    public Pane getRoot() {
        return anchorPane;
    }

    @Override
    public void exit() {

    }
}
