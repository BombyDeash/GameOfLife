package GameOfLife.model;

import java.util.ArrayList;

/**
 * Deze kasse bevat een ArrayList bestaande uit Universe instanties, het is de "timeline" van Game Of Life.
 *
 * @author Mattis Swannet
 * @author Jordi Fransen
 * @version 1.0
 */
public class Evolution{
    private ArrayList<Universe> evolutions;

    public Evolution(boolean[][] universe) {
        evolutions = new ArrayList<>();
        evolutions.add(new Universe(this, universe.length, universe));
    }

    protected void add(Universe universe) {
        evolutions.add(universe);
    }

    /**
     * Deze methode voegt een geupdate versie van de laatste Universe toe achteraan in de ArrayList evolutions
     */
    protected void step() {
        evolutions.get(evolutions.size() - 1).update();
    }

    protected boolean[][] toArray(int iteration) {
        return evolutions.get(iteration).toArray();
    }
}
