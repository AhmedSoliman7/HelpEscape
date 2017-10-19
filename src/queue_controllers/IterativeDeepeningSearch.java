package queue_controllers;

import java.util.ArrayList;
import java.util.Stack;

import generic.Node;

/**
 * Iterative-Deepening Search
 */
public class IterativeDeepeningSearch extends QueueController {

	private static final int MAX_DEPTH = 1_000_000_000;
	private Stack<Node> stack = new Stack<>();
	private int depth = 0;

	/**
	 * Checks whether the queue is empty and handles searching depth.
	 * If the queue is empty and maximum depth is reached, then it
	 * returns that the queue is empty. Otherwise, it increases the
	 * current depth and adds the initial node to the queue.
	 * @return true if the queue is empty and the maximum depth is reached.
	 */
	@Override
	public boolean isEmpty() 
	{ 
		if(!stack.isEmpty())
			return false;
		if(stack.isEmpty() && depth == MAX_DEPTH)
			return true;
		++depth;
		makeQueue(initNode);
		return false;
	}

	/**
	 * Adds the input expanded nodes to the queue if they are not visited before
	 * and if their depth does not exceed maximum depth.
	 * @param nodes the nodes to be added to the queue.
	 */
	@Override
	public void add(ArrayList<Node> nodes) {
		for(Node node: nodes)
		{
			Integer pathCost = vis.get(node.getState());
			if(node.getDepth() <= depth && pathCost == null) {
				vis.put(node.getState(), node.getPathCost());
				stack.push(node);
			}
		}
	}

	/**
	 * Removes the front of the queue which is the node chosen for relaxation.
	 * The front of the queue in IterativeDeepeningSearch is the last inserted node (LIFO)
	 * @return the removed node.
	 */
	@Override
	public Node removeFront() { return stack.pop(); }	
}
