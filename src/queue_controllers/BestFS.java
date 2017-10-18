package queue_controllers;

import general.Node;

import java.util.Comparator;
import java.util.function.Function;

public class BestFS extends UCS {

    public BestFS(Function<Node, Integer> evalFunction) {
        super(new InformedNodeComparator(evalFunction));
    }

    private static class InformedNodeComparator<Node> implements Comparator<Node>
    {
        Function<Node, Integer> evalFunc;

        private InformedNodeComparator(Function<Node, Integer> evalFunc) {
            this.evalFunc = evalFunc;
        }

        public int compare(Node x, Node y)
        {
            return evalFunc.apply(x) - evalFunc.apply(y);
        }
    }
}
