package queue_controllers;

import general.functions.EvaluationFunction;
import general.Node;

import java.util.Comparator;

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

    /**
     * A comparator for the values of evaluation function for different nodes.
     */
    private static class InformedNodeComparator implements Comparator<Node>
    {
        EvaluationFunction evalFunc;

        /**
         * Constructs a new informed comparator with the given evaluation function.
         * @param evalFunc
         */
        private InformedNodeComparator(EvaluationFunction evalFunc) {
            this.evalFunc = evalFunc;
        }

        /**
         * Compares two nodes based on the evaluation function.
         * @param x first node to be compared.
         * @param y second node to be compared.
         * @return comparison values based on natural order.
         */
        public int compare(Node x, Node y) { return evalFunc.apply(x) - evalFunc.apply(y); }
    }
}
