package r2d2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import general.Operator;
import general.Problem;
import general.State;

public class R2D2Problem extends Problem{

	/**
	 * Problem Definition: grid dimensions and description.
	 * n -> number of rows in the grid.
	 * m -> number of columns in the grid.
	 * grid -> 2D array with characters defined in the mapping below.
	 */
	private int n, m;
	public char[][] grid;

	/**
	 *	Mapping for all grid characters.
	 */
	public static final char ROBOT = 'R', TELEPORT = 'T', EMPTY_CELL = '.';
	public static final char OBSTACLE = '#', ROCK = 'O', PRESSURE_PAD = 'P';
	public static final char ROCK_ON_PRESSURE_PAD = 'X', ROBOT_ON_PRESSURE_PAD = 'r', ROBOT_ON_TELEPORT = 'A';
	private static final String PADS = "XPr";


	/**
	 * Constructs a new R2D2 problem. It randomly initializes a grid
	 * with the input dimensions and defines the problem operators.
	 * @param N the number of grid rows.
	 * @param M the number of grid columns.
	 */
	public R2D2Problem(int N, int M) {

		this.n = N;
		this.m = M;

		initializeGrid(N - 2, M - 2);
		initializeOperators();
	}

	/**
	 * Constructs a new R2D2 problem. It initializes the problem grid
	 * with the input grid and defines the problem operators.
	 * @param grid the input grid to be assigned to the constructed problem.
	 */
	public R2D2Problem(char[][] grid) {

		this.grid = grid;
		this.n = grid.length;
		this.m = grid[0].length;
		initializeOperators();
	}

	/**
	 * Initializes the problem with a random grid.
	 * @param n the number of grid rows.
	 * @param m the number of grid columns.
	 */
	private void initializeGrid(int n, int m)
	{
		grid = new char[this.n][this.m];
		for(char[] row: grid)
			Arrays.fill(row, R2D2Problem.EMPTY_CELL);
		Random rand = new Random();
		int numberOfRocks = rand.nextInt((n * m - 2) / 2 + 1);
		int numberOfObstacles = rand.nextInt(Math.min(n * m - 2 - numberOfRocks * 2 + 1, Math.max(n, m)));
		int[] positions = new int[n * m];
		for (int i = 0; i < n * m; ++i)
			positions[i] = i;
		shuffle(positions, rand);
		char[] symbols = { ROBOT, TELEPORT, ROCK, PRESSURE_PAD, OBSTACLE };
		int[] sizes = { 1, 1, numberOfRocks, numberOfRocks, numberOfObstacles };
		for (int i = 0, start = 0; i < symbols.length; ++i)
			setPositions(positions, start, start += sizes[i], symbols[i]);
	}

	/**
	 * Initializes the problem operators (moving in four directions).
	 */
	private void initializeOperators()
	{
		operators = new ArrayList<>(4);
		final int[] dx = {-1, 0, 1, 0};
		final int[] dy = {0, 1, 0, -1};
		final String[] operatorName = {"Go North", "Go East", "Go South", "Go West"};
		for(int k = 0; k < 4; ++k)
			operators.add(new R2D2Operator(dx[k], dy[k], 1, operatorName[k]));
	}

	/**
	 * Shuffles the input array.
	 * @param positions the array to be shuffled/
	 * @param rand the random variable that generates random numbers.
	 */
	private void shuffle(int[] positions, Random rand) {
		for (int i = 0; i < positions.length; ++i) {
			int j = rand.nextInt(positions.length - i) + i;
			int tmp = positions[i];
			positions[i] = positions[j];
			positions[j] = tmp;
		}
	}

	/**
	 * Sets a cell position with the given symbol in the grid.
	 * @param position the cell position.
	 * @param symbol the assigned symbol.
	 */
	private void setPosition(int position, char symbol) {
		grid[position / (m - 2) + 1][position % (m - 2) + 1] = symbol;
	}

	/**
	 * Sets a set of cell positions with the given symbol in the grid.
	 * @param positions the full list of grid positions.
	 * @param start the start index (inclusive) of the cell position to be assigned.
	 * @param end the end index (exclusive) of the cell position to be assigned.
	 * @param symbol the assigned symbol.
	 */
	private void setPositions(int[] positions, int start, int end, char symbol) {
		for (int i = start; i < end; ++i)
			setPosition(positions[i], symbol);
	}

	/**
	 * Checks with the input symbol represents (pressed or unpressed) pad.
	 * @param c the symbol to check.
	 * @return true if the input symbol is a pressure pad.
	 */
	public static boolean isPressurePad(char c) { return PADS.indexOf(c) != -1; }

	/**.
	 * @return a representation for the problem grid
	 */
	public String toString() {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			ret.append(grid[i]).append("\n");
		}
		return ret.toString();
	}

	/**
	 * @return the operators of the R2D2 problem.
	 */
	@Override
	public ArrayList<Operator> getOperators() { return operators; }

	/**
	 * @return the initial state of the R2D2 problem.
	 */
	@Override
	public State getInitialState() {

		Cell robotPosition = null;
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < m; ++j)
				if(grid[i][j] == R2D2Problem.ROBOT)
					robotPosition = new Cell(i, j);
		return new R2D2State(grid, robotPosition);
	}

	/**
	 * Tests whether the input state is a goal state. That's,
	 * the robot is at the teleport and it's already activated.
	 * @param state the state to test.
	 * @return true if the input state is a goal state.
	 */
	@Override
	public boolean testGoal(State state) {

		R2D2State st = (R2D2State)state;
		Cell robotPosition = st.getRobotPosition();
		return grid[robotPosition.x][robotPosition.y] == 'T';
	}
}
