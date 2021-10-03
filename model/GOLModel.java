package GameOfLife.model;

import java.util.Arrays;

/**
 * Dit is de enige klasse in het model waarmee gecommuniceerd wordt vanuit de rest van het project.
 * Hij bevat dus een aantal cruciale methodes die nodig zijn om te communiceren.
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class GOLModel {
    private int size = 10;
    private boolean[][] universe;
    private Evolution evolution;
    private int i = 0;

    public GOLModel() {
        universe = new boolean[size][size];
        for (boolean[] booleans : universe) {
            Arrays.fill(booleans,false);
        }
        resize();
    }

    public GOLModel(boolean[][] universe) {
        this.universe = universe;
        this.size = universe.length;
        resize();
    }

    //copy-constructor
    public GOLModel(GOLModel golModel) {
        this.size = golModel.size;
        this.universe = golModel.universe;
        this.evolution = golModel.evolution;
        this.i = 0;
    }

    /**
     * Deze methode ververst de Evolution met de nieuwe boolean[][] array.
     * Dit wordt gebruikt als de gebruiker het spelbord van grootte verandert of als het spelbord ververst moet worden.
     */
    public void resize() {
        boolean[][] universeCopy = new boolean[size][size];
        for (boolean[] booleans : universeCopy) {
            Arrays.fill(booleans,false);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    universeCopy[j][i] = universe[j][i];
                } catch(ArrayIndexOutOfBoundsException a) {
                    universeCopy[j][i] = false;
                }
            }
        }

        universe = universeCopy;
        evolution = new Evolution(universe);
    }

    /**
     * Deze methode is nodig om randgevallen te behandelen, als iteration negatief is wordt null teruggegeven.
     *
     * @param iteration index in Evolution
     * @return boolean[][] array van Universe op index iteration
     */
    public boolean[][] show(int iteration) {
        try {
            return evolution.toArray(iteration);
        } catch (IndexOutOfBoundsException a) {
            return null;
        }
    }

    /**
     * Deze methode zet alle booleans in boolean[][] array op false, en roept dan resize op om Evolution te verversen.
     */
    public void clear() {
        for (boolean[] booleans : universe) {
            Arrays.fill(booleans, false);
        }
        resize();
    }

    /**
     * Deze methode is gewoon een wrapper voor de step() methode in Evolution.
     */
    public void addNext() {
        evolution.step();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        resize();
    }

    public boolean[][] getUniverse() {
        return universe;
    }

    public void setUniverse(boolean[][] universe) {
        this.universe = universe;
        resize();
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
