package queue_controllers;

import java.util.ArrayList;
import java.util.Stack;

import general.Node;

public class DFS extends QueueController {

	private Stack<Node> stack = new Stack<>();
	
	@Override
	public boolean isEmpty() { return stack.isEmpty(); }

	@Override
	public void addFiltered(ArrayList<Node> nodes) {
		for(Node node: nodes)
			stack.push(node);
	}

	@Override
	public Node removeFront() { return stack.pop(); }

	
}
