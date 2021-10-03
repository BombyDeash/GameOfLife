package GameOfLife.view;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

/**
 * Deze klasse wordt getoond aan de gebruiker, het is de configuratie-fase van het spel.
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class ConfScreen extends BorderPane {
    private Button buttonStartGame;
    private Button buttonClear;
    private RadioButton buttonGlider;
    private RadioButton buttonBlock;
    private RadioButton buttonBlinker;
    private RadioButton buttonFreePlay;
    private Canvas universe;
    private Button buttonReturn;
    private HBox hboxGridSize;
    private Label labelGridSize;
    private HBox hBoxGameSettings;
    private HBox hBoxBottom;
    private Slider slider;
    private VBox vBoxAll;
    private Background background;

    private Tooltip tooltipSlider;
    private Tooltip tooltipGlider;
    private Tooltip tooltipBlock;
    private Tooltip tooltipBlinker;
    private Tooltip tooltipFreePlay;
    private Tooltip tooltipClear;


    public Button getButtonClear() {
        return buttonClear;
    }

    public Button getButtonStartGame() {
        return buttonStartGame;
    }

    public RadioButton getButtonGlider() {
        return buttonGlider;
    }

    public RadioButton getButtonBlock() {
        return buttonBlock;
    }

    public RadioButton getButtonBlinker() {
        return buttonBlinker;
    }

    public RadioButton getButtonFreePlay() {
        return buttonFreePlay;
    }

    public Slider getSlider() {
        return slider;
    }

    public Button getButtonReturn() {
        return buttonReturn;
    }

    public Canvas getUniverse() {
        return universe;
    }

    public ConfScreen() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        tooltipSlider = new Tooltip("Decrease / Increase gridsize");
        tooltipBlinker = new Tooltip("Makes a Blinker");
        tooltipBlock = new Tooltip("Makes a Block");
        tooltipGlider = new Tooltip("Makes a Glider");
        tooltipFreePlay = new Tooltip("You can Place Cells");
        tooltipClear = new Tooltip("Clears the grid");

        labelGridSize = new Label("Gridsize:");
        labelGridSize.setTextFill(Paint.valueOf("#ffff"));

        slider = new Slider(10, 20, 2);
        slider.setBlockIncrement(2);
        slider.setMajorTickUnit(2);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);

        hboxGridSize = new HBox();
        hboxGridSize.getChildren().addAll(labelGridSize, slider);

        hBoxBottom = new HBox();
        buttonReturn = new Button("Return");
        buttonClear = new Button("Clear");
        buttonStartGame = new Button("Start Game");
        hBoxBottom.getChildren().addAll(buttonReturn, buttonClear, buttonStartGame);

        hBoxGameSettings = new HBox();
        //labelFiguren = new Label("Figures:");
        //labelFiguren.setTextFill(Paint.valueOf("#ffff"));

        buttonGlider = new RadioButton("Glider");
        buttonBlinker = new RadioButton("Blinker");
        buttonBlock = new RadioButton("Block");
        buttonFreePlay = new RadioButton("Free Play");
        hBoxGameSettings.getChildren().addAll( buttonGlider, buttonBlinker, buttonBlock, buttonFreePlay);

        ToggleGroup toggleGroup = new ToggleGroup();
        buttonFreePlay.setToggleGroup(toggleGroup);
        buttonFreePlay.setTextFill(Paint.valueOf("#ffff"));
        buttonBlock.setToggleGroup(toggleGroup);
        buttonBlock.setTextFill(Paint.valueOf("#ffff"));
        buttonBlinker.setToggleGroup(toggleGroup);
        buttonBlinker.setTextFill(Paint.valueOf("#ffff"));
        buttonGlider.setToggleGroup(toggleGroup);
        buttonGlider.setTextFill(Paint.valueOf("#ffff"));
        buttonFreePlay.setSelected(true);

        universe = new Canvas(400, 400);
        GraphicsContext gc = universe.getGraphicsContext2D();

        vBoxAll = new VBox();
        vBoxAll.getChildren().addAll(universe, hboxGridSize, hBoxGameSettings, hBoxBottom);

        background = new Background(new BackgroundImage(new Image("Achtergrond.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        slider.setTooltip(tooltipSlider);
        buttonGlider.setTooltip(tooltipGlider);
        buttonBlinker.setTooltip(tooltipBlinker);
        buttonBlock.setTooltip(tooltipBlock);
        buttonFreePlay.setTooltip(tooltipFreePlay);
        buttonClear.setTooltip(tooltipClear);
        labelGridSize.setStyle("-fx-font-size: 15;");
    }

    private void layoutNodes() {
        this.setBackground(background);
        this.setCenter(vBoxAll);
        vBoxAll.setSpacing(10);
        vBoxAll.setAlignment(Pos.TOP_CENTER);
        hboxGridSize.setSpacing(10);
        hboxGridSize.setAlignment(Pos.CENTER);
        hBoxGameSettings.setSpacing(10);
        hBoxGameSettings.setAlignment(Pos.CENTER);
        hBoxBottom.setAlignment(Pos.CENTER);
        hBoxBottom.setSpacing(10);
    }
}
