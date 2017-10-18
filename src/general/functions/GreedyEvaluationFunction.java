package general.functions;

import general.Node;

public class GreedyEvaluationFunction extends EvaluationFunction {

    public GreedyEvaluationFunction(HeuristicFunction heurFunc) { super(heurFunc); }

    @Override
    public Integer apply(Node node) {
        return heurFunc.apply(node);
    }
}
