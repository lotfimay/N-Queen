public class MetaNode implements Comparable<MetaNode> {
    private NQueen solution;
    private int fitness;

    public MetaNode(NQueen solution , int fitness){
        this.solution = solution;
        this.fitness = fitness;
    }

    public NQueen getSolution() {
        return solution;
    }

    public void setSolution(NQueen solution) {
        this.solution = solution;
    }

    public int getFitness() {
        return solution.getConflicts();
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    @Override
    public int compareTo(MetaNode o) {
        return Integer.compare(this.fitness , o.fitness);
    }

    @Override
    public String toString() {
        return solution.toString();
    }
}
