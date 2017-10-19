package r2d2.heuristics;

import functions.HeuristicFunction;
import generic.Node;
import r2d2.R2D2Operator;
import r2d2.R2D2State;

/**
 * Heuristic Function for R2D2 Problem. The heuristic cost of a node
 * is the number of unmatched rocks multiplied by the push cost.
 *
 * h(node) = sum[r: unmatched rock]{push_cost}
 */
public class RemainingRocksHeuristicFunction extends HeuristicFunction {

    /**
     * Applys the heuristic function to the input node.
     *
     * @param node: the node to which the heuristic function is applied.
     * @return the heuristic cost of the node.
     */
    @Override
    public Integer apply(Node node) {
        R2D2State state = (R2D2State) node.getState();
        return state.getRemainingRocks() * R2D2Operator.ROCK_PUSH_COST;
    }
}
