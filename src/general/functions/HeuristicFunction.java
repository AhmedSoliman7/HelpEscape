package general.functions;

import general.Node;

/**
 * Abstract class for the heuristic function.
 */
public abstract class HeuristicFunction {

    /**
     * Calculates the heuristic function of the input node.
     * @param node the node to which the heuristic function is applied.
     * @return the heuristic cost.
     */
    public abstract Integer apply(Node node);

}
