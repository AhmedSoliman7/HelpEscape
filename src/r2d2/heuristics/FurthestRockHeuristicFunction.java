package r2d2.heuristics;

import general.functions.HeuristicFunction;
import general.Node;
import r2d2.Cell;
import r2d2.R2D2Problem;
import r2d2.R2D2State;

/**
 * Heuristic Function for R2D2 Problem. The heuristic cost of a node
 * is the Manhattan distance between the robot position and the position
 * of the furthest rock.
 * h(node) = max[r: unmatched rock]{L1(robot, r)}
 */
public class FurthestRockHeuristicFunction extends HeuristicFunction {

    /**
     * Applys the heuristic function to the input node.
     *
     * @param node: the node to which the heuristic function is applied.
     * @return the heuristic cost of the node.
     */
    @Override
    public Integer apply(Node node) {
        R2D2State state = (R2D2State) node.getState();
        char[][] grid = state.getGrid();
        Cell robot = state.getRobotPosition();
        int n = grid.length, m = grid[0].length;
        int maxRockDist = 0;
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < m; ++j)
                if(grid[i][j] == R2D2Problem.ROCK)
                    maxRockDist = Math.max(maxRockDist, Math.abs(i - robot.x) + Math.abs(j - robot.y));
        return maxRockDist;
    }
}
