package general;

public class Node {

	private State state;
	private Node parent;
	private Operator operator;
	private int depth, pathCost;

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
	
	public State getState() { return this.state; }

	public Node getParent() { return parent; }

	public Operator getOperator() { return operator; }

	public int getDepth() { return depth; }

	public int getPathCost() { return pathCost; }	
	
	public void setPathCost(int newCost) { pathCost = newCost; }
}
