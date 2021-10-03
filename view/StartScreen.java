package GameOfLife.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * Deze klasse wordt getoond aan de gebruiker, het is de start-pagina van het spel.
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class StartScreen extends BorderPane {
    private Label welkom;
    private Background background;
    private Button buttonStartGame;
    private Button buttonLoadGame;
    private Button buttonHowToPlay;
    private Button buttonAbout;
    private VBox vboxMain;
    private Tooltip tooltip;

    public Button getButtonHowToPlay() {
        return buttonHowToPlay;
    }

    public Button getButtonLoadGame() {
        return buttonLoadGame;
    }

    public Button getButtonStartGame() {
        return buttonStartGame;
    }

    public StartScreen() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        tooltip = new Tooltip("Gemaakt door INF105A studenten: Mattis Swannet & Jordi Fransen.");
        background = new Background(new BackgroundImage(new Image("Achtergrond.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));

        welkom = new Label("Game Of Life");
        welkom.setStyle("-fx-font-size: 25;");

        buttonStartGame = new Button(" New Game  ");
        //buttonStartGame.setFont(Font.font());
        buttonStartGame.setStyle("-fx-background-radius: 22");

        buttonLoadGame = new Button(" Load Game ");
        //buttonLoadGame.setStyle("-fx-background-color: ");
        //buttonLoadGame.setFont(Font.font());
        buttonLoadGame.setStyle("-fx-background-radius: 22");

        buttonAbout = new Button("   About   ");
        //buttonAbout.setStyle("-fx-background-color: ");
        //buttonAbout.setFont(Font.font());
        buttonAbout.setStyle("-fx-background-radius: 22");

        buttonHowToPlay = new Button("How To Play");
        //buttonHowToPlay.setStyle("-fx-background-color: ");
        //buttonHowToPlay.setFont(Font.font());
        buttonHowToPlay.setStyle("-fx-background-radius: 22");

        vboxMain = new VBox();
        vboxMain.getChildren().addAll(buttonStartGame, buttonLoadGame, buttonHowToPlay, buttonAbout);
        buttonAbout.setTooltip(tooltip);
    }

    private void layoutNodes() {
        this.setTop(welkom);

        BorderPane.setAlignment(welkom,Pos.CENTER);
        BorderPane.setMargin(welkom,new Insets(40));
        this.setBackground(background);
        this.setCenter(vboxMain);
        vboxMain.setAlignment(Pos.TOP_CENTER);
        BorderPane.setMargin(vboxMain, new Insets(90));
        BorderPane.setAlignment(vboxMain, Pos.CENTER);
        vboxMain.setSpacing(10);
    }
}
