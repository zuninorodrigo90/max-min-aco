public class Main {

    public static void main(String[] args) {

        // Load node coordinates from TSPLIB file
        double[][] coords = TSPLibReader.readCoordinates("resources/berlin52.tsp");

        // Compute distance matrix
        double[][] distance = DistanceMatrix.computeEuclideanDistanceMatrix(coords);

        // Instantiate MAX–MIN ACO solver
        MaxMinACO aco = new MaxMinACO(distance);

        // Run for 300 iterations
        aco.run(100);
    }
}
