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

public class Main extends Application {
    private HBox baseBox;
    private FlappyGame game;

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
    private void setContent(Node content) {
        this.baseBox.getChildren().remove(1);
        this.baseBox.getChildren().add(content);
        this.baseBox.setHgrow(content, Priority.ALWAYS);
    }

    /**
     * Exits the current game instance, if there is one.
     */
    private void exitGame() {
        if (this.game != null) {
            this.game.exit();
            this.game = null;
        }
    }

    private VBox getSideBar() {
        VBox vBox = new VBox();
        { final Region spacer = new Region(); VBox.setVgrow(spacer, Priority.ALWAYS); vBox.getChildren().add(spacer); }
        { //game button
            Button button = new Button("Game");
            button.setOnAction(actionEvent -> {
                exitGame();
                this.game = new FlappyGame();
                this.setContent(this.game.getRoot());
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
                exitGame();
                this.setContent(this.getShopScreen());
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
                exitGame();
                this.setContent(this.getInventoryScreen());
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
                exitGame();
                this.setContent(this.getTradeScreen());
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

    //screens TODO move to own classes
    private Parent getShopScreen() {
        Pane thirdScreen = new FlowPane();

        thirdScreen.getChildren().add(new Button("Buy normal box"));
        thirdScreen.getChildren().add(new Button("Buy rare box"));
        return thirdScreen;
    }

    private Parent getInventoryScreen() {
        Pane fifthScreen = new FlowPane();
        fifthScreen.getChildren().add(new Button("View Item"));
        return fifthScreen;
    }

    private Parent getTradeScreen() {
        Pane fourthScreen = new FlowPane();
        fourthScreen.getChildren().add(new Button("Trade"));
        return fourthScreen;
    }
}
