package general;

public class Node {

	private State state;
	private Node parent;
	private Operator operator;
	private int depth, pathCost;

	/**
	 * Constructs a new node with the given state.
	 * @param state the state encapsulated in the constructed node.
	 * @param parent the parent from which this node is reached.
	 * @param operator the operator applied to reach this node.
	 */
	public Node(State state, Node parent, Operator operator) {
		this.state = state;
		this.parent = parent;
		this.operator = operator;
		if(parent != null)
		{
			this.depth = parent.getDepth() + 1;
			this.pathCost = parent.getPathCost() + operator.getCost();		
		}

	}

	/**
	 * @return the state encapsulated in the node.
	 */
	public State getState() { return this.state; }

	/**
	 * @return the parent node from which this node was reached.
	 */
	public Node getParent() { return parent; }

	public Operator getOperator() { return operator; }

	/**
	 * @return the depth of the node which is the number of operators
	 * applied from the initial node to reach the current node.
	 */
	public int getDepth() { return depth; }

	/**
	 * @return the path cost of the node.
	 */
	public int getPathCost() { return pathCost; }

	/**
	 * Sets the cost of the current node. Usually used when the path cost
	 * of the node is not only dependent on the path cost of the previous
	 * node and the fixed operator cost, but also on how the effect of
	 * the operator which is a combination of the previous state and the
	 * operator applied
	 * @param newCost the new cost of the node.
	 */
	public void setPathCost(int newCost) { pathCost = newCost; }
}
