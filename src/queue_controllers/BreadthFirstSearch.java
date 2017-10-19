package queue_controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import generic.Node;

/**
 * Breadth-First Search
 */
public class BreadthFirstSearch extends QueueController {

	private Queue<Node> queue = new LinkedList<>();

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
			if(pathCost == null) {
				vis.put(node.getState(), node.getPathCost());
				queue.add(node);
			}
		}
	}

	/**
	 * Removes the front of the queue which is the node chosen for relaxation.
	 * The front of the queue in DepthFirstSearch is the first inserted node (FIFO).
	 * @return the removed node.
	 */
	@Override
	public Node removeFront() { return queue.remove(); }

	
}
