package r2d2;

import java.util.Scanner;

import general.functions.AStarEvaluationFunction;
import general.functions.EvaluationFunction;
import general.functions.GreedyEvaluationFunction;
import general.functions.HeuristicFunction;
import general.GeneralSearch;
import queue_controllers.*;
import r2d2.heuristics.FurthestRockHeuristicFunction;
import r2d2.heuristics.RemainingRocksHeuristicFunction;
import r2d2.heuristics.RockPadMatchingHeuristicFunction;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter grid dimensions separated by a single space");
		int n = sc.nextInt(), m = sc.nextInt();
		R2D2Problem problem = new R2D2Problem(n, m);
		System.out.println("Initial Grid\n------------");
		System.out.println(problem.getInitialState());
		while(true)
		{
			System.out.println("Enter search strategy (or \"done\")");
			String strategy = sc.next();
			if(strategy.equals("done"))
				break;
			QueueController controller = getController(strategy);
			GeneralSearch.search(problem, controller, true);
		}
		sc.close();
		
	}
	
	private static QueueController getController(String s)
	{
		if(s.equals("BF"))			//Breadth-First Search
			return new BFS();
		if(s.equals("DF"))			//Depth-First Search
			return new DFS();
		if(s.equals("ID"))			//Iterative-Deepening Search
			return new IDS();
		if(s.equals("UC"))			//Uniform-Cost Search
			return new UCS();

		// Best-First Search
		EvaluationFunction evalFunc = getEvaluationFunction(s);
		return new BestFS(evalFunc);
	}

	private static EvaluationFunction getEvaluationFunction(String s)
	{
		HeuristicFunction heurFunc = getHeuristicFunction(s);
		if(s.startsWith("GR"))
			return new GreedyEvaluationFunction(heurFunc);
		if(s.startsWith("AR"))
			return new AStarEvaluationFunction(heurFunc);
		return null;
	}

	private static HeuristicFunction getHeuristicFunction(String s)
	{
		if(s.charAt(s.length() - 1) == '1')
			return new RemainingRocksHeuristicFunction();
		if(s.charAt(s.length() - 1) == '2')
			return new FurthestRockHeuristicFunction();
		if(s.charAt(s.length() - 1) == '3')
			return new RockPadMatchingHeuristicFunction();
		return null;
	}
}
