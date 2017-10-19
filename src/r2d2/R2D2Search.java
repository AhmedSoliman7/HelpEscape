package r2d2;

import java.util.Scanner;

import functions.AStarEvaluationFunction;
import functions.EvaluationFunction;
import functions.GreedyEvaluationFunction;
import functions.HeuristicFunction;
import generic.GeneralSearch;
import queue_controllers.*;
import r2d2.heuristics.FurthestRockHeuristicFunction;
import r2d2.heuristics.RemainingRocksHeuristicFunction;
import r2d2.heuristics.RockPadMatchingHeuristicFunction;

/**
 * Run everything from here :)
 */
public class R2D2Search {

	/**
	 * R2D2Search function.
	 *
	 * Takes grid dimensions from the user and generates the problem
	 * based on these dimensions. If the input dimensions are -1 -1, the user is
	 * allowed to enter the grid manually.
	 *
	 * After grid initializations, the user is allowed to make multiple runs for
	 * different search strategies. Available search strategies are:
	 * 1. Breadth-First Search => BF
	 * 2. Depth-First Search => DF
	 * 3. Iterative-Deepening Search => ID
	 * 4. Uniform-Cost Search => UC
	 * 5. Greedy Search with 3 different heuristics => GRi, i = {1, 2, 3}
	 * 6. A* Search with 3 different heuristics => ARi, i = {1, 2, 3}
	 *
	 * Available heuristics:
	 * 1. The number of unmatched rocks multiplied by the push cost.
	 * 2. The Manhattan distance from the robot to the furthest rock.
	 * 3. The sum of Manhattan distances from each unmatched rock to
	 * the nearest pad to it multiplied by the push cost.
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter grid dimensions separated by a single space");
		int n = sc.nextInt(), m = sc.nextInt();
		R2D2Problem problem = n == -1 ? new R2D2Problem(getGridFromInput(sc)) : new R2D2Problem(n, m);
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

	/**
	 * Constructs a grid from the input (manually).
	 * @param sc the input reader.
	 * @return the constructed grid.
	 */
	private static char[][] getGridFromInput(Scanner sc)
	{
		System.out.println("[Input Grid] Enter grid dimensions");
		int n = sc.nextInt(), m = sc.nextInt();
		System.out.printf("[Input Grid] Enter %d x %d grid\n", n, m);
		char[][] grid = new char[n][];
		for(int i = 0; i < n; ++i)
			grid[i] = sc.next().toCharArray();
		return grid;
	}

	/**
	 * Creates an instance of the search strategy / queuing function
	 * used as defined by the user.
	 * @param s the code of the queue controller to be instantiated.
	 * @return the constructed queue controller.
	 */
	private static QueueController getController(String s)
	{
		if(s.equals("BF"))			//Breadth-First Search
			return new BreadthFirstSearch();
		if(s.equals("DF"))			//Depth-First Search
			return new DepthFirstSearch();
		if(s.equals("ID"))			//Iterative-Deepening Search
			return new IterativeDeepeningSearch();
		if(s.equals("UC"))			//Uniform-Cost Search
			return new UniformCostSearch();

		// Best-First Search
		EvaluationFunction evalFunc = getEvaluationFunction(s);
		return new BestFirstSearch(evalFunc);
	}

	/**
	 * Creates an evaluation function as defined by the input string.
	 * @param s the code of the evaluation and heuristic functions.
	 * @return the constructed evaluation function.
	 */
	private static EvaluationFunction getEvaluationFunction(String s)
	{
		HeuristicFunction heurFunc = getHeuristicFunction(s);
		if(s.startsWith("GR"))
			return new GreedyEvaluationFunction(heurFunc);
		if(s.startsWith("AR"))
			return new AStarEvaluationFunction(heurFunc);
		return null;
	}

	/**
	 * Creates an evaluation function as defined by the input string.
	 * @param s the code of the evaluation and heuristic functions.
	 * @return the constructed heuristic function.
	 */
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
