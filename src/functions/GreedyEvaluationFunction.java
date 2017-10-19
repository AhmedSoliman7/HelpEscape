package functions;

import generic.Node;

public class GreedyEvaluationFunction extends EvaluationFunction {

    /**
     * Constructs a greedy evaluation function based on the input heuristic function.
     * @param heurFunc the heuristic function to be used in the greedy evaluation function.
     */
    public GreedyEvaluationFunction(HeuristicFunction heurFunc) { super(heurFunc); }

    /**
     * Calculates the estimated cost of the given node which the heuristic cost
     * @param node the node to which the evaluation function is applied.
     * @return the estimated cost.
     */
    @Override
    public Integer apply(Node node) {
        return heurFunc.apply(node);
    }
}
