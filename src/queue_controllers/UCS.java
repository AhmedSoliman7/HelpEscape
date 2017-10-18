package queue_controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import general.Node;

public class UCS extends QueueController {

	private PriorityQueue<Node> queue;

	public UCS() { this(new UninformedNodeComparator()); }

	protected UCS(Comparator<Node> comp) { queue = new PriorityQueue<>(comp); }
	
	@Override
	public boolean isEmpty() { return queue.isEmpty(); }

	@Override
	public void addFiltered(ArrayList<Node> nodes) {
		for(Node node: nodes)
			queue.add(node);
	}

	@Override
	public Node removeFront() { return queue.remove(); }
	
	
	private static class UninformedNodeComparator implements Comparator<Node>
	{
		public int compare(Node a, Node b) { return a.getPathCost() - b.getPathCost(); }
	}
	
}
