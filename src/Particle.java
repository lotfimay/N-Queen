public class Particle {
    private MetaNode currentSolution;
    private MetaNode bestSolution;
    private static MetaNode globalBestSolution;







    public Particle(MetaNode currentSolution, MetaNode bestSolution) {
        this.currentSolution = currentSolution;
        this.bestSolution = bestSolution;
    }

    public MetaNode getCurrentSolution() {
        return currentSolution;
    }

    public void setCurrentSolution(MetaNode currentSolution) {
        this.currentSolution = currentSolution;
    }

    public MetaNode getBestSolution() {
        return bestSolution;
    }

    public void setBestSolution(MetaNode bestSolution) {
        this.bestSolution = bestSolution;
    }


    public static MetaNode getGlobalBestSolution() {
        return globalBestSolution;
    }

    public static void setGlobalBestSolution(MetaNode globalBestSolution) {
        Particle.globalBestSolution = globalBestSolution;
    }

}
