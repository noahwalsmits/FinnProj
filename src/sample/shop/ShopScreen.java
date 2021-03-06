package sample.shop;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.ContentScreen;
import sample.inventory.cosmetics.CosmeticType;
import sample.inventory.cosmetics.UserData;

public class ShopScreen implements ContentScreen {
    private final Pane root;

    public ShopScreen() {
        this.root = new FlowPane();

        HBox hBox = new HBox();
        LootBox[] lootBoxes = LootBox.getLootCrates();
        for (LootBox lootBox : lootBoxes) {
            VBox pane = new VBox();
            pane.setAlignment(Pos.CENTER);
            pane.getStyleClass().add("crate-background");
            pane.setOnMouseClicked(mouseEvent -> {
                UserData userData = new UserData();
                if (userData.changePoints(lootBox.getPrice())) {
                    giveItem(lootBox.open());
                } else {
                    //TODO not enough money
                }
            });

            Label labelName = new Label(lootBox.getName());
            labelName.getStyleClass().add("inventory-label");
            pane.getChildren().add(labelName);

            pane.getChildren().add(new ImageView(lootBox.getImageResource()));

            Label labelPrice = new Label(Integer.toString(lootBox.getPrice()));
            labelPrice.getStyleClass().add("inventory-label");
            pane.getChildren().add(labelPrice);

            hBox.getChildren().add(pane);
        }
        this.root.getChildren().add(hBox);
    }

    private void giveItem(CosmeticType cosmeticType) {
        UserData userData = new UserData();
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public void exit() {

    }
}
