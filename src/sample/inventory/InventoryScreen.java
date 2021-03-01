package sample.inventory;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.ContentScreen;

public class InventoryScreen implements ContentScreen {
    private final AnchorPane anchorPane;
    private final GridPane itemPane;

    public InventoryScreen() {
        this.anchorPane = new AnchorPane();
        this.itemPane = this.createItemGrid(6, 4);
        AnchorPane.setLeftAnchor(this.itemPane, 0.0);
        AnchorPane.setRightAnchor(this.itemPane, 0.0);
        AnchorPane.setTopAnchor(this.itemPane, 0.0);
        AnchorPane.setBottomAnchor(this.itemPane, 0.0);
        this.anchorPane.getChildren().add(itemPane);

        this.itemPane.add(new Label("item"), 0, 0);
        this.itemPane.add(new Label("item"), 0, 1);
        this.itemPane.add(new Label("item"), 1, 1);
    }

    private GridPane createItemGrid(int width, int height) {
        GridPane gridPane = new GridPane();
        //constraints
        for (int i = 0; i < width; i++) { //add column constraints
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0/width);
            columnConstraints.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(columnConstraints);
        }
        for (int i = 0; i < width; i++) { //add row constraints
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0/height);
            rowConstraints.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(rowConstraints);
        }
        //style
        gridPane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        return gridPane;
    }

    private void placeItem(String name, String image, int quantity, int x, int y) {
        VBox itemBox = new VBox();

        this.itemPane.add(itemBox, x, y);
    }

    @Override
    public Pane getRoot() {
        return anchorPane;
    }

    @Override
    public void exit() {

    }
}
