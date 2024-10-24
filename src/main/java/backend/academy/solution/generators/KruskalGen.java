package backend.academy.solution.generators;

import backend.academy.solution.maze.Cell;
import backend.academy.solution.maze.Edge;
import backend.academy.solution.maze.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalGen implements Generator {
    @Override
    public Maze generate(int height, int width) {
        final int[][] ident = new int[height][width];
        final Cell[][] grid = new Cell[height][width];
        final List<Edge> edges = new ArrayList<>();

        unit(height, width, grid, ident, edges);

        final List<Edge> finalEdges = new ArrayList<>();
        for (final Edge edge : edges) {
            final Cell c1 = edge.first();
            final Cell c2 = edge.second();

            final int firstIdent = ident[c1.row()][c1.col()];
            final int secondIdent = ident[c2.row()][c2.col()];

            if (firstIdent != secondIdent) {
                finalEdges.add(edge);

                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (ident[i][j] == secondIdent) {
                            ident[i][j] = firstIdent;
                        }
                    }
                }
                ident[c1.row()][c1.col()] = ident[c2.row()][c2.col()];
            }
        }

        return new Maze(height, width, finalEdges);
    }

    private static void unit(
        final int h,
        final int w,
        final Cell[][] grid,
        final int[][] ident,
        final List<Edge> edges
    ) {
        int counter = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                grid[i][j] = new Cell(i, j);
                ident[i][j] = counter++;
            }
        }

        for (int i = 1; i < h; i++) {
            for (int j = 0; j < w; j++) {
                edges.add(new Edge(grid[i - 1][j], grid[i][j]));
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 1; j < w; j++) {
                edges.add(new Edge(grid[i][j - 1], grid[i][j]));
            }
        }
        Collections.shuffle(edges);
    }
}
