package functions;

import generic.Node;

/**
 * Abstract class for the evaluation function.
 */
public abstract class EvaluationFunction {

    protected HeuristicFunction heurFunc;

    /**
     * Constructs an evaluation function based on the input heuristic function.
     * @param heurFunc the heuristic function to be used in the evaluation function.
     */
    public EvaluationFunction(HeuristicFunction heurFunc) { this.heurFunc = heurFunc; }

    /**
     * Calculates the estimated cost of the input node.
     * @param node the node to which the evaluation function is applied.
     * @return the estimated cost.
     */
    public abstract Integer apply(Node node);
}
