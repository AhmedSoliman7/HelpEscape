package generic;
import java.util.ArrayList;

import queue_controllers.QueueController;

public class GeneralSearch {

	/**
	 * Expands the input node using the list of given operators.
 	 * @param node the node to expand using the operators.
	 * @param operators the operators used to expand the node.
	 * @return the expanded nodes.
	 */
	private static ArrayList<Node> expand(Node node, ArrayList<Operator> operators) {
		ArrayList<Node> expandedNodes = new ArrayList<Node>(1);
		for(Operator operator: operators) {
			Node nxtNode = operator.apply(node);
			if(nxtNode != null)
				expandedNodes.add(nxtNode);
		}
		return expandedNodes;
	}

	/**
	 * Searches for a solution to the input problem given a queue controller.
	 * @param problem the problem to be solved.
	 * @param queueController the queuing function used for node selection.
	 * @param visualize a boolean to print the solution with some stats.
	 */
	public static void search(Problem problem, QueueController queueController, boolean visualize) {
		
		State initState = problem.getInitialState();
		
		queueController.makeQueue(new Node(initState, null, null));
		int expandedNodes = 0;
		while(!queueController.isEmpty()) {
			Node node = queueController.removeFront();
			++expandedNodes;
			if(problem.testGoal(node.getState())) 
			{
				System.out.println("Goal Reached!");
				if(visualize)
				{
					printSolution(node);
					System.out.printf("Solution path length = %d\n", node.getDepth());
					System.out.printf("Solution path cost = %d\n", node.getPathCost());
					System.out.printf("# Expanded nodes = %d\n", expandedNodes);
				}
				return;
			}
			
			queueController.add(expand(node, problem.getOperators()));
		}
		System.out.println("Failure :(");
	}

	/**
	 * Prints the states of the ancestors of the input node in addition to
	 * the state of the input node.
	 * @param node the node to print its state and its ancestors states.
	 */
	private static void printSolution(Node node)
	{
		if(node.getParent() != null)
		{
			printSolution(node.getParent());
			System.out.println(node.getOperator().getName());
		}
		
		System.out.println(node.getState());
	}
}
