import java.util.*;

public class BFSolution {

    public static Statistics getSolution(NQueen problem) {

        long startTime = System.currentTimeMillis();
        Queue<NQueen> open  = new LinkedList<>();
        open.add(problem);


        int generated = 1;
        int developed = 0;
        int visited = 0;
        while(open.size()!=0){

            NQueen current = open.poll();
            visited++;
            if(current.isFinalState()){
                if(current.isValidNQueenSolution(1)){
                    long executionTime = System.currentTimeMillis() - startTime;
                    return new Statistics(current , generated , developed , visited , executionTime);
                }
            }else{
                developed++;
                List<NQueen> childNodes = current.getNextNodes();
                Collections.reverse(childNodes);
                generated+=childNodes.size();
                open.addAll(childNodes);
            }
        }

        return null;
    }

}
