package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.game.FlappyGame;

public class Main extends Application {
    private HBox baseBox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.baseBox = new HBox();
        this.baseBox.getChildren().add(this.createSideBar());
        this.baseBox.getChildren().add(new ImageView("images/wall.jpg"));

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
    }

    private VBox createSideBar() {
        VBox vBox = new VBox();
        { //game button
            Button button = new Button("Game");
            button.setOnAction(actionEvent -> {
                this.setContent(new FlappyGame().getRoot());
            });
            button.setMaxWidth(Double.MAX_VALUE);
            vBox.getChildren().add(button);
        }
        { //loot button
            Button button = new Button("Loot");
            button.setOnAction(actionEvent -> {
                this.setContent(new ImageView("images/wall.jpg"));//
            });
            button.setMaxWidth(Double.MAX_VALUE);
            vBox.getChildren().add(button);
        }
        { //inventory button
            Button button = new Button("Inventory");
            button.setOnAction(actionEvent -> {
                this.setContent(new Pane());//
            });
            button.setMaxWidth(Double.MAX_VALUE);
            vBox.getChildren().add(button);
        }
        { //social button
            Button button = new Button("Social");
            button.setOnAction(actionEvent -> {
                this.setContent(new Pane());//
            });
            button.setMaxWidth(Double.MAX_VALUE);
            vBox.getChildren().add(button);
        }
        vBox.setMinWidth(Region.USE_PREF_SIZE);
        vBox.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        return vBox;
    }
}
