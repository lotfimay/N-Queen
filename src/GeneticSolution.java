import java.util.*;

public class GeneticSolution {



    private final int problemSize;
    private  int POPULATION_SIZE = 300;
    private  double MUTATION_RATE = 80;
    private  int SELECTION_FACTOR = 6;
    private  int ITERATIONS = 4000;

    private List<MetaNode> population;


    public GeneticSolution(int problemSize){
        this.problemSize = problemSize;
    }

    public NQueen resolve(){


        int iteration = 1;


        initPopulation(problemSize);
        NQueen bestSolution = getBestSolution();

        while(iteration <= ITERATIONS && bestSolution.getConflicts() > 0){

            List<MetaNode> parentsPopulation = clonePopulation();

            for(int i = 0 ; i < POPULATION_SIZE ; i+=2){
                List<MetaNode> parents = selectParents(parentsPopulation);
                List<MetaNode> children = crossover(parents);
                mutate(children);
                population.set(i , children.get(0));

                if( i != POPULATION_SIZE - 1){
                    population.set(i+1 , children.get(1));
                }
            }
            bestSolution = getBestSolution();
            iteration++;
        }
        return bestSolution;
    }

    private NQueen getBestSolution() {
        int maxScore = population.get(0).getFitness();
        NQueen bestSolution = population.get(0).getSolution();
        for(MetaNode solution : population){
            if(solution.getFitness() < maxScore){
                maxScore = solution.getFitness();
                bestSolution = solution.getSolution();
            }
        }
        return bestSolution;
    }


    public void initPopulation(int size){
        population = new ArrayList<>();
        for(int i = 0 ; i  < POPULATION_SIZE ; i++){
            NQueen solution = NQueen.getRandomSolution(size);
            population.add (new MetaNode(solution , solution.getConflicts()));
        }
    }

    private  List<MetaNode> selectParents(List<MetaNode> parentsPopulation) {
        List<MetaNode> parents = new ArrayList<>();


        Collections.sort(parentsPopulation);
        for(int i = 0 ; i < 2 ;i++){
            double rand = Math.random();
            int randIndex = (int)(POPULATION_SIZE * Math.pow(rand , SELECTION_FACTOR));
            parents.add (parentsPopulation.get(randIndex));
        }




        return parents;
    }


    private List<MetaNode> crossover(List<MetaNode> parents){
        List<MetaNode> children = new ArrayList<>();

        NQueen solution1 =  NQueen.getInstance(parents.get(0).getSolution());
        MetaNode firstChild = new MetaNode(solution1 , solution1.getConflicts());

        NQueen solution2 =  NQueen.getInstance(parents.get(1).getSolution());
        MetaNode secondChild = new MetaNode(solution2 , solution1.getConflicts());

        int crossPoint = Utils.generateRandomInt(0 , problemSize - 1);

        for(int i = crossPoint ; i < problemSize ; i++){
            firstChild.getSolution().getQueens()[i] = parents.get(1).getSolution().getQueens()[i];
            secondChild.getSolution().getQueens()[i] = parents.get(0).getSolution().getQueens()[i];
        }

        children.add(firstChild);
        children.add(secondChild);
        return children;
    }

    private void mutate(List<MetaNode> children){
        for(MetaNode child : children){
            int rand = (int) Math.round(Math.random() * 100);

            if(rand <= MUTATION_RATE){
                int randIndex = (int) Math.round(Math.random() * (problemSize - 1));
                child.getSolution().getQueens()[randIndex] = (int) Math.round(Math.random() * (problemSize));
            }
        }
    }




    private List<MetaNode> clonePopulation(){
        List<MetaNode> result = new ArrayList<>();
        for(MetaNode individual : population){
            NQueen solution = new NQueen(individual.getSolution().getQueens());
            result.add(new MetaNode(solution , solution.getConflicts()));
        }
        return result;
    }


    public void setParams(int POPULATION_SIZE , int MUTATION_RATE , int SELECTION_FACTOR , int ITERATIONS){
        this.POPULATION_SIZE = POPULATION_SIZE;
        this.MUTATION_RATE = MUTATION_RATE;
        this.SELECTION_FACTOR = SELECTION_FACTOR;
        this.ITERATIONS = ITERATIONS;
    }


}
