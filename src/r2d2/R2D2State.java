package r2d2;
import general.State;

public class R2D2State extends State {
	
	private char[][] grid;
	private Cell robotPosition;
	private int remainingRocks;

	/**
	 * Constructs an R2D2 state given the grid and robot position.
	 * @param grid the grid of the new constructed state.
	 * @param robotPosition the position of the robot in the grid.
	 */
	public R2D2State(char[][] grid, Cell robotPosition)
	{
		this.grid = grid;
		this.robotPosition = robotPosition;
		for(char[] row: grid)
			for(char c: row)
				if(c == R2D2Problem.ROCK)
					++this.remainingRocks;
	}

	/**
	 * @return the grid of the current state.
	 */
	public char[][] getGrid() { return grid; }

	/**
	 * @return the cell position of the robot.
	 */
	public Cell getRobotPosition() { return robotPosition; }

	/**
	 * @return the number of remaining unmatched rocks.
	 */
	public int getRemainingRocks() { return remainingRocks; }

	/**
	 * Compares the current state with another state using ASCII
	 * values of the corresponding cells. This is only necessary
	 * to arrange states in a data structure (such as treemap)
	 * to check for visited states.
	 * @param o the state to be compared with the current state
	 * @return 0 if the two states are the same, negative value if
	 * the current state is lexicographically smaller and a positive
	 * number otherwise.
	 */
	@Override
	public int compareTo(State o) {
	
		char[][] oGrid = ((R2D2State)o).getGrid();
		for(int i = 0; i < grid.length; ++i)
			for(int j = 0; j < grid[0].length; ++j)
				if(grid[i][j] != oGrid[i][j])
					return grid[i][j] - oGrid[i][j];
		return 0;
	}

	/**
	 * @return a representation for the state grid.
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(char[] row: grid)
			sb.append(row).append("\n");
		return sb.toString();
	}
}
