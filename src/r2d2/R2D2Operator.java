package r2d2;

import general.Node;
import general.Operator;
import general.State;

public class R2D2Operator extends Operator {

	public static final int ROCK_PUSH_COST = 5;
	private int dx, dy;
	
	R2D2Operator(int dx, int dy, int cost, String name) { super(cost, name); this.dx = dx; this.dy = dy; }
	
	@Override
	public Node apply(Node node) {
		
		R2D2State state = (R2D2State)node.getState();
		char[][] grid = state.getGrid();
		
		Cell oldPos = state.getRobotPosition();
		Cell newPos = new Cell(oldPos.x + dx, oldPos.y + dy);

		// 1. can't go outside the grid
		if(!isValidCell(grid, newPos.x, newPos.y))
			return null;
		char c = grid[newPos.x][newPos.y];
		// 2. can't go to teleport if remaining rocks > 0
		// 3. can't go into an obstacle
		if(c == '#' || (state.getRemainingRocks() > 0 && c == 'T'))
			return null;

		
		char[] badPositionsForRock = {
			R2D2Problem.OBSTACLE,
			R2D2Problem.TELEPORT,
			R2D2Problem.ROCK,
			R2D2Problem.ROCK_ON_PRESSURE_PAD
		};
		
		int rockX = -1, rockY = -1;
		// 4. can't push rock if next cell after rock is non-empty (contains rock/obstacle/teleport/outside grid)
		if(c == R2D2Problem.ROCK || c == R2D2Problem.ROCK_ON_PRESSURE_PAD)
		{
			rockX = newPos.x + dx;
			rockY = newPos.y + dy;
			if(!isValidCell(grid, rockX, rockY) || new String(badPositionsForRock).indexOf(grid[rockX][rockY]) != -1)
				return null;
		}
		
		// VALID MOVE !!
		// 1. Construct new state
		int remRocks = state.getRemainingRocks(), n = grid.length, m = grid[0].length;
		char[][] newGrid = new char[n][m];
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < m; ++j)
			{
				char oldC = grid[i][j], newC;
				if(i == newPos.x && j == newPos.y)
				{
					if(oldC == R2D2Problem.TELEPORT)
						newC = R2D2Problem.ROBOT_ON_TELEPORT;
					else if(oldC == R2D2Problem.EMPTY_CELL)
						newC = R2D2Problem.ROBOT;
					else if(oldC == R2D2Problem.ROCK)
						newC = R2D2Problem.ROBOT;
					else // oldC == R2D2Problem.PRESSURE_PAD || oldC == R2D2Problem.ROCK_ON_PRESSURE_PAD
					{
						newC = R2D2Problem.ROBOT_ON_PRESSURE_PAD;
						if(oldC == R2D2Problem.ROCK_ON_PRESSURE_PAD)
							++remRocks;
					}
				}
				else if(i == rockX && j == rockY)
				{
					if(oldC == R2D2Problem.EMPTY_CELL)
						newC = R2D2Problem.ROCK;
					else // R2D2Problem.PRESSURE_PAD
					{
						newC = R2D2Problem.ROCK_ON_PRESSURE_PAD;
						--remRocks;
					}
				}
				else
				{
					if(oldC == R2D2Problem.ROBOT)
						newC = R2D2Problem.EMPTY_CELL;
					else if(oldC == R2D2Problem.ROBOT_ON_PRESSURE_PAD)
						newC = R2D2Problem.PRESSURE_PAD;
					else
						newC = oldC;
				}
				newGrid[i][j] = newC;
			}
		State newState = new R2D2State(newGrid, newPos, remRocks);
		Node newNode = new Node(newState, node, this);
		if(rockX != -1)
			newNode.setPathCost(newNode.getPathCost() + ROCK_PUSH_COST);
		return newNode;
	}
	
	private boolean isValidCell(char[][] grid, int x, int y)
	{
		return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length; 
	}

}
