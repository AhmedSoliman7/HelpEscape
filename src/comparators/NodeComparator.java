package comparators;

import generic.Node;

import java.util.Comparator;

/**
 * Comparator of nodes for ordered-search strategies.
 */
public abstract class NodeComparator implements Comparator<Node> {

    /**
     * Computes the node cost which is used in the queue ordering.
     * @param node the node to find its cost.
     * @return the cost of the node.
     */
    public abstract Integer getNodeCost(Node node);
}
