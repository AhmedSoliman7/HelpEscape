package queue_controllers;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import general.Node;
import general.State;

/**
 * Abstract class for the Queue Controller (Search Strategy/Queuing Function)
 */
public abstract class QueueController {

	protected TreeMap<State, Integer> vis;
	protected Node initNode;

	/**
	 * Checks whether the queue is empty.
	 * @return true if the queue is empty.
	 */
	public abstract boolean isEmpty();

	/**
	 * Adds the input expanded nodes to the queue (if they are not visited before).
	 * @param nodes the nodes to be added to the queue.
	 */
	public abstract void add(ArrayList<Node> nodes);

	/**
	 * Constructs a new queue with the initial node.
	 * @param node the initial node.
	 */
	public final void makeQueue(Node node) {
		initNode = node;
		vis = new TreeMap<>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(initNode);
		add(nodes);
	}

	/**
	 * Removes the front of the queue which is the node chosen for relaxation.
	 * @return the removed node.
	 */
	public abstract Node removeFront();
}
