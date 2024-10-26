package backend.academy.solution.maze;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import static java.lang.Math.min;

/**
 * class for imitating maze
 * @author Artem Televnoy
 */
 @SuppressWarnings("all") // Contents of array 'cellTypes' are written to, but never read
 @Getter public final class Maze {
    private final int height;
    private final int width;
    private final Type[][] cellTypes;
    private final Cell[][] cellsMaze;
    private final SecureRandom RANDOM = new SecureRandom();

    /**
     * Constructor
     * @param height height of maze
     * @param width width of maze
     * @param edges {@link List} of {@link Edge} edges of maze
     */
    public Maze(int height, int width, List<Edge> edges) {
        this.height = height;
        this.width = width;

        final int h = height * 2 - 1;
        final int w = width * 2 - 1;
        cellsMaze = new Cell[height][width];

        cellTypes = new Type[h][w];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cellsMaze[i][j] = new Cell(i, j);
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                cellTypes[i][j] = Type.WALL;
            }
        }

        for (int i = 0; i < h; i += 2) {
            for (int j = 0; j < w; j += 2) {
                cellTypes[i][j] = Type.PASSAGE;
            }
        }

        for (final Edge edge : edges) {
            final Cell c1 = edge.first();
            final Cell c2 = edge.second();
            final Coordinate edgeIndex = combCord(makeCord(c1), makeCord(c2));
            cellTypes[edgeIndex.row()][edgeIndex.col()] = Type.PASSAGE;

            cellsMaze[c1.row()][c1.col()].addEl(c2);
            cellsMaze[c2.row()][c2.col()].addEl(c1);
        }
    }

    public void addTypes() {
        final List<Coordinate> cells = new ArrayList<>(height * width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells.add(new Coordinate(i * 2, j * 2));
            }
        }

        for (int i = 0; i < min(height, width); i++) {
            final Coordinate cord = cells.get(RANDOM.nextInt(cells.size()));

            final Type newType = RANDOM.nextInt(2) == 0 ? Type.COIN : Type.BAD;
            cellTypes[cord.row()][cord.col()] = newType;
            cellsMaze[cord.row() / 2][cord.col() / 2].type(newType);
        }
    }

    private static Coordinate makeCord(final Cell cell) {
        return new Coordinate(cell.row() * 2, cell.col() * 2);
    }

    private static Coordinate combCord(final Coordinate first, final Coordinate second) {
        return new Coordinate(first.row() == second.row() ? first.row() : min(first.row(), second.row()) + 1,
            first.col() == second.col() ? first.col() : min(first.col(), second.col()) + 1);
    }

    /**
     * set element on grid
     * @param cord {@link Coordinate} of el
     * @param type {@link Type} of el
     */
    public void setEl(final Coordinate cord, final Type type) {
        cellTypes[cord.row()][cord.col()] = type;
        cellsMaze[cord.row() / 2][cord.col() / 2].type(type);
    }
}
