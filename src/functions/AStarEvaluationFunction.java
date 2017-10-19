package functions;

import generic.Node;

public class AStarEvaluationFunction extends EvaluationFunction {

    /**
     * Constructs an A* evaluation function based on the input heuristic function.
     * @param heurFunc the heuristic function to be used in the A* evaluation function.
     */
    public AStarEvaluationFunction(HeuristicFunction heurFunc) { super(heurFunc); }

    /**
     * Calculates the estimated cost of the given node which the sum of the node
     * path cost and the heuristic cost
     * @param node the node to which the evaluation function is applied.
     * @return the estimated cost.
     */
    @Override
    public Integer apply(Node node) { return node.getPathCost() + heurFunc.apply(node); }
}
