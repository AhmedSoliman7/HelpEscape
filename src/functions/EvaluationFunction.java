package functions;

import general.Node;

public class EvaluationFunction {

    HeuristicFunction heurFunc;

    public EvaluationFunction() { this(new DefaultHeuristicFunction()); }

    public EvaluationFunction(HeuristicFunction heurFunc) {
        this.heurFunc = heurFunc;
    }

    public Integer apply(Node node) {
        return node.getPathCost() + heurFunc.apply(node);
    }

}
