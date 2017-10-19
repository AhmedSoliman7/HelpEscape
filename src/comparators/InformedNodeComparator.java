package comparators;

import generic.Node;
import functions.EvaluationFunction;

/**
 * A comparator of nodes for best-first search.
 */
public class InformedNodeComparator extends NodeComparator {

    EvaluationFunction evalFunc;

    /**
     * Constructs a new informed comparator with the given evaluation function.
     * @param evalFunc
     */
    public InformedNodeComparator(EvaluationFunction evalFunc) {
        this.evalFunc = evalFunc;
    }

    /**
     * Computes the node cost which is used in the queue ordering. The node
     * cost is the estimated cost by the evaluation function.
     * @param node the node to find its cost.
     * @return the cost of the node.
     */
    @Override
    public Integer getNodeCost(Node node) {
        return evalFunc.apply(node);
    }

    /**
     * Compares two nodes based on the estimated cost of every node.
     * @param a the first node to compare
     * @param b the second node to compare
     * @return zero if the nodes have equal costs, a negative number if the first node
     * has smaller cost, a positive number otherwise.
     */
    @Override
    public int compare(Node a, Node b) { return evalFunc.apply(a) - evalFunc.apply(b); }
}
