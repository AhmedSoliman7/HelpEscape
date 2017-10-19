package queue_controllers;

import general.comparators.InformedNodeComparator;
import general.functions.EvaluationFunction;

/**
 * Best-First Search
 */
public class BestFS extends UCS {

    /**
     * Constructs a Best-First Queue Controller with the given evaluation function
     * @param evalFunction the evaluation function to be used in the best-first search.
     */
    public BestFS(EvaluationFunction evalFunction) {
        super(new InformedNodeComparator(evalFunction));
    }
}
