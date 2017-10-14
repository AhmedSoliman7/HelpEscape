package general;

import java.util.ArrayList;

public abstract class Problem {

	protected ArrayList<Operator> operators;
	
	protected State initialState;
	
	public abstract ArrayList<Operator> getOperators();
	
	public abstract State getInitialState();
	
	public abstract boolean testGoal(State state);
}
