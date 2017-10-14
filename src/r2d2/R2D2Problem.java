package r2d2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import general.Operator;
import general.Problem;
import general.State;

public class R2D2Problem extends Problem{

	private int n, m;
	public char[][] grid;
	
	public static final char ROBOT = 'R', TELEPORT = 'T', EMPTY_CELL = '.';
	public static final char OBSTACLE = '#', ROCK = 'O', PRESSURE_PAD = 'P';
	public static final char ROCK_ON_PRESSURE_PAD = 'X', ROBOT_ON_PRESSURE_PAD = 'r', ROBOT_ON_TELEPORT = 'A';
	
	
	public R2D2Problem(int N, int M) {
		
		int n = N - 2, m = M - 2;
		this.n = N;
		this.m = M;
		grid = new char[N][M];
		for(char[] row: grid)
			Arrays.fill(row, R2D2Problem.EMPTY_CELL);

		Random rand = new Random();
		int numberOfRocks = rand.nextInt((n * m - 2) / 2 + 1);
		int numberOfObstacles = rand.nextInt(Math.min(n * m - 2 - numberOfRocks * 2 + 1, Math.max(n, m)));	// TODO: find reasonable values
		int[] positions = new int[n * m];
		for (int i = 0; i < n * m; ++i)
			positions[i] = i;
		shuffle(positions, rand);
		char[] symbols = { 'R', 'T', 'O', 'P', '#' };
		int[] sizes = { 1, 1, numberOfRocks, numberOfRocks, numberOfObstacles };
		for (int i = 0, start = 0; i < 5; ++i) {
			setPositions(positions, start, start += sizes[i], symbols[i]);
		}
		
		initializeOperators();
	}
	
	private void initializeOperators()
	{
		operators = new ArrayList<>(4);
		final int[] dx = {-1, 0, 1, 0};
		final int[] dy = {0, 1, 0, -1};
		final String[] operatorName = {"Go North", "Go East", "Go South", "Go West"};
		for(int k = 0; k < 4; ++k)
			operators.add(new R2D2Operator(dx[k], dy[k], 1, operatorName[k]));
	}

	private void shuffle(int[] positions, Random rand) {
		for (int i = 0; i < positions.length; ++i) {
			int j = rand.nextInt(positions.length - i) + i;
			int tmp = positions[i];
			positions[i] = positions[j];
			positions[j] = tmp;
		}
	}

	private void setPosition(int position, char symbol) {
		grid[position / (m - 2) + 1][position % (m - 2) + 1] = symbol;
	}

	private void setPositions(int[] positions, int start, int end, char symbol) {
		for (int i = start; i < end; ++i)
			setPosition(positions[i], symbol);
	}

	public String toString() {
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			ret.append(grid[i]).append("\n");
		}
		return ret.toString();
	}
	
	public char[][] getGrid() { return grid; }
	
	public int[] getDimensions() { return new int[] { n, m }; }
	
	@Override
	public ArrayList<Operator> getOperators() { return operators; }

	@Override
	public State getInitialState() {

		Cell robotPosition = null;
		int remRocks = 0;
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < m; ++j)
				if(grid[i][j] == R2D2Problem.ROBOT)
					robotPosition = new Cell(i, j);
				else if(grid[i][j] == R2D2Problem.ROCK)
					++remRocks;
				
		return new R2D2State(grid, robotPosition, remRocks);
	}

	@Override
	public boolean testGoal(State state) {
		
		R2D2State st = (R2D2State)state;
		Cell robotPosition = st.getRobotPosition();
		return grid[robotPosition.x][robotPosition.y] == 'T';
	}	
}
