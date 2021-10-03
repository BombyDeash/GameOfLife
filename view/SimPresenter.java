package GameOfLife.view;

import GameOfLife.model.GOLModel;
import GameOfLife.loadSave;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

/**
 * Deze klasse behandeld alle interacties tussen de gebruiker en SimScreen
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class SimPresenter {
    private GOLModel model;
    private SimScreen view;
    private Stage stage;
    private boolean running;

    public SimPresenter(GOLModel model, SimScreen view, Stage stage) {
        this.model = model;
        this.view = view;
        this.stage = stage;

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        /**
         * Deze Timeline wordt enkel gebruikt door de eventhandler van view.getBtnPlayPause
         */
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getBtnNext().fire();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        view.getBtnSave().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    loadSave.Save(stage, model.getUniverse());
                } catch (IOException a) {
                    loadSave.saveErr.showAndWait();
                }
                view.getLabel().setText("Je configuratie is opgeslagen!");
            }
        });

        view.getBtnReturn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ConfScreen confScreen = new ConfScreen();
                model = new GOLModel(model);
                ConfPresenter confPresenter = new ConfPresenter(model,confScreen,stage);
                stage.setScene(new Scene(confScreen));
                stage.setHeight(550);
                stage.setMaxHeight(550);
                stage.setMaxWidth(450);
                stage.setWidth(450);
                stage.setResizable(false);
                confScreen.getStylesheets().add("GameOfLife/css/myStyle.css");
                timeline.stop();
            }
        });

        view.getBtnPrevious().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.setI(model.getI()-1);
                try {
                    updateView();
                } catch (NullPointerException n) {
                    model.setI(0);
                    updateView();
                    view.getLabel().setText("Je kan geen universe onder 0 laten zien!");
                }
            }
        });

        view.getBtnPlayPause().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                running = !running;
                view.getBtnPlayPause().setGraphic(new ImageView(new Image(running?"pauseIcon.png":"playIcon.png",40,40,false,false)));
                if (running) {
                    timeline.play();
                    view.getLabel().setText("Het spel is gestart!");
                } else {
                    timeline.stop();
                    view.getLabel().setText("Het spel is gestopt!");
                }
            }
        });

        view.getBtnNext().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.setI(model.getI() + 1);
//                if(model.checkIfDead()) {
//                    view.getTxtTop().setText("Helaas is de evolutie tot stilstand gekomen op iteratie " + model.getI());
//                }
                try {
                    updateView();
                } catch (NullPointerException n) {
                    model.addNext();
                    updateView();
                }

            }
        });
    }

    /**
     * Deze methode tekent het model op het canvas, op basis van de show() methode in het model
     */
    private void updateView() {
        GraphicsContext gc = view.getUniverse().getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,400,400);
        gc.setFill(Color.WHITE);
        gc.fillRect(2,2,396,396);

        //teken het raster
        gc.setFill(Color.BLACK);
        for (int i = 1; i < model.getSize(); i++) {
            gc.fillRect(i*(400/model.getSize()),0,2,400);
            gc.fillRect(0,i*(400/model.getSize()),400,2);
        }

        //bvul het raster
        for (int i = 0; i < model.getSize(); i++) {
            for (int j = 0; j < model.getSize(); j++) {
                if(model.show(model.getI())[j][i]) gc.fillRect(j*(400/model.getSize()),i*(400/model.getSize()),400/model.getSize(),400/model.getSize());
            }
        }

        view.getLabel().setText("Momenteel ziet u iteratie " + model.getI());
    }
}
