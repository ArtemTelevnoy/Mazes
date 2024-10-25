import backend.academy.solution.Main;
import backend.academy.solution.maze.Cell;
import backend.academy.solution.maze.Coordinate;
import backend.academy.solution.maze.Edge;
import backend.academy.solution.maze.Maze;
import backend.academy.solution.renderers.SimpleRenderer;
import backend.academy.solution.solvers.Bfs;
import backend.academy.solution.solvers.Dfs;
import backend.academy.solution.solvers.Solver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MazeTest {
    private static Maze simpleMaze() {
        final int h = 3;
        final int w = 3;
        final Cell[][] grid = new Cell[3][3];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }

        final List<Edge> edges = List.of(
            new Edge(grid[0][0], grid[0][1]),
            new Edge(grid[0][1], grid[1][1]),
            new Edge(grid[1][1], grid[1][2]),
            new Edge(grid[1][2], grid[2][2]),
            new Edge(grid[0][0], grid[1][0]),
            new Edge(grid[1][0], grid[2][0]),
            new Edge(grid[2][0], grid[2][1]),
            new Edge(grid[0][1], grid[0][2])
        );

        return new Maze(3, 3, edges);
    }

    private static void checkSolver(final Solver solver) {
        final Maze maze = simpleMaze();
        Assertions.assertEquals(
            solver.solve(maze, new Coordinate(0, 0), new Coordinate(2, 2)),
            List.of(new Coordinate(0, 0), new Coordinate(0, 2),
                new Coordinate(2, 2), new Coordinate(2, 4),
                new Coordinate(4, 4)));
    }

    @Test
    public void testDfs() {
        checkSolver(new Dfs());
    }

    @Test
    public void testBfs() {
        checkSolver(new Bfs());
    }

    @Test
    public void testBadParams1() {
        Assertions.assertDoesNotThrow(() -> Main.main(new String[] {}));
    }

    @Test
    public void testBadParams2() {
        Assertions.assertDoesNotThrow(() -> Main.main(null));
    }

    @Test
    public void testBadParams3() {
        Assertions.assertDoesNotThrow(() -> Main.main(new String[] {"--invalid flag", null, "rand word"}));
    }

    @Test
    public void testBadParams4() {
        Assertions.assertDoesNotThrow(() -> Main.main(new String[] {"--height=-2", "--width=3", "--start=0,4"}));
    }

    @Disabled
    @Test
    public void testValidView1() {
        final String expected = """
            ⬜️⬜️⬜️⬜️⬜️⬜️⬜️
            ⬜️⬛️⬛️⬛️⬛️⬛️⬜️
            ⬜️⬛️⬜️⬛️⬜️⬜️⬜️
            ⬜️⬛️⬜️⬛️⬛️⬛️⬜️
            ⬜️⬛️⬜️⬜️⬜️⬛️⬜️
            ⬜️⬛️⬛️⬛️⬜️⬛️⬜️
            ⬜️⬜️⬜️⬜️⬜️⬜️⬜️
            """;
        Assertions.assertEquals(expected, new SimpleRenderer().render(simpleMaze()));
    }

    @Disabled
    @Test
    public void testValidView2() {
        Assertions.assertEquals(String.format("⬜⬜⬜⬜⬜⬜⬜%n" +
            "⬜⬛⬛⬛⬛⬛⬜%n" +
            "⬜⬛⬜⬛⬜⬜⬜%n" +
            "⬜⬛⬜⬛⬛⬛⬜%n" +
            "⬜⬛⬜⬜⬜⬛⬜%n" +
            "⬜⬛⬛⬛⬜⬛⬜%n" +
            "⬜⬜⬜⬜⬜⬜⬜%n"), new SimpleRenderer().render(simpleMaze()));
    }
}
