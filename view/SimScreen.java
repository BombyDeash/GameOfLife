package GameOfLife.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * Deze klasse wordt getoond aan de gebruiker, het is de simulatie-fase van het spel.
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class SimScreen extends BorderPane {
    private Button btnPrevious;
    private Button btnPlayPause;
    private Button btnNext;
    private Button btnReturn;
    private Button btnSave;
    private Canvas game;
    private HBox controls;
    private HBox hboxBot;
    private HBox hboxTop;
    private Label label;
    private VBox vBoxAll;
    private Background background;

    public Button getBtnSave() {
        return btnSave;
    }

    public Label getLabel() {
        return label;
    }

    public Button getBtnPrevious() {
        return btnPrevious;
    }

    public Button getBtnPlayPause() {
        return btnPlayPause;
    }

    public Button getBtnNext() {
        return btnNext;
    }

    public Button getBtnReturn() {
        return btnReturn;
    }

    public Canvas getUniverse() {
        return game;
    }

    public SimScreen() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        background = new Background(new BackgroundImage(new Image("Achtergrond.jpg"),BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT));

        //labelTop
        label = new Label();
        label.setStyle("-fx-text-fill: #ffff");

        //buttons
        btnReturn = new Button();
        btnReturn.setGraphic(new ImageView(new Image("returnmenu.png",40,40,false,false)));
        btnReturn.setStyle("-fx-background-color: #000000");

        btnPrevious = new Button();
        btnPrevious.setGraphic(new ImageView(new Image("previousIcon.png",40,40,false,false)));
        btnPrevious.setStyle("-fx-background-color: #000000");

        btnPlayPause = new Button();
        btnPlayPause.setGraphic(new ImageView(new Image("playIcon.png",40,40,false,false)));
        btnPlayPause.setStyle("-fx-background-color: #000000");

        btnNext = new Button();
        btnNext.setGraphic(new ImageView(new Image("nextIcon.png",40,40,false,false)));
        btnNext.setStyle("-fx-background-color: #000000");

        btnSave = new Button();
        btnSave.setGraphic(new ImageView(new Image("save.png",40,40,false,false)));
        btnSave.setStyle("-fx-background-color: #000000");

        //Hboxes
        controls = new HBox();
        hboxBot = new HBox();
        hboxTop = new HBox();

        //canvas
        game = new Canvas(400,400);

        //HboxBot
        controls.getChildren().addAll(btnReturn,btnPrevious,btnPlayPause,btnNext,btnSave);

        //HboxTop
        hboxTop.getChildren().add(label);

        //VboxAll
        vBoxAll = new VBox();
        vBoxAll.getChildren().addAll(hboxTop,game,hboxBot);
    }

    private void layoutNodes() {
        this.setBackground(background);

        //gridPane
        this.setCenter(game);

        //HboxBot
        this.setBottom(controls);
        this.controls.setSpacing(10);
        BorderPane.setAlignment(controls, Pos.CENTER);
        BorderPane.setMargin(controls, new Insets(10));
        controls.setAlignment(Pos.CENTER);

        //HboxTop
        this.setTop(hboxTop);
        BorderPane.setAlignment(hboxTop, Pos.CENTER);
        BorderPane.setMargin(hboxTop, new Insets(5));
        this.hboxTop.setSpacing(10);

        //txtTop
        label.setMaxHeight(40);
        label.setMaxWidth(375);
    }
}