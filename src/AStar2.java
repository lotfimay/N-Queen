import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class AStar2 {
    public static Statistics getSolution(NQueen problem){

        long startTime = System.currentTimeMillis();

        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        open.add(new Node(problem , 0 , 0));

        int generated = 1;
        int developed = 0;
        int visited = 0;

        while (open.size() != 0){
            Node current = open.poll();
            visited++;
            if(current.getState().isFinalState()){
                long executionTime = System.currentTimeMillis() - startTime;
                 if (current.getState().isValidNQueenSolution(1)){
                     return  new Statistics(current.getState() , generated , developed , visited , executionTime);
                 }else return null;
            }else{
                developed++;
                List<NQueen> nextStates = current.getState().getNextNodes();
                if(nextStates != null){
                    generated+= nextStates.size();
                    for(NQueen state : nextStates){

                        int cost = g(state);
                        int heuristic = h(state);
                        Node tmp = new Node(state , cost , heuristic);
                        open.offer(tmp);
                    }
                }
            }

        }

        return null;
    }

    public static int h(NQueen state) {
        return state.getThreateningQueens();
    }

    public static int g(NQueen state){
        return 0;
    }
}
