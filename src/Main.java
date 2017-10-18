import java.util.Scanner;

import general.GeneralSearch;
import queue_controllers.*;
import r2d2.R2D2Problem;

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
		if(s.equals("BF"))
			return new BFS();
		if(s.equals("DF"))
			return new DFS();
		if(s.equals("ID"))
			return new IDS();
		if(s.equals("UC"))
			return new UCS();

		return null;
	}
}
