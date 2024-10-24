package backend.academy.solution.solvers;

import backend.academy.solution.maze.Coordinate;
import backend.academy.solution.maze.Maze;
import java.util.List;

/**
 * interface for finding way in {@link Maze}
 * @author Artem Televnoy
 */
public interface Solver {
    /**
     * finding way from start to end
     * @param maze maze for finding way
     * @param start first point
     * @param end second point
     * @return list of {@link Coordinate} of way from start to end
     */
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
