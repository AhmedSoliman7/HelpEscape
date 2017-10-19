package queue_controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import general.Node;

/**
 * Uniform-Cost Search
 */
public class UCS extends QueueController {

	private PriorityQueue<Node> queue;

	/**
	 * Constructs a uniform-cost queue controller based on the default comparator function.
	 */
	public UCS() { this(new UninformedNodeComparator()); }

	/**
	 * Constructs a uniform-cost queue controller based on the comparator function.
	 * @param comp the comparator to be used for cost comparison.
	 */
	protected UCS(Comparator<Node> comp) { queue = new PriorityQueue<>(comp); }

	/**
	 * Checks whether the queue is empty.
	 * @return true if the queue is empty.
	 */
	@Override
	public boolean isEmpty() { return queue.isEmpty(); }

	/**
	 * Adds the input expanded nodes to the queue (if they are not visited before).
	 * @param nodes the nodes to be added to the queue.
	 */
	@Override
	public void add(ArrayList<Node> nodes) {
		for(Node node: nodes)
		{
			Integer pathCost = vis.get(node.getState());
			if(pathCost == null || node.getPathCost() < pathCost) {
				vis.put(node.getState(), node.getPathCost());
				queue.add(node);
			}
		}
	}

	/**
	 * Removes the front of the queue which is the node chosen for relaxation.
	 * The front of the queue is based on a comparator function.
	 * @return the removed node.
	 */
	@Override
	public Node removeFront() { return queue.remove(); }

	/**
	 * A comparator for the uniform cost search. It compares nodes based on
	 * the path cost.
	 */
	private static class UninformedNodeComparator implements Comparator<Node>
	{
		public int compare(Node a, Node b) { return a.getPathCost() - b.getPathCost(); }
	}
	
}
