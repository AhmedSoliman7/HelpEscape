package queue_controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import general.Node;

public class BFS extends QueueController {

	private Queue<Node> queue = new LinkedList<>();
	
	@Override
	public boolean isEmpty() { return queue.isEmpty(); }

	@Override
	public void addFiltered(ArrayList<Node> nodes) {
		for(Node node: nodes)
			queue.add(node);
	}

	@Override
	public Node removeFront() { return queue.remove(); }

	
}
