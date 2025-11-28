import java.util.Random;

public class MaxMinACO {

    private int numCities;
    private int numAnts;
    private double[][] pheromone;
    private double[][] distance;
    private Ant[] ants;

    // ACO parameters
    private double alpha = 1;
    private double beta = 5;
    private double evaporation = 0.5;
    private double tauMax = 1.0;
    private double tauMin = 0.0001;

    private int bestTour[];
    private double bestLength = Double.MAX_VALUE;

    private Random rand = new Random();

    public MaxMinACO(double[][] distance) {
        this.distance = distance;
        this.numCities = distance.length;
        this.numAnts = numCities;
        this.pheromone = new double[numCities][numCities];
        this.ants = new Ant[numAnts];

        for (int i = 0; i < numAnts; i++)
            ants[i] = new Ant(numCities);

        initPheromones();
    }

    // Initialize pheromone matrix
    private void initPheromones() {
        for (int i = 0; i < numCities; i++)
            for (int j = 0; j < numCities; j++)
                pheromone[i][j] = tauMax;
    }

    // Main ACO execution loop
    public void run(int iterations) {

        for (int iter = 0; iter < iterations; iter++) {
            buildSolutions();
            updatePheromones();
            System.out.println("Iteration: " + iter + " best length: " + bestLength);
        }

        System.out.println("=== FINAL BEST TOUR ===");
        for (int i = 0; i < numCities; i++)
            System.out.print(bestTour[i] + " ");
        System.out.println("\nTotal length: " + bestLength);
    }

    // Each ant builds a tour
    private void buildSolutions() {

        for (Ant ant : ants) {
            ant.reset();

            int start = rand.nextInt(numCities);
            ant.tour[0] = start;
            ant.visited[start] = true;

            for (int pos = 1; pos < numCities; pos++) {
                int last = ant.tour[pos - 1];
                int next = selectNextCity(last, ant.visited);
                ant.tour[pos] = next;
                ant.visited[next] = true;
            }

            computeTourLength(ant);

            if (ant.tourLength < bestLength) {
                bestLength = ant.tourLength;
                bestTour = ant.tour.clone();
            }
        }
    }

    // Select next city by probability rule
    private int selectNextCity(int current, boolean[] visited) {

        double[] probs = new double[numCities];
        double sum = 0.0;

        for (int j = 0; j < numCities; j++) {
            if (!visited[j]) {
                probs[j] = Math.pow(pheromone[current][j], alpha) *
                        Math.pow(1.0 / distance[current][j], beta);
                sum += probs[j];
            }
        }
        double r = rand.nextDouble();
        double cum = 0.0;

        for (int j = 0; j < numCities; j++) {
            if (!visited[j]) {
                cum += probs[j] / sum;
                if (cum >= r)
                    return j;
            }
        }
        // fallback
        for (int j = 0; j < numCities; j++)
            if (!visited[j]) return j;

        return -1;
    }

    // Compute length of a tour
    private void computeTourLength(Ant ant) {
        double length = 0;
        for (int i = 0; i < numCities - 1; i++)
            length += distance[ant.tour[i]][ant.tour[i + 1]];
        length += distance[ant.tour[numCities - 1]][ant.tour[0]];
        ant.tourLength = length;
    }

    // MAX–MIN pheromone update
    private void updatePheromones() {

        for (int i = 0; i < numCities; i++)
            for (int j = 0; j < numCities; j++)
                pheromone[i][j] *= (1 - evaporation);

        for (int i = 0; i < numCities - 1; i++) {
            int a = bestTour[i];
            int b = bestTour[i + 1];
            pheromone[a][b] += evaporation * (1.0 / bestLength);
            pheromone[b][a] += evaporation * (1.0 / bestLength);
        }

        normalizePheromones();
    }

    // Enforce tauMin <= tau <= tauMax
    private void normalizePheromones() {
        for (int i = 0; i < numCities; i++)
            for (int j = 0; j < numCities; j++) {
                if (pheromone[i][j] < tauMin) pheromone[i][j] = tauMin;
                else if (pheromone[i][j] > tauMax) pheromone[i][j] = tauMax;
            }
    }
}
