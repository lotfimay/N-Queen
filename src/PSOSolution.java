import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PSOSolution {

    private final int problemSize;

    private  int POPULATION_SIZE = 30;
    private  int ITERATIONS = 30;

    private  double G_BEST = 0.25;
    private  double P_BEST = 0.75;


    public PSOSolution(int problemSize) {
        this.problemSize = problemSize;
    }

    public void setParams(int ITERATIONS , int POPULATION_SIZE , double G_BEST , double P_BEST){
        this.ITERATIONS = ITERATIONS;
        this.POPULATION_SIZE = POPULATION_SIZE;
        this.G_BEST = G_BEST;
        this.P_BEST = P_BEST;

    }

    private List<Particle> population;


    public NQueen resolve(){

        initPopulation(problemSize);
        setGlobalBestSolution();

        int iteration = 1;

        while (iteration < ITERATIONS && Particle.getGlobalBestSolution().getFitness() > 0){

            for(Particle particle : population){

                double rand = Math.random();

                if(rand < G_BEST){
                    List<MetaNode> parents = new ArrayList<>();
                    parents.add(particle.getCurrentSolution());
                    parents.add(Particle.getGlobalBestSolution());
                    List<MetaNode> children = crossover(parents);
                    Collections.sort(children);
                    particle.setCurrentSolution(children.get(0));

                    applyLocalSearch(particle);

                    if(particle.getCurrentSolution().getFitness() < particle.getBestSolution().getFitness())
                        particle.setBestSolution(particle.getCurrentSolution());

                    if(particle.getBestSolution().getFitness() < Particle.getGlobalBestSolution().getFitness())
                        Particle.setGlobalBestSolution(particle.getBestSolution());

                }

                else if (rand < P_BEST){
                    List<MetaNode> parents = new ArrayList<>();
                    parents.add(particle.getCurrentSolution());
                    parents.add(particle.getBestSolution());
                    List<MetaNode> children = crossover(parents);
                    Collections.sort(children);
                    particle.setCurrentSolution(children.get(0));

                    applyLocalSearch(particle);

                    if(particle.getCurrentSolution().getFitness() < particle.getBestSolution().getFitness())
                        particle.setBestSolution(particle.getCurrentSolution());

                    if(particle.getBestSolution().getFitness() < Particle.getGlobalBestSolution().getFitness())
                        Particle.setGlobalBestSolution(particle.getBestSolution());
                }

                else {

                    NQueen newSolution = NQueen.getRandomSolution(problemSize);
                    MetaNode currentSolution = new MetaNode(newSolution , newSolution.getConflicts());
                    NQueen tmp = NQueen.getInstance(newSolution);
                    MetaNode bestSolution = new MetaNode(tmp , tmp.getConflicts());
                    Particle newParticle = new Particle(currentSolution , bestSolution);
                    List<MetaNode> parents = new ArrayList<>();
                    parents.add(particle.getCurrentSolution());
                    parents.add(newParticle.getCurrentSolution());
                    List<MetaNode> children = crossover(parents);
                    Collections.sort(children);
                    particle.setCurrentSolution(children.get(0));

                    applyLocalSearch(particle);

                    if(particle.getCurrentSolution().getFitness() < particle.getBestSolution().getFitness())
                        particle.setBestSolution(particle.getCurrentSolution());

                    if(particle.getBestSolution().getFitness() < Particle.getGlobalBestSolution().getFitness())
                        Particle.setGlobalBestSolution(particle.getBestSolution());

                }


            }

            iteration++;
        }

        return Particle.getGlobalBestSolution().getSolution();

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

    public void initPopulation(int size){
        population = new ArrayList<>();
        for(int i = 0 ; i  < POPULATION_SIZE ; i++){
            NQueen currentSolution = NQueen.getRandomSolution(size);
            MetaNode node = new MetaNode(currentSolution , currentSolution.getConflicts());
            Particle tmp = new Particle(node , node);
            population.add(tmp);
        }
    }

    public void setGlobalBestSolution(){
        MetaNode best = null;
        for(Particle particle : population){
            if(best == null)
                best = particle.getBestSolution();
            else if(particle.getBestSolution().getFitness() < best.getFitness())
                best = particle.getBestSolution();
        }
        Particle.setGlobalBestSolution(best);
    }


    public void applyLocalSearch(Particle particle){
        int[] solution = particle.getCurrentSolution().getSolution().getQueens();
        int size = solution.length;

        int current_fitness = particle.getCurrentSolution().getFitness();

        for(int i = 0 ; i < size ; i ++){

            int col = solution[i];

            for(int j = 1 ; j <= size ; j++){

                if(j != col){

                    solution[i] = j;

                    int new_fitness = particle.getCurrentSolution().getFitness();

                    if(new_fitness < current_fitness){
                        current_fitness = new_fitness;
                        break;
                    }else {
                        solution[i] = col;
                    }

                }
            }

        }
    }
}
