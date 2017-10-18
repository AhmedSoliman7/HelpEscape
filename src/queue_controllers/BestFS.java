package queue_controllers;

import general.functions.EvaluationFunction;
import general.Node;

import java.util.Comparator;

public class BestFS extends UCS {

    public BestFS(EvaluationFunction evalFunction) {
        super(new InformedNodeComparator(evalFunction));
    }

    private static class InformedNodeComparator implements Comparator<Node>
    {
        EvaluationFunction evalFunc;

        private InformedNodeComparator(EvaluationFunction evalFunc) {
            this.evalFunc = evalFunc;
        }

        public int compare(Node x, Node y)
        {
            return evalFunc.apply(x) - evalFunc.apply(y);
        }
    }
}
