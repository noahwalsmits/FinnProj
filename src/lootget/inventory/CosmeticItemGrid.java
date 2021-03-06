package lootget.inventory;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import lootget.inventory.cosmetics.CosmeticItem;
import lootget.inventory.cosmetics.UserData;

import java.util.ArrayList;
import java.util.List;

public class CosmeticItemGrid extends GridPane {
    private List<VBox> itemBoxes;

    public CosmeticItemGrid(List<CosmeticItem> items, int width, int height) {
        super();
        this.getStyleClass().add("game-grid");

        this.itemBoxes = new ArrayList<>();

        //constraints
        for (int i = 0; i < width; i++) { //add column constraints
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / width);
            columnConstraints.setHgrow(Priority.ALWAYS);
            this.getColumnConstraints().add(columnConstraints);
        }
        for (int i = 0; i < height; i++) { //add row constraints
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / height);
            rowConstraints.setVgrow(Priority.ALWAYS);
            this.getRowConstraints().add(rowConstraints);
        }

        //fill cells with empty itemboxes
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                VBox pane = new VBox();
                this.itemBoxes.add(pane);
                pane.getStyleClass().add("game-grid-cell");
                if (x == 0) {
                    pane.getStyleClass().add("first-column");
                }
                if (y == 0) {
                    pane.getStyleClass().add("first-row");
                }
                this.add(pane, x, y);
            }
        }

        for (int i = 0; i < items.size(); i++) {
            VBox vBox = this.itemBoxes.get(i);
            vBox.setAlignment(Pos.CENTER);


            Label name = new Label(items.get(i).getType().getName());
            name.getStyleClass().add("inventory-label");
            vBox.getChildren().add(name);

            Pane imageBox = new StackPane();
            Image img = new Image(items.get(i).getType().getImageResource(), 100, 100, false, true);
            ImageView imageView = new ImageView(img);
            imageView.fitWidthProperty().bind(imageBox.widthProperty());
            imageView.fitHeightProperty().bind(imageBox.heightProperty());
            vBox.setVgrow(imageBox, Priority.ALWAYS);
            imageBox.getChildren().add(imageView);
            vBox.getChildren().add(imageBox);

            Label quantity = new Label(Integer.toString(items.get(i).getQuantity()));
            quantity.getStyleClass().add("inventory-label");
            vBox.getChildren().add(quantity);

            switch (items.get(i).getType().getQuality()) {
                case COMMON:
                    vBox.getStyleClass().add("quality-common");
                    break;
                case UNCOMMON:
                    vBox.getStyleClass().add("quality-uncommon");
                    break;
                case RARE:
                    vBox.getStyleClass().add("quality-rare");
                    break;
                case MYTHICAL:
                    vBox.getStyleClass().add("quality-mythical");
                    break;
            }

            int finalI = i;
            vBox.setOnMouseClicked(mouseEvent -> {
                new UserData().setEquippedItem(items.get(finalI).getType());
            });
        }
    }

}
