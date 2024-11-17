package backend.academy.solution.solvers;

import backend.academy.solution.maze.Cell;
import backend.academy.solution.maze.Coordinate;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Bfs extends AbsAlgo {
    @Override
    protected void algo(final Cell start) {
        used[start.row()][start.col()] = true;
        final Map<Cell, Cell> cellParents = new HashMap<>();

        final Queue<Cell> queue = new LinkedList<>();
        queue.add(start);

        do {
            final Cell v = queue.poll();

            if (v.isEqualCord(end())) {
                buildWay(cellParents, start.getViewCord());
                break;
            }

            for (final Cell neighbor : v.neighbors()) {
                final Cell cellNeighbor = cellMaze[neighbor.row()][neighbor.col()];

                if (!used[cellNeighbor.row()][cellNeighbor.col()]) {
                    used[cellNeighbor.row()][cellNeighbor.col()] = true;
                    queue.add(cellNeighbor);
                    cellParents.put(cellNeighbor, v);
                }
            }
        } while (!queue.isEmpty());
    }

    private void buildWay(final Map<Cell, Cell> cellParents, final Coordinate start) {
        Cell cell = cellMaze[end().row()][end().col()];
        way().add(cell.getViewCord());

        while (!cell.isEqualCord(start)) {
            final Cell now = cellParents.get(cell);
            way().add(now.getViewCord());
            cell = now;
        }

        Collections.reverse(way());
    }
}
