package backend.academy.solution.generators;

import backend.academy.solution.maze.Cell;
import backend.academy.solution.maze.Edge;
import backend.academy.solution.maze.Maze;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PrimGen implements Generator {
    private final static SecureRandom SECURE_RANDOM = new SecureRandom();

    @Override
    public Maze generate(int height, int width) {
        final Cell[][] grid = new Cell[height][width];
        final List<Cell> unvisited = new ArrayList<>();
        unit(unvisited, grid, height, width);

        final List<Edge> edges = new ArrayList<>(height * width);
        final Cell startCell = grid[SECURE_RANDOM.nextInt(height)][SECURE_RANDOM.nextInt(width)];

        for (final Cell cell : startCell.neighbors()) {
            edges.add(new Edge(startCell, cell));
        }

        unvisited.remove(startCell);

        final List<Edge> graphEdges = new ArrayList<>();
        do {
            final Edge edge = edges.remove(SECURE_RANDOM.nextInt(edges.size()));
            graphEdges.add(edge);
            unvisited.remove(edge.second());

            for (final Cell cell : edge.second().neighbors()) {
                edges.add(new Edge(edge.second(), cell));
            }

            edges.removeIf(e -> !unvisited.contains(e.second()));
        } while (!edges.isEmpty());

        return new Maze(height, width, graphEdges);
    }

    private static void unit(final List<Cell> unvisited, final Cell[][] grid, final int height, final int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j);
                unvisited.add(grid[i][j]);
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x > 0) {
                    grid[y][x].addNeighbor(grid[y][x - 1]);
                }
                if (x < width - 1) {
                    grid[y][x].addNeighbor(grid[y][x + 1]);
                }
                if (y > 0) {
                    grid[y][x].addNeighbor(grid[y - 1][x]);
                }
                if (y < height - 1) {
                    grid[y][x].addNeighbor(grid[y + 1][x]);
                }
            }
        }
    }
}
