package r2d2.heuristics;

import general.Node;
import general.functions.HeuristicFunction;
import r2d2.Cell;
import r2d2.R2D2Operator;
import r2d2.R2D2Problem;
import r2d2.R2D2State;

import java.util.ArrayList;

/**
 * Heuristic Function for R2D2 Problem.
 *
 * For every rock that is not on a pad, the Manhattan distance to the nearest
 * (empty or non-empty) pad is calculated. The cost caused by this rock is the
 * distance multiplied by the push cost. The heuristic cost for the state is
 * the total sum of rock costs.
 *
 * h'(rock) = L1(rock, nearest_pad) * push_cost
 * h(node) = sum[r: unmatched rock]{h'(r)}
 */
public class RockPadMatchingHeuristicFunction extends HeuristicFunction {

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
        int n = grid.length, m = grid[0].length;

        ArrayList<Cell> rockPositions = new ArrayList<>(1);
        ArrayList<Cell> padPositions = new ArrayList<>(1);
        getRocksAndPads(grid, n, m, rockPositions, padPositions);

        int cost = 0;
        for(Cell rock: rockPositions)
            cost += distanceToNearestPad(rock, padPositions) * R2D2Operator.ROCK_PUSH_COST;
        return cost;
    }

    /**
     * Calculates, for a given rock, the Manhattan distance to the nearest pad.
     * @param rock the rock to find the distance for.
     * @param pads a list of pads in the grid.
     * @return the Manhattan distance to the nearest pad.
     */
    private static int distanceToNearestPad(Cell rock, ArrayList<Cell> pads) {
        int minDist = -1;   // pads can never be empty, so this value will be overwritten
        for(Cell pad: pads) {
            int curDist = Math.abs(rock.x - pad.x) + Math.abs(rock.y - pad.y);
            if(minDist == -1 || curDist < minDist)
                minDist = curDist;
        }
        return minDist;
    }

    /**
     * Searches for unmatched rocks and all (matched and unmatched) pads .
     * @param grid the grid to search in.
     * @param n the number of grid rows.
     * @param m the number of grid columns.
     * @param rocks a list to be filled with found rocks.
     * @param pads a list to be filled with found pads.
     */
    private static void getRocksAndPads(char[][] grid, int n, int m, ArrayList<Cell> rocks, ArrayList<Cell> pads) {
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < m; ++j)
            {
                char c = grid[i][j];
                if(c == R2D2Problem.ROCK)
                    rocks.add(new Cell(i, j));
                else if(R2D2Problem.isPressurePad(c))
                    pads.add(new Cell(i, j));
            }
    }
}
