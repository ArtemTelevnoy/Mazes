package backend.academy.solution.generators;

import backend.academy.solution.maze.Coordinate;
import backend.academy.solution.maze.Maze;
import backend.academy.solution.maze.Type;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RandGen implements Generator {
    private final static SecureRandom RANDOM = new SecureRandom();
    private final Generator generator;

    private static void deleteRand(final Maze maze, final List<Coordinate> walls) {
        maze.setEl(walls.get(RANDOM.nextInt(walls.size())), Type.PASSAGE);
    }

    @Override
    public Maze generate(int height, int width) {
        final Maze maze = generator.generate(height, width);

        final Type[][] cells = maze.cellTypes();
        final List<Coordinate> walls = new ArrayList<>();
        for (int i = 0; i < 2 * height - 1; i++) {
            for (int j = 0; j < 2 * width - 1; j++) {
                if (cells[i][j] == Type.WALL) {
                    walls.add(new Coordinate(i, j));
                }
            }
        }

        for (int i = 0; i < Math.min(height, width); i++) {
            deleteRand(maze, walls);
        }

        return maze;
    }
}
