package backend.academy.solution.renderers;

import backend.academy.solution.maze.Coordinate;
import backend.academy.solution.maze.Maze;
import backend.academy.solution.maze.Type;
import java.util.List;

public class SimpleRenderer implements Renderer {
    private static final String START_SYMBOL = "🟩";
    private static final String WAY_SYMBOL = "🟨";
    private static final String PASSAGE_SYMBOL = "⬛️";
    private static final String WALL_SYMBOL = "⬜️";
    private static final String END_SYMBOL = "🟥";
    private static final String BAD_SYMBOL = "\uD83D\uDC89";
    private static final String COIN_SYMBOL = "\uD83D\uDC8A";

    @Override
    public String render(Maze maze) {
        return makeString(maze);
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        maze.setEl(path.getFirst(), Type.START);

        for (int i = 1; i < path.size() - 1; i++) {
            maze.setEl(path.get(i), Type.WAY);
        }

        maze.setEl(path.getLast(), Type.END);
        return makeString(maze);
    }

    private static String makeString(final Maze maze) {
        final Type[][] grid = maze.cellTypes();
        final StringBuilder sb = new StringBuilder();
        sb.append(WALL_SYMBOL.repeat(maze.width() * 2 + 1)).append(System.lineSeparator());

        for (int i = 0; i < maze.height() * 2 - 1; i++) {
            sb.append(WALL_SYMBOL);
            for (int j = 0; j < maze.width() * 2 - 1; j++) {
                sb.append(switch (grid[i][j]) {
                    case START -> START_SYMBOL;
                    case WAY -> WAY_SYMBOL;
                    case PASSAGE -> PASSAGE_SYMBOL;
                    case WALL -> WALL_SYMBOL;
                    case END -> END_SYMBOL;
                    case BAD -> BAD_SYMBOL;
                    case COIN -> COIN_SYMBOL;
                });
            }

            sb.append(WALL_SYMBOL).append(System.lineSeparator());
        }
        sb.append(WALL_SYMBOL.repeat(maze.width() * 2 + 1)).append(System.lineSeparator());

        return sb.toString();
    }
}
