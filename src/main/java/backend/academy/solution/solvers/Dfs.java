package backend.academy.solution.solvers;

import backend.academy.solution.maze.Cell;

public class Dfs extends AbsAlgo {
    private void dfs(final Cell now, final int weight) {
        used[now.row()][now.col()] = true;
        curWay().add(now.getViewCord());

        final int curWeight = weight
            + switch (cellMaze[now.getViewCord().row() / 2][now.getViewCord().col() / 2].type()) {
            case BAD -> 2;
            case PASSAGE -> 1;
            default -> 0;
        };

        if (now.isThatCell(end())) {
            if (curWeight <= maxWeight) {
                maxWeight = curWeight;
                way().clear();
                way().addAll(curWay());
            }
        } else {
            for (final Cell neighbor : now.neighbors()) {
                final Cell cellNeighbor = cellMaze[neighbor.row()][neighbor.col()];

                if (!used[cellNeighbor.row()][cellNeighbor.col()]) {
                    dfs(cellNeighbor, curWeight);
                }
            }
        }

        used[now.row()][now.col()] = false;
        curWay().removeLast();
    }

    @Override
    protected void algo(final Cell now) {
        dfs(now, 0);
    }
}
