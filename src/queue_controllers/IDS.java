package queue_controllers;

import java.util.ArrayList;
import java.util.Stack;

import general.Node;

public class IDS extends QueueController {

	private static final int MAX_DEPTH = 100;
	private Stack<Node> stack = new Stack<>();
	private int depth = 0;
	
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

	@Override
	public Node removeFront() { return stack.pop(); }	
}
