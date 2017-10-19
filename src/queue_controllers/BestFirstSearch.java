package queue_controllers;

import comparators.InformedNodeComparator;
import functions.EvaluationFunction;

/**
 * Best-First Search
 */
public class BestFirstSearch extends UniformCostSearch {

    /**
     * Constructs a Best-First Queue Controller with the given evaluation function
     * @param evalFunction the evaluation function to be used in the best-first search.
     */
    public BestFirstSearch(EvaluationFunction evalFunction) {
        super(new InformedNodeComparator(evalFunction));
    }
}
