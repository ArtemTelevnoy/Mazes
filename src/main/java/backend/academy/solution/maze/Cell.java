package backend.academy.solution.maze;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * view of cell in graph
 * @author Artem Televnoy
 */
@AllArgsConstructor
@Getter public final class Cell {
    private final int row;
    private final int col;
    private final List<Cell> neighbors = new ArrayList<>();

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
