package GameOfLife.view;

import GameOfLife.model.GOLModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Deze klasse behandeld alle interacties tussen de gebruiker en HTPScreen
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class HTPPresenter {
    private GOLModel model;
    private HTPScreen view;
    private Stage stage;

    public HTPPresenter(GOLModel model, HTPScreen view, Stage stage) {
        this.model = model;
        this.view = view;
        this.stage = stage;

        AddEventsHandlers();
    }
    private void AddEventsHandlers(){
        view.getButtonReturn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StartScreen startScreen = new StartScreen();
                StartPresenter startPresenter = new StartPresenter(model, startScreen,stage);
                stage.setScene(new Scene(startScreen));
                stage.setHeight(550);
                stage.setWidth(400);
                stage.setMaxWidth(450);
                stage.setMaxHeight(600);
                stage.setResizable(false);
                startScreen.getStylesheets().add("GameOfLife/css/myStyle.css");
            }
        });
    }
}
