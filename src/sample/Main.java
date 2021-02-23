package sample;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.game.FlappyGame;
import sample.inventory.InventoryScreen;
import sample.shop.ShopScreen;
import sample.social.SocialScreen;

public class Main extends Application {
    private HBox baseBox;
    private ContentScreen currentScreen;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.baseBox = new HBox();
        this.baseBox.setMinWidth(Region.USE_PREF_SIZE);
        this.baseBox.setMaxWidth(Region.USE_PREF_SIZE);
        this.baseBox.getChildren().add(this.getSideBar());
        this.baseBox.getChildren().add(new Pane());

        primaryStage.setTitle("Game");
        Scene scene = new Scene(this.baseBox);
        primaryStage.setScene(scene);
        primaryStage.setWidth(300.0);
        primaryStage.setHeight(300.0);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets the content of the screen and leaves the sidebar untouched.
     *
     * @param content The content that should be displayed.
     */
    private void setContent(ContentScreen content) {
        if (this.currentScreen != null) {
            this.currentScreen.exit();
            this.currentScreen = content;
        }
        this.baseBox.getChildren().remove(1);
        this.baseBox.getChildren().add(content.getRoot());
        this.baseBox.setHgrow(content.getRoot(), Priority.ALWAYS);
    }

    private VBox getSideBar() {
        VBox vBox = new VBox();
        { final Region spacer = new Region(); VBox.setVgrow(spacer, Priority.ALWAYS); vBox.getChildren().add(spacer); }
        { //game button
            Button button = new Button("Game");
            button.setOnAction(actionEvent -> {
                this.setContent(new FlappyGame());
            });
            button.setMaxWidth(Double.MAX_VALUE);
            button.widthProperty().addListener(observable -> {
                button.setPrefHeight(button.getWidth());
            });
            vBox.getChildren().add(button);
        }
        { final Region spacer = new Region(); VBox.setVgrow(spacer, Priority.ALWAYS); vBox.getChildren().add(spacer); }
        { //loot button
            Button button = new Button("Shop");
            button.setOnAction(actionEvent -> {
                this.setContent(new ShopScreen());
            });
            button.setMaxWidth(Double.MAX_VALUE);
            button.widthProperty().addListener(observable -> {
                button.setPrefHeight(button.getWidth());
            });
            vBox.getChildren().add(button);
        }
        { final Region spacer = new Region(); VBox.setVgrow(spacer, Priority.ALWAYS); vBox.getChildren().add(spacer); }
        { //inventory button
            Button button = new Button("Inventory");
            button.setOnAction(actionEvent -> {
                this.setContent(new InventoryScreen());
            });
            button.setMaxWidth(Double.MAX_VALUE);
            button.widthProperty().addListener(observable -> {
                button.setPrefHeight(button.getWidth());
            });
            vBox.getChildren().add(button);
        }
        { final Region spacer = new Region(); VBox.setVgrow(spacer, Priority.ALWAYS); vBox.getChildren().add(spacer); }
        { //social button
            Button button = new Button("Social");
            button.setOnAction(actionEvent -> {
                this.setContent(new SocialScreen());
            });
            button.setMaxWidth(Double.MAX_VALUE);
            button.widthProperty().addListener(observable -> {
                button.setPrefHeight(button.getWidth());
            });
            vBox.getChildren().add(button);
        }
        { final Region spacer = new Region(); VBox.setVgrow(spacer, Priority.ALWAYS); vBox.getChildren().add(spacer); }
        vBox.setMinWidth(Region.USE_PREF_SIZE);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        return vBox;
    }
}
