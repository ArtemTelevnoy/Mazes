package backend.academy.solution.generators;

import backend.academy.solution.maze.Maze;

/**
 * interface for generating {@link Maze}
 * @author Artem Televnoy
 */
public interface Generator {
    /**
     * generate {@link Maze}
     * @param height height of maze
     * @param width width of maze
     * @return generated {@link Maze}
     */
    Maze generate(int height, int width);
}
