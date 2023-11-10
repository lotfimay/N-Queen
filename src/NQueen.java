import java.util.*;
import java.util.stream.IntStream;

public class NQueen {

    private int[] queens;

    public NQueen(int  n){
        this.queens = new int[n];
        IntStream.range(0 , n)
                .forEach(index -> this.queens[index] = -1);
    }
    public NQueen(int[] queens){
        this.queens = Arrays.copyOf(queens , queens.length);
    }
    public static NQueen getInstance(NQueen problem){
        return new NQueen(problem.getQueens());
    }


    public void placeAQueen(int line , int column){
           this.queens[line] = column;
    }


    public int getTheFirstEmpty(){
        for(int i = 0;i < this.queens.length ; i++){
            if(this.queens[i] == -1)
                return i;
        }
        return -1;
    }



    public  boolean isValidNQueenSolution() {
        int n = this.getTheFirstEmpty() == -1 ? this.queens.length : this.getTheFirstEmpty();


        // Check if each queen is in a different column
        Set<Integer> columns = new HashSet<>();
        for (int i = 0 ; i < n ; i++) {
            if (!columns.add(this.queens[i])) {
                return false;
            }
        }

        // Check if no two queens are on the same diagonal

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(this.queens[i] - this.queens[j]) == Math.abs(i - j)) {
                        return false;
                }
            }
            }
        return true;
    }

    public boolean isFinalState(){
        return this.getTheFirstEmpty() == -1;
    }

    public  List<NQueen> getNextNodes(){
        List<NQueen> result = new ArrayList<>();
        int index = this.getTheFirstEmpty();


        for(int i = this.queens.length ; i >= 1 ; i--){
            NQueen tmp = NQueen.getInstance(this);
            tmp.placeAQueen(index , i);
            result.add(tmp);
        }
        return result;
    }

    public int[] getQueens() {
        return queens;
    }

    public void setQueens(int[] queens) {
        this.queens = queens;
    }











    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for(int i = 0;i < this.queens.length ; i++){
            if(i == this.queens.length - 1)
                result.append(this.queens[i]).append("]");
            else result.append(this.queens[i]).append(",");
        }
        return result.toString();
    }
}
