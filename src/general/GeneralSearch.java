package general;
import java.util.ArrayList;

import queue_controllers.QueueController;

public abstract class GeneralSearch {
	
	
	
	private static ArrayList<Node> expand(Node node, ArrayList<Operator> operators) {
		ArrayList<Node> expandedNodes = new ArrayList<Node>(1);
		for(Operator operator: operators) {
			Node nxtNode = operator.apply(node);
			if(nxtNode != null)
				expandedNodes.add(nxtNode);
		}
		return expandedNodes;
	}
	
	public static void search(Problem problem, QueueController queueController, boolean visualize) {
		
		State initState = problem.getInitialState();
		
		queueController.makeQueue(new Node(initState, null, null));
		
		while(!queueController.isEmpty()) {
			Node node = queueController.removeFront();
		
			if(problem.testGoal(node.getState())) 
			{
				System.out.println("Goal Reached!");
				if(visualize)
				{
					printSolution(node);
					System.out.printf("Path Length = %d\n", node.getDepth());
					System.out.printf("Path Cost = %d\n", node.getPathCost());
				}
				return;
			}
			
			queueController.add(expand(node, problem.getOperators()));
		}
		System.out.println("Failure :(");
	}
	
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
