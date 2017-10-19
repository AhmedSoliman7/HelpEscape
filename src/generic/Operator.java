package generic;

/**
 * Abstract Operator class.
 */
public abstract class Operator {

	protected int cost;
	protected String name;

	/**
	 * Constructs a new operator with the input cost and name.
	 * @param cost the cost of the operator.
	 * @param name the name of the operator.
	 */
	protected Operator(int cost, String name) { this.cost = cost; this.name = name; }

	/**
	 * Applies the operator to the input node.
	 * @param node the node to which the operator is applied.
	 * @return the new node after applying the operator.
	 */
	public abstract Node apply(Node node);

	/**
	 * @return the operator cost.
	 */
	public int getCost() { return cost; }

	/**
	 * @return the operator name.
	 */
	public String getName() { return name; }
}