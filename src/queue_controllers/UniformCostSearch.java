package queue_controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import general.Node;
import general.comparators.NodeComparator;
import general.comparators.UninformedNodeComparator;

/**
 * Uniform-Cost Search
 */
public class UCS extends QueueController {

	private PriorityQueue<Node> queue;
	private NodeComparator nodeComparator;
	/**
	 * Constructs a uniform-cost queue controller based on the default comparator function.
	 */
	public UCS() { this(new UninformedNodeComparator()); }

	/**
	 * Constructs a uniform-cost queue controller based on the comparator function.
	 * @param comp the comparator to be used for cost comparison.
	 */
	protected UCS(NodeComparator comp) { queue = new PriorityQueue<>(nodeComparator = comp); }

	/**
	 * Checks whether the queue is empty.
	 * @return true if the queue is empty.
	 */
	@Override
	public boolean isEmpty()
	{
		while(!queue.isEmpty()) {
			Node node = queue.peek();
			if(nodeComparator.getNodeCost(node) == vis.get(node.getState()))
				break;
			queue.remove();
		}
		return queue.isEmpty();
	}

	/**
	 * Adds the input expanded nodes to the queue (if they are not visited before).
	 * @param nodes the nodes to be added to the queue.
	 */
	@Override
	public void add(ArrayList<Node> nodes) {
		for(Node node: nodes)
		{
			Integer oldNodeCost = vis.get(node.getState());
			Integer curNodeCost = nodeComparator.getNodeCost(node);
			if(oldNodeCost == null || curNodeCost < oldNodeCost) {
				vis.put(node.getState(), curNodeCost);
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
}
