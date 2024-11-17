package backend.academy.solution.maze;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 * view of cell in graph
 * @author Artem Televnoy
 */
@Getter public final class Cell {
    private final int row;
    private final int col;
    private final Set<Cell> neighbors = new HashSet<>();
    @Setter private Type type = Type.PASSAGE;

    /**
     * Constructor
     * @param row row of {@link Cell}
     * @param col col of {@link Cell}
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * check equals of coordinates
     * @param another {@link Coordinate}
     * @return res of equals
     */
    public boolean isEqualCord(final Coordinate another) {
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
    public void addNeighbor(final Cell cell) {
        neighbors.add(cell);
    }
}
