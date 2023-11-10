public class Node {

    private NQueen state;
    private int heuristic;
    private int cost;

    public Node(int size){
        this.state  = new NQueen(size);
        this.heuristic = 0;
        this.cost = 0;
    }

    public Node(NQueen state, int cost , int heuristic){
        this.state = state;
        this.heuristic = heuristic;
        this.cost = cost;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getF(){
        return this.heuristic + this.cost;
    }

    public NQueen getState() {
        return state;
    }

    @Override
    public String toString() {
        return this.getState().toString().concat(Integer.toString(getF()));
    }
}
