package general.functions;

import general.Node;

public class AStarEvaluationFunction extends EvaluationFunction {

    public AStarEvaluationFunction(HeuristicFunction heurFunc) { super(heurFunc); }

    @Override
    public Integer apply(Node node) {
        return node.getPathCost() + heurFunc.apply(node);
    }
}
