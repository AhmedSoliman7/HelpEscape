package general;

import java.util.ArrayList;

/**
 * Abstract Problem class
 */
public abstract class Problem {

	protected ArrayList<Operator> operators;
	
	protected State initialState;

	/**
	 * @return the operators of the problem.
	 */
	public abstract ArrayList<Operator> getOperators();

	/**
	 * @return the initial state of the problem.
	 */
	public abstract State getInitialState();

	/**
	 * Tests whether the input state is a goal state.
	 * @param state the state to be tested.
	 * @return true if the input state is a goal state.
	 */
	public abstract boolean testGoal(State state);
}
