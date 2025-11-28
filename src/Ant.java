public class Ant {
    public int[] tour;
    public boolean[] visited;
    public double tourLength;
    public int numCities;

    public Ant(int numCities) {
        this.numCities = numCities;
        this.tour = new int[numCities];
        this.visited = new boolean[numCities];
        reset();
    }

    // Reset ant for a new iteration
    public void reset() {
        for (int i = 0; i < numCities; i++) {
            visited[i] = false;
            tour[i] = -1;
        }
        tourLength = 0;
    }
}
