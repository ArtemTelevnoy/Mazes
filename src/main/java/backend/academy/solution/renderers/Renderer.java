package backend.academy.solution.renderers;

import backend.academy.solution.maze.Coordinate;
import backend.academy.solution.maze.Maze;
import java.util.List;

/**
 * rendering interface
 * @author Artem Televnoy
 */
public interface Renderer {
    /**
     *  render maze
     * @param maze maze for rendering
     * @return {@link String} view of maze
     */
    String render(Maze maze);

    /**
     *  render maze
     * @param maze maze for rendering
     * @param path list of {@link Coordinate} of way
     * @return {@link String} view of maze and way
     */
    String render(Maze maze, List<Coordinate> path);
}
