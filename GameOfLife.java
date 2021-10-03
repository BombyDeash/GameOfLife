package GameOfLife;

import GameOfLife.model.GOLModel;
import GameOfLife.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Deze klasse is de uitvoerder van het programma, hij initialiseert de stage en zet startScreen op de scene
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class GameOfLife extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StartScreen startScreen = new StartScreen();
        GOLModel model = new GOLModel();
        StartPresenter presenter = new StartPresenter(model, startScreen, primaryStage);

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(startScreen));
        startScreen.getStylesheets().add("GameOfLife/css/myStyle.css");
        primaryStage.setTitle("Game Of Life");
        primaryStage.setMaxWidth(400);
        primaryStage.setWidth(400);
        primaryStage.setMaxHeight(550);
        primaryStage.setHeight(550);
        primaryStage.getIcons().add(new Image("shrek.png"));
        primaryStage.show();
    }
}
