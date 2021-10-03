package GameOfLife.view;

import GameOfLife.model.GOLModel;
import GameOfLife.loadSave;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

/**
 * Deze klasse behandeld alle interacties tussen de gebruiker en ConfScreen
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class ConfPresenter {
    private GOLModel model;
    private ConfScreen view;
    private Stage stage;

    public ConfPresenter(GOLModel model, ConfScreen view, Stage stage) {
        this.model = model;
        this.view = view;
        this.stage = stage;

        view.getSlider().setValue(model.getSize());

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getButtonBlinker().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    model.setUniverse(loadSave.Load(new File("resources/Patterns/blinker.gol")));
                } catch (IOException a) {
                    loadSave.loadErr.showAndWait();
                }
                updateView();
            }
        });

        view.getButtonBlock().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    model.setUniverse(loadSave.Load(new File("resources/Patterns/block.gol")));
                } catch (IOException a) {
                    loadSave.loadErr.showAndWait();
                }
                updateView();
            }
        });

        view.getButtonGlider().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    model.setUniverse(loadSave.Load(new File("resources/Patterns/glider.gol")));
                } catch (IOException a) {
                    loadSave.loadErr.showAndWait();
                }
                updateView();
            }
        });

        view.getButtonFreePlay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getButtonClear().fire();
            }
        });

        view.getButtonStartGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SimScreen simScreen = new SimScreen();
                model.resize();
                SimPresenter simPresenter = new SimPresenter(model, simScreen, stage);
                stage.setScene(new Scene(simScreen));
                stage.setWidth(415);
                stage.setHeight(551);
                stage.setMaxHeight(600);
                stage.setMaxWidth(500);
                stage.setResizable(false);
                simScreen.getStylesheets().add("GameOfLife/css/myStyle.css");


            }
        });

        view.getButtonClear().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.clear();
                updateView();
            }
        });

        view.getButtonReturn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StartScreen startScreen = new StartScreen();
                StartPresenter startPresenter = new StartPresenter(model, startScreen, stage);
                stage.setScene(new Scene(startScreen));
                stage.setHeight(550);
                stage.setWidth(400);
                stage.setResizable(false);
                startScreen.getStylesheets().add("GameOfLife/css/myStyle.css");

            }
        });

        view.getSlider().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getSlider().getValue() % view.getSlider().getBlockIncrement() == 0) {
                    model.setSize((int) view.getSlider().getValue());
                }
                updateView();
            }
        });

        /**
         * Deze methode kijkt waar de gebruiker in het canvas klikt, en switcht in model de boolean die overeenkomt met dat coordinaat.
         */
        view.getUniverse().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                model.getUniverse()[(int) mouseEvent.getX() / (400 / model.getSize())][(int) mouseEvent.getY() / (400 / model.getSize())] = !model.getUniverse()[(int) mouseEvent.getX() / (400 / model.getSize())][(int) mouseEvent.getY() / (400 / model.getSize())];

                updateView();
            }
        });
    }

    /**
     * Deze methode tekent het model op het canvas, op basis van de boolean[][] array in het model
     */
    private void updateView() {
        GraphicsContext gc = view.getUniverse().getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 400, 400);
        gc.setFill(Color.WHITE);
        gc.fillRect(2, 2, 396, 396);

        //teken het raster
        gc.setFill(Color.BLACK);
        for (int i = 1; i < model.getSize(); i++) {
            gc.fillRect(i * (400 / model.getSize()), 0, 2, 400);
            gc.fillRect(0, i * (400 / model.getSize()), 400, 2);
        }

        //vul de correcte vakjes
        for (int i = 0; i < model.getSize(); i++) {
            for (int j = 0; j < model.getSize(); j++) {
                if (model.getUniverse()[j][i])
                    gc.fillRect(j * (400 / model.getSize()), i * (400 / model.getSize()), 400 / model.getSize(), 400 / model.getSize());
            }
        }
    }
}
