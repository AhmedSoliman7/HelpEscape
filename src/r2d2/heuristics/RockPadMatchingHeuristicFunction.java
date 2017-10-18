package r2d2.heuristics;

import general.Node;
import general.functions.HeuristicFunction;
import r2d2.Cell;
import r2d2.R2D2Operator;
import r2d2.R2D2Problem;
import r2d2.R2D2State;

import java.util.ArrayList;

// Puts every rock on the nearest pad
public class RockPadMatchingHeuristicFunction extends HeuristicFunction {

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

    private static int distanceToNearestPad(Cell rock, ArrayList<Cell> pads) {
        int minDist = -1;
        for(Cell pad: pads) {
            int curDist = Math.abs(rock.x - pad.x) + Math.abs(rock.y - pad.y);
            if(minDist == -1 || curDist < minDist)
                minDist = curDist;
        }
        return minDist;
    }

    private static void getRocksAndPads(char[][] grid, int n, int m, ArrayList<Cell> rocks, ArrayList<Cell> pads) {
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < m; ++j)
                if(grid[i][j] == R2D2Problem.ROCK)
                    rocks.add(new Cell(i, j));
                else if(grid[i][j] == R2D2Problem.PRESSURE_PAD)
                    pads.add(new Cell(i, j));
    }
}
