package lootget.inventory;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lootget.ContentScreen;
import lootget.inventory.cosmetics.CosmeticItem;
import lootget.inventory.cosmetics.UserData;

import java.util.ArrayList;
import java.util.List;

public class InventoryScreen implements ContentScreen {
    private final VBox root;
    private final StackPane gridDisplay;
    private final GridPane[] itemPanes;
    private int currentItemPaneIndex;
    private final MediaPlayer mediaPlayer;

    private static final int GRID_WIDTH = 6;
    private static final int GRID_HEIGHT = 4;

    public InventoryScreen() {
        this.root = new VBox();
        this.gridDisplay = new StackPane();
        this.itemPanes = this.getGrids();
        this.root.getChildren().add(this.gridDisplay);
        this.root.setVgrow(this.gridDisplay, Priority.ALWAYS);
        this.currentItemPaneIndex = 0;
        this.gridDisplay.getChildren().add(this.itemPanes[0]);
        this.changeGridIndex(-1);
        this.mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/sounds/music/inventory_theme.mp3").toString()));
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        this.mediaPlayer.play();

        HBox navBox = new HBox();
        navBox.setAlignment(Pos.CENTER);
        Button left = new Button("<");
        left.setOnAction(actionEvent -> changeGridIndex(-1));
        left.getStyleClass().add("inventory-label");
        left.getStyleClass().add("inventory-button");
        navBox.getChildren().add(left);

        Button right = new Button(">");
        right.setOnAction(actionEvent -> changeGridIndex(1));
        right.getStyleClass().add("inventory-label");
        right.getStyleClass().add("inventory-button");
        navBox.getChildren().add(right);
        this.root.getChildren().add(navBox);
    }

    private CosmeticItemGrid[] getGrids() {
        List<CosmeticItem> totalItems = new UserData().getItems();
        List<CosmeticItem>[] itemPages = new List[totalItems.size() / (GRID_WIDTH * GRID_HEIGHT) + 1];

        for (int i = 0; i < itemPages.length; i++) {
            itemPages[i] = new ArrayList<>();
        }
        for (int i = 0; i < totalItems.size(); i++) {
            itemPages[i / (GRID_WIDTH * GRID_HEIGHT)].add(totalItems.get(i));
        }
        CosmeticItemGrid[] itemGrids = new CosmeticItemGrid[itemPages.length];
        for (int i = 0; i < itemPages.length; i++) {
            itemGrids[i] = new CosmeticItemGrid(itemPages[i], GRID_WIDTH, GRID_HEIGHT);
        }
        return itemGrids;
    }

    private void changeGridIndex(int change) {
        int newIndex = this.currentItemPaneIndex + change;
        if (newIndex < itemPanes.length && newIndex >= 0) {
            this.gridDisplay.getChildren().remove(this.itemPanes[currentItemPaneIndex]);
            this.gridDisplay.getChildren().add(this.itemPanes[newIndex]);
            this.currentItemPaneIndex = newIndex;
        }
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public void exit() {
        this.mediaPlayer.stop();
    }
}
