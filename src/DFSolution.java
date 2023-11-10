import java.util.*;

public class DFSolution {

    public static List<NQueen> getSolutions(NQueen problem){
        List<NQueen> result = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        Stack<NQueen> open  = new Stack<>();
        open.push(problem);
        while(!open.empty()){
            NQueen current = open.pop();
            if(current.isFinalState()){
                if(current.isValidNQueenSolution()){
                    long executionTime = System.currentTimeMillis() - startTime;
                    result.add(current);
                }
            }else{
                List<NQueen> childNodes = current.getNextNodes();
                open.addAll(childNodes);
            }
        }

        return result;
    }



}
