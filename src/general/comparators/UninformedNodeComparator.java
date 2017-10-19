package general.comparators;

import general.Node;

/**
 * A comparator of nodes for uniform cost search.
 */
public class UninformedNodeComparator extends NodeComparator {

    /**
     * Computes the node cost which is used in the queue ordering. The node
     * cost in uniformed search is the path cost of the node.
     * @param node the node to find its cost.
     * @return the cost of the node.
     */
    @Override
    public Integer getNodeCost(Node node) { return node.getPathCost(); }

    /**
     * Compares two nodes based on the path cost of every node
     * @param a the first node to compare
     * @param b the second node to compare
     * @return zero if the nodes have equal costs, a negative number if the first node
     * has smaller path cost, a positive number otherwise.
     */
    @Override
    public int compare(Node a, Node b) { return a.getPathCost() - b.getPathCost(); }
}
