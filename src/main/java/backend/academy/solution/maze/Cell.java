package backend.academy.solution.maze;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * view of cell in graph
 * @author Artem Televnoy
 */
@Getter public class Cell {
    private final int row;
    private final int col;
    private final List<Cell> neighbors;

    /**
     * Constructor
     * @param row x coordinate
     * @param col y coordinate
     */
    public Cell(int row, int col) {
        this.col = col;
        this.row = row;
        this.neighbors = new ArrayList<>();
    }

    /**
     * check equals of coordinates
     * @param another {@link Coordinate}
     * @return res of equals
     */
    public boolean isThatCell(final Coordinate another) {
        return col == another.col() && row == another.row();
    }

    /**
     * getting cell coordinate
     * @return {@link Coordinate} of cell
     */
    public Coordinate getViewCord() {
        return new Coordinate(row * 2, col * 2);
    }

    /**
     * adding neighbor
     * @param cell new neighbor {@link Cell}
     */
    public void addEl(final Cell cell) {
        neighbors.add(cell);
    }
}
