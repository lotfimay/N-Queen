public class Statistics {
    private NQueen solution;
    private int generatedNodes;
    private int developedNodes;
    private int visitedNodes;
    private  long executionTime;

    public Statistics(NQueen solution, int generatedNodes, int developedNodes, int visitedNodes , long executionTime) {
        this.solution = solution;
        this.generatedNodes = generatedNodes;
        this.developedNodes = developedNodes;
        this.visitedNodes = visitedNodes;
        this.executionTime = executionTime;
    }

    public NQueen getSolution() {
        return solution;
    }

    public int getGeneratedNodes() {
        return generatedNodes;
    }

    public int getDevelopedNodes() {
        return developedNodes;
    }

    public int getVisitedNodes() {
        return visitedNodes;
    }

    @Override
    public String toString() {
        String result = "solution : ";
        result = result.concat(this.solution.toString().concat("\n"));
        result = result.concat("generated nodes : ".concat(Integer.toString(this.generatedNodes).concat("\n")));
        result = result.concat("developed nodes : ".concat(Integer.toString(this.developedNodes).concat("\n")));
        result = result.concat("visited nodes : ".concat(Integer.toString(this.visitedNodes).concat("\n")));
        result = result.concat("execution time : ".concat(Long.toString(this.executionTime).concat("\n")));
        return result;
    }
}
