package backend.academy.solution.solvers;

import backend.academy.solution.maze.Cell;

public class Dfs extends AbsAlgo {
    @Override
    protected boolean algo(final Cell now) {
        used[now.row()][now.col()] = true;
        way().add(now.getViewCord());
        if (now.isThatCell(end())) {
            return true;
        }

        for (final Cell neighbor : now.neighbors()) {
            final Cell cellNeighbor = cellMaze[neighbor.row()][neighbor.col()];

            if (!used[cellNeighbor.row()][cellNeighbor.col()]) {
                if (algo(cellNeighbor)) {
                    return true;
                }
            }
        }

        used[now.row()][now.col()] = false;
        way().removeLast();
        return false;
    }
}
