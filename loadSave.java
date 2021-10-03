package GameOfLife;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

/**
 * Deze klasse heeft een aantal statische methodes om een configuratie te laden en op te slagen,
 * hij bevat ook alerts die opgeroepen kunnen worden als er iets mis loopt.
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class loadSave {
    public final static Alert loadErr = new Alert(Alert.AlertType.ERROR,"Kon configuratie niet laden!");
    public final static Alert saveErr = new Alert(Alert.AlertType.ERROR,"Kon configuratie niet opslagen!");

    public static void Save(Stage stage, boolean[][] universe) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Selecteer je configuratie");
        chooser.setInitialFileName("MyGameofLife.gol");
        File selectedSaveFile = chooser.showSaveDialog(stage);

        SaveGOL(selectedSaveFile, universe);
    }

    public static boolean[][] Load(Stage stage) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Slaag je configuratie op!");
        File selectedLoadFile = chooser.showOpenDialog(stage);
        return LoadGOL(selectedLoadFile);
    }

    public static boolean[][] Load(File saveLoc) throws IOException{
        return LoadGOL(saveLoc);
    }

    /**
     * Deze methode leest eerst de size van de boolean[][], en leest daarna 1 voor 1 alle booleans in.
     * @param loadLoc dit is het bestand dat gelezen wordt
     * @return dit is de boolean[][] array die uit het bestand gelezen wordt
     * @throws IOException deze exception wordt gegooid als er iets misgaat
     */
    private static boolean[][] LoadGOL(File loadLoc) throws IOException {
        DataInputStream golStream = new DataInputStream(new BufferedInputStream(new FileInputStream(loadLoc)));

        int size = golStream.readInt();

        boolean[][] readBooleans = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                readBooleans[i][j] = golStream.readBoolean();
            }
        }

        golStream.close();

        return readBooleans;
    }

    /**
     * Deze methode laadt eerst de size van de boolean[][], en laadt daarna 1 voor 1 alle booleans op.
     * @param saveLoc dit is het bestand waarnaartoe geschreven wordt
     * @param universe dit is de boolean[][] array die opgeslagen wordt
     * @throws IOException deze exception wordt gegooid als er iets misgaat
     */
    private static void SaveGOL(File saveLoc, boolean[][] universe) throws IOException {
        DataOutputStream golStream = new DataOutputStream( new BufferedOutputStream( new FileOutputStream(saveLoc)));

        //write the size
        golStream.writeInt(universe.length);

        //write the booleans
        for (boolean[] booleans : universe) {
            for (boolean cell : booleans) {
                golStream.writeBoolean(cell);
            }
        }

        golStream.close();
    }
}
