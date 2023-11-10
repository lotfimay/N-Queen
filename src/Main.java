public class Main{

    public static void main(String[] args) {

        NQueen solution = new GeneticSolution(200).resolve();
        System.out.println("solution :" + solution);
        System.out.println("fitness :" + solution.getConflicts());
    }


    public static void fixingGAParams(){

        int iterations = 100;


        System.out.println("Problem size : 20 , Mutation rate : 80  , population size : 20 , iterations : 100");


        while(iterations <= 1000){

            GeneticSolution solver = new GeneticSolution(20);
            solver.setParams(300 , 80 , 15 , iterations);

            int perfect = 0;

            long average_execution_time = 0;


            int tests = 100;

            for(int i = 1 ; i <= tests ; i++){

                long start_time = System.currentTimeMillis();
                NQueen solution = solver.resolve();
                long end_time = System.currentTimeMillis();
                average_execution_time += (end_time - start_time);

                if(solution.getConflicts() == 0){
                    perfect++;
                }
            }
            double accuracy = ((double)perfect/tests)*100;
            average_execution_time/=tests;
            System.out.println("Iterations : " + iterations);
            System.out.println("Accuracy : "  + accuracy + "%");
            System.out.println("Average execution time : " + average_execution_time);
            System.out.println("===================================================");

            iterations+=100;
        }

    }


    public static void fixingPSOParams(){




        int iterations = 10;



        while(iterations <= 100){
            PSOSolution solver = new PSOSolution(20);
            solver.setParams(iterations , 40 , 0.25 , 0.75);

            int perfect = 0;

            long average_execution_time = 0;


            int tests = 50;

            for(int i = 1 ; i <= tests ; i++){

                long start_time = System.currentTimeMillis();
                NQueen solution = solver.resolve();
                long end_time = System.currentTimeMillis();
                average_execution_time += (end_time - start_time);

                if(solution.getConflicts() == 0){
                    perfect++;
                }
            }
            double accuracy = ((double)perfect/tests)*100;
            average_execution_time/=tests;
            System.out.println("Iterations :" + iterations);
            System.out.println("Accuracy : "  + accuracy + "%");
            System.out.println("Average execution time : " + average_execution_time);
            System.out.println("===================================================");

            iterations+=10;
        }

    }


}



