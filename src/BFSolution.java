import java.util.*;

public class BFSolution {

    public static List<NQueen> getSolutions(NQueen problem) {

        List<NQueen> result = new ArrayList<>();

        Queue<NQueen> open  = new LinkedList<>();
        open.add(problem);



        while(!open.isEmpty()){

            NQueen current = open.poll();
            if(current.isFinalState()){
                if(current.isValidNQueenSolution()){
                    result.add(current);
                }
            }else{
                List<NQueen> childNodes = current.getNextNodes();
                Collections.reverse(childNodes);
                open.addAll(childNodes);
            }
        }

        return result;
    }

}
