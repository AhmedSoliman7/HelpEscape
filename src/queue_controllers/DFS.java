package queue_controllers;

import java.util.ArrayList;
import java.util.Stack;

import general.Node;

public class DFS extends QueueController {

	private Stack<Node> stack = new Stack<>();
	
	@Override
	public boolean isEmpty() { return stack.isEmpty(); }

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

	@Override
	public Node removeFront() { return stack.pop(); }

	
}
