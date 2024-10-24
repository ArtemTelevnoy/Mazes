package backend.academy.solution.solvers;

import backend.academy.solution.maze.Cell;
import backend.academy.solution.maze.Coordinate;
import backend.academy.solution.maze.Maze;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;

public abstract class AbsAlgo implements Solver {
    @Getter(AccessLevel.PROTECTED) private Coordinate end;
    @Getter(AccessLevel.PROTECTED) private List<Coordinate> way;
    protected boolean[][] used;
    protected Cell[][] cellMaze;

    protected void fillData(final Maze maze, final Coordinate end) {
        this.end = end;
        used = new boolean[maze.height()][maze.width()];
        way = new ArrayList<>(maze.height() * maze.width());
        cellMaze = maze.getCellMaze();

        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                used[i][j] = false;
            }
        }
    }

    protected abstract boolean algo(Cell now);

    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        fillData(maze, end);
        algo(cellMaze[start.row()][start.col()]);
        return way;
    }
}
