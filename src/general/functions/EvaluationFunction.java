package general.functions;

import general.Node;

public abstract class EvaluationFunction {

    protected HeuristicFunction heurFunc;

    public EvaluationFunction(HeuristicFunction heurFunc) { this.heurFunc = heurFunc; }

    public abstract Integer apply(Node node);
}
