package queue_controllers;
import java.util.ArrayList;
import java.util.TreeSet;

import general.Node;
import general.State;

public abstract class QueueController {

	protected TreeSet<State> vis;
	protected Node initNode;
	
	public abstract boolean isEmpty();
	
	public final void add(ArrayList<Node> nodes)
	{
		// filter nodes and mark visited
		ArrayList<Node> filteredNodes = new ArrayList<Node>(nodes.size());
		for(Node node: nodes)
			if(vis.add(node.getState()))
				filteredNodes.add(node);
		addFiltered(filteredNodes);
	}

	public final void makeQueue(Node node)
	{
		initNode = node;
		vis = new TreeSet<>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(initNode);
		add(nodes);
	}
	
	protected abstract void addFiltered(ArrayList<Node> nodes);
	
	public abstract Node removeFront();
	
}
