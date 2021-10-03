package GameOfLife.view;

import GameOfLife.model.GOLModel;
import GameOfLife.loadSave;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Deze klasse behandeld alle interacties tussen de gebruiker en StartScreen
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class StartPresenter {
    private GOLModel model;
    private StartScreen view;
    private Stage stage;

    public StartPresenter(GOLModel model, StartScreen view, Stage stage) {
        this.model = model;
        this.view = view;
        this.stage = stage;

        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getButtonHowToPlay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HTPScreen htpScreen = new HTPScreen();
                HTPPresenter htpPresenter = new HTPPresenter(model,htpScreen,stage);
                stage.setScene(new Scene(htpScreen));
                stage.setResizable(false);
                stage.setHeight(600);
                stage.setWidth(401);
                htpScreen.getStylesheets().add("GameOfLife/css/myStyle.css");


            }
        });

        view.getButtonStartGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ConfScreen confScreen = new ConfScreen();
                model = new GOLModel();
                ConfPresenter confPresenter = new ConfPresenter(model,confScreen,stage);
                stage.setScene(new Scene(confScreen));
                stage.setHeight(550);
                stage.setMaxHeight(550);
                stage.setMaxWidth(450);
                stage.setWidth(450);
                stage.setResizable(false);
                confScreen.getStylesheets().add("GameOfLife/css/myStyle.css");


            }
        });

        view.getButtonLoadGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    ConfScreen confScreen = new ConfScreen();
                    model = new GOLModel(loadSave.Load(stage));
                    ConfPresenter confPresenter = new ConfPresenter(model, confScreen, stage);
                    stage.setScene(new Scene(confScreen));
                    stage.setHeight(550);
                    stage.setMaxHeight(550);
                    stage.setMaxWidth(450);
                    stage.setWidth(450);
                    stage.setResizable(false);
                    confScreen.getStylesheets().add("GameOfLife/css/myStyle.css");
                } catch(IOException a) {
                    loadSave.loadErr.showAndWait();
                }
            }
        });
    }
}
