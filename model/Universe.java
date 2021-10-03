package GameOfLife.model;

/**
 * Deze klasse bevat het grootste deel van de intelligentie van ons model.
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class Universe {
    private int size;
    private Cell[][] universe;
    private Evolution evolution;

    private void setUniverse(Cell[][] universe) {
        this.universe = universe;
    }

    //copy-constructor
    protected Universe(Universe copyUniverse) {
        size = copyUniverse.size;
        universe = copyUniverse.universe;
        evolution = copyUniverse.evolution;

    }

    protected Universe(Evolution evolution, int size, boolean[][] universe) {
        this.size = size;
        this.universe = new Cell[size][size];
        this.evolution = evolution;

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.universe[j][i] = new Cell(universe[i][j]);
            }

        }
    }

    /**
     * Geeft het aantal levende buren van een cell met de gegeven coordinaten.
     *
     * @param x x-coordinaat
     * @param y y-coordinaat
     * @return aantal levende buren
     */
    private int getNeighbors(int x, int y) {
        int amount = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (checkCell(x + i , y + j)) amount++;
            }
        }
        if (checkCell(x, y)) amount--;

        return amount;
    }

    /**
     * Deze methode is nodig om randgevallen te behandelen, als een index buiten de array ligt, geeft hij false terug.
     * 
     * @param x x-coordinaat
     * @param y y-coordinaat
     * @return levend of dood
     */
    private boolean checkCell(int x, int y) {
        try {
            return universe[x][y].isAlive();
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return false;
        }
    }

    /**
     * Deze methode update de actuele Universe, en voegt de nieuwe universe toe aan de ArrayList in Evolution.
     */
    protected void update() {
        Cell[][] copy = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[j][i] = new Cell(universe[j][i].isAlive());
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                switch (getNeighbors(j, i)) {
                    case 2:
                        break;
                    case 3:
                        copy[j][i].setAlive(true);
                        break;
                    default:
                        copy[j][i].setAlive(false);
                        break;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[j][i] = new Cell(copy[j][i].isAlive());
            }
        }

        Universe copyUniverse = new Universe(this);
        copyUniverse.setUniverse(copy);
        evolution.add(copyUniverse);
    }

    protected boolean[][] toArray() {
        boolean[][] boolUniverse = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolUniverse[j][i] = universe[i][j].isAlive();
            }
        }

        return boolUniverse;
    }
}
