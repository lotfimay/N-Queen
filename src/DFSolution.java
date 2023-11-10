import java.util.*;

public class DFSolution {

    public static Statistics getSolution(NQueen problem){
        long startTime = System.currentTimeMillis();
        Stack<NQueen> open  = new Stack<>();
        open.push(problem);
        int generated = 1;
        int developed = 0;
        int visited = 0;
        while(!open.empty()){
            NQueen current = open.pop();
            visited++;
            if(current.isFinalState()){
                if(current.isValidNQueenSolution(1)){
                    long executionTime = System.currentTimeMillis() - startTime;
                    return new Statistics(current , generated , developed , visited , executionTime);
                }
            }else{
                developed++;
                List<NQueen> childNodes = current.getNextNodes();
                open.addAll(childNodes);
                generated+=childNodes.size();
            }
        }

        return null;
    }



}
