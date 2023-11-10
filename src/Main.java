import java.util.List;

public class Main{

    public static void main(String[] args) {


        NQueen problem = new NQueen(4);


        List<NQueen> solutions = DFSolution.getSolutions(problem);
        System.out.println("solutions :" + solutions);

    }





}



