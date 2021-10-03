package GameOfLife.model;

/**
 * Deze klasse functioneert als een cel in Game Of Life.
 * Enkel een boolean met getter en setter.
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class Cell {
    private boolean isAlive;

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    protected void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
