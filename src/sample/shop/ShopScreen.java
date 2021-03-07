package sample.shop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import sample.ContentScreen;
import sample.inventory.cosmetics.CosmeticType;
import sample.inventory.cosmetics.UserData;

public class ShopScreen implements ContentScreen {
    private final Pane root;
    private final Label pointCount;
    private final Label itemStatus;
    private AudioClip crateSound;

    public ShopScreen() {
        this.root = new VBox();
        this.pointCount = new Label();
        this.pointCount.getStyleClass().add("inventory-label");
        this.root.getChildren().add(this.pointCount);
        this.updatePointCount();
        this.itemStatus = new Label();
        this.itemStatus.getStyleClass().add("inventory-label");
        this.crateSound = new AudioClip(getClass().getResource("/sounds/Mvm_bought_upgrade.wav").toString());

        HBox hBox = new HBox();
        hBox.getStyleClass().add("crate-background");
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(15.);

        LootBox[] lootBoxes = LootBox.getLootCrates();
        for (LootBox lootBox : lootBoxes) {
            VBox pane = new VBox();
            pane.setAlignment(Pos.CENTER);
            pane.getStyleClass().add("crate-background");
            pane.setOnMouseClicked(mouseEvent -> {
                UserData userData = new UserData();
                if (userData.changePoints(-lootBox.getPrice())) {
                    updatePointCount();
                    CosmeticType item = lootBox.open();
                    userData.addItem(item);
                    itemStatus.setText("You opened a " + lootBox.getName() + " and got " + item.getName());
                    crateSound.play();
                } else {
                    itemStatus.setText("You do not have enough points to open this crate");
                }
            });

            Label labelName = new Label(lootBox.getName());
            labelName.getStyleClass().add("inventory-label");
            pane.getChildren().add(labelName);

            ImageView imageView = new ImageView(lootBox.getImageResource());
            imageView.setFitWidth(200.);
            imageView.setFitHeight(200.);
            pane.getChildren().add(imageView);

            Label labelPrice = new Label(Integer.toString(lootBox.getPrice()));
            labelPrice.getStyleClass().add("inventory-label");
            pane.getChildren().add(labelPrice);

            hBox.getChildren().add(pane);
        }
        this.root.getChildren().add(hBox);
        this.root.getChildren().add(this.itemStatus);
    }

    private void updatePointCount() {
        UserData userData = new UserData();
        this.pointCount.setText("Points: " + userData.getPoints());
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public void exit() {

    }
}
