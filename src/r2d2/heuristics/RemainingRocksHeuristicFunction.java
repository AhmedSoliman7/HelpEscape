package r2d2.heuristics;

import general.functions.HeuristicFunction;
import general.Node;
import r2d2.R2D2Operator;
import r2d2.R2D2State;

public class RemainingRocksHeuristicFunction extends HeuristicFunction {

    @Override
    public Integer apply(Node node) {
        R2D2State state = (R2D2State) node.getState();
        return state.getRemainingRocks() * R2D2Operator.ROCK_PUSH_COST;
    }
}
