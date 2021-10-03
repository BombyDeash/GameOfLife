package GameOfLife.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

/**
 * Deze klasse wordt getoond aan de gebruiker, het is het "How-To-Play" scherm waarop info wordt getoond over het spel.
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class HTPScreen extends BorderPane {
    private Label label;
    private Label label2;
    private Label label3;
    private Button buttonReturn;
    private VBox vbox;
    private HBox hboxMid;
    private Background background;

    public Button getButtonReturn() {
        return buttonReturn;
    }

    public HTPScreen() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        background = new Background(new BackgroundImage(new Image("achtergrond 2.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        label2 = new Label("\n\tThe Game Of Life " +
                "\n\nThe Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970.");
        label3 = new Label("\n\tGame Rules: " +
                "\n\nThe Game Of Life takes place on a two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, " +
                "alive or dead. Every cell interacts with its eight neighbours, which are the cells that horizontally, vertically, or diagonally adjacent.");
        label = new Label("At each step in time (that is: for each generation), the following transitions occur:" +
                "\n- Live cells with fewer than two live neighbours die." +
                "\n- Live cells with two or three live neighbours survives." +
                "\n- Live cells with more than three live neighbours dies." +
                "\n- Dead cells with exactly three live neighbours " +
                "\n  becomes a live cell.");

        buttonReturn = new Button("Return");
        label.setTextFill(Paint.valueOf("#ffff"));
        //label.setFont(Font.font("Verdana",16));
        label.setWrapText(true);

        label2.setTextFill(Paint.valueOf("#ffff"));
        //label2.setFont(Font.font("Verdana",16));
        label2.setWrapText(true);

        label3.setTextFill(Paint.valueOf("#ffff"));
        //label3.setFont(Font.font("Verdana",16));
        label3.setWrapText(true);

        vbox = new VBox();
        hboxMid = new HBox();

        vbox.getChildren().addAll(label2, label3, label, hboxMid);
        hboxMid.getChildren().addAll(buttonReturn);
    }

    private void layoutNodes() {
        BorderPane.setMargin(vbox, new Insets(8));
        this.setBackground(background);
        this.setTop(vbox);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        hboxMid.setAlignment(Pos.CENTER);
    }
}
