package r2d2;
import general.State;

public class R2D2State extends State {
	
	private char[][] grid;
	private Cell robotPosition;
	private int remainingRocks;
		
	public R2D2State(char[][] grid, Cell robotPosition)
	{
		this.grid = grid;
		this.robotPosition = robotPosition;
		for(char[] row: grid)
			for(char c: row)
				if(c == R2D2Problem.ROCK)
					++this.remainingRocks;
	}
	
	public char[][] getGrid() { return grid; }
	
	public Cell getRobotPosition() { return robotPosition; }
	
	public int getRemainingRocks() { return remainingRocks; }
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(char[] row: grid)
			sb.append(row).append("\n");
		return sb.toString();
	}

	@Override
	public int compareTo(State o) {
	
		char[][] oGrid = ((R2D2State)o).getGrid();
		for(int i = 0; i < grid.length; ++i)
			for(int j = 0; j < grid[0].length; ++j)
				if(grid[i][j] != oGrid[i][j])
					return grid[i][j] - oGrid[i][j];
		return 0;
	}
}
