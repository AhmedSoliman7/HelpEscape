package general;
public abstract class Operator {

	protected int cost;
	protected String name;
	
	protected Operator(int cost, String name) { this.cost = cost; this.name = name; }
	
	public abstract Node apply(Node node);
	
	public int getCost() { return cost; }
	
	public String getName() { return name; }
}
