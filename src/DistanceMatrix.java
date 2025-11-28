public class DistanceMatrix {
    public static double[][] computeEuclideanDistanceMatrix(double[][] coords) {
        int n = coords.length;
        double[][] dist = new double[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                double dx = coords[i][0] - coords[j][0];
                double dy = coords[i][1] - coords[j][1];
                dist[i][j] = Math.sqrt(dx * dx + dy * dy);
            }
        }
        return dist;
    }
}
