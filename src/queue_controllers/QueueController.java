package queue_controllers;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import general.Node;
import general.State;

public abstract class QueueController {

	protected TreeMap<State, Integer> vis;
	protected Node initNode;

	public abstract boolean isEmpty();

	public abstract void add(ArrayList<Node> nodes);

	public final void makeQueue(Node node) {
		initNode = node;
		vis = new TreeMap<>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(initNode);
		add(nodes);
	}

	public abstract Node removeFront();
}
