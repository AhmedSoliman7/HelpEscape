package queue_controllers;

import java.util.ArrayList;
import java.util.Stack;

import general.Node;

/**
 * Depth-First Search
 */
public class DFS extends QueueController {

	private Stack<Node> stack = new Stack<>();

	/**
	 * Checks whether the queue is empty.
	 * @return true if the queue is empty.
	 */
	@Override
	public boolean isEmpty() { return stack.isEmpty(); }

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
				stack.push(node);
			}
		}
	}

	/**
	 * Removes the front of the queue which is the node chosen for relaxation.
	 * The front of the queue in DFS is the last inserted node (LIFO).
	 * @return the removed node.
	 */
	@Override
	public Node removeFront() { return stack.pop(); }
}