package backend.academy.solution;

import backend.academy.solution.generators.Generator;
import backend.academy.solution.generators.KruskalGen;
import backend.academy.solution.generators.PrimGen;
import backend.academy.solution.maze.Coordinate;
import backend.academy.solution.maze.Maze;
import backend.academy.solution.renderers.Renderer;
import backend.academy.solution.renderers.SimpleRenderer;
import backend.academy.solution.solvers.Bfs;
import backend.academy.solution.solvers.Dfs;
import backend.academy.solution.solvers.Solver;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import static java.lang.Integer.parseInt;

@UtilityClass
public class Main {
    private static final PrintStream OUTPUT = System.out;
    private static final Renderer RENDERER = new SimpleRenderer();

    private static final int DEFAULT_WIDTH = 3;
    private static final int DEFAULT_HEIGHT = 3;
    private static final Coordinate DEFAULT_START_POINT = new Coordinate(0, 0);
    private static final Generator DEFAULT_GENERATOR = new KruskalGen();
    private static final Solver DEFAULT_SOLVER = new Dfs();

    private static final String[] INF = new String[] {
        "--help for this info",
        "--prim | --kruskal for choosing gen algo",
        "--dfs | --bfs for choosing solver algo",
        "--width=val for choosing width where val-some number",
        "--height=val for choosing height where val-some number",
        "--start=x,y for choosing coordinate(x,y) of start point",
        "--end=x,y for choosing coordinate(x,y) of end point"
    };

    private static final Map<String, Generator> GENERATORS = Map.of(
        "--prim", new PrimGen(),
        "--kruskal", new KruskalGen()
    );

    private static final Map<String, Solver> SOLVERS = Map.of(
        "--bfs", new Bfs(),
        "--dfs", new Dfs()
    );

    private static final BiFunction<String, Integer, Integer> PARSE_INT = (str, defVal) -> {
        try {
            final Integer res = Integer.valueOf(str);
            return res > 0 ? res : defVal;
        } catch (NumberFormatException e) {
            return defVal;
        }
    };

    private static final BiFunction<String, Coordinate, Coordinate> PARSE_CORD = (str, defVal) -> {
        final String[] cordVals = str.split(",");

        if (cordVals.length == 2) {
            try {
                final int x = parseInt(cordVals[0]);
                final int y = parseInt(cordVals[1]);

                return new Coordinate(x < 0 ? defVal.row() : x, y < 0 ? defVal.col() : y);
            } catch (NumberFormatException ignored) {
            }
        }

        return defVal;
    };

    private static <T> T getParam(
        final String[] args,
        final T defaultValue,
        final Set<String> validValues,
        final Function<String, T> f
    ) {
        if (args == null || Arrays.stream(args).anyMatch(Objects::isNull)) {
            return defaultValue;
        }
        final String[] values = Arrays.stream(args).filter(validValues::contains).toArray(String[]::new);

        if (values.length > 1) {
            throw new IllegalArgumentException("Too many non exclusive flags: " + Arrays.toString(values));
        } else if (values.length == 1) {
            return f.apply(values[0]);
        }

        return defaultValue;
    }

    private static <T> T getParam(
        final String[] args,
        final T defaultValue,
        final String prefix,
        final BiFunction<String, T, T> f
    ) {
        if (args != null && Arrays.stream(args).noneMatch(Objects::isNull)) {
            final String[] values = Arrays.stream(args).filter(o -> o.startsWith(prefix)).toArray(String[]::new);

            if (values.length == 1) {
                final String[] splitVals = values[0].split("=");

                if (splitVals.length == 2) {
                    return f.apply(splitVals[1], defaultValue);
                }
            }
        }

        return defaultValue;
    }

    private static Coordinate validateCord(final Coordinate cord, final int h, final int w) {
        return new Coordinate(Math.min(cord.row(), h - 1), Math.min(cord.col(), w - 1));
    }

    public static void main(String[] args) {
        if (args != null && Arrays.asList(args).contains("--help")) {
            for (final String inf : INF) {
                OUTPUT.println(inf);
            }
        }

        final int width = getParam(args, DEFAULT_WIDTH, "--width", PARSE_INT);
        final int height = getParam(args, DEFAULT_HEIGHT, "--height", PARSE_INT);
        final Coordinate startPoint = validateCord(getParam(
            args, DEFAULT_START_POINT, "--start", PARSE_CORD), height, width);
        final Coordinate endPoint = validateCord(getParam(
            args, new Coordinate(height - 1, width - 1), "--end", PARSE_CORD), height, width);
        final Generator generator = getParam(args, DEFAULT_GENERATOR, GENERATORS.keySet(), GENERATORS::get);
        final Solver solver = getParam(args, DEFAULT_SOLVER, SOLVERS.keySet(), SOLVERS::get);

        final Maze maze = generator.generate(height, width);
        OUTPUT.println(RENDERER.render(maze));

        final List<Coordinate> list = solver.solve(maze, startPoint, endPoint);
        OUTPUT.println(RENDERER.render(maze, list));

        OUTPUT.println("run with flag --help for more info");
    }
}
