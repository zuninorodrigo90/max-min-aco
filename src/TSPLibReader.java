import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TSPLibReader {

    // Reads a TSPLIB file with NODE_COORD_SECTION format
    public static double[][] readCoordinates(String filename) {
        int dimension = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            // first scan → find DIMENSION
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("DIMENSION")) {
                    String[] parts = line.split(":");
                    dimension = Integer.parseInt(parts[1].trim());
                    break;
                }
            }
            br.close();

            double[][] coords = new double[dimension][2];

            // second scan → read coordinates
            br = new BufferedReader(new FileReader(filename));
            boolean coordSection = false;
            int index = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.equals("NODE_COORD_SECTION")) {
                    coordSection = true;
                    continue;
                }
                if (line.equals("EOF")) {
                    break;
                }
                if (coordSection) {
                    String[] parts = line.split("\\s+");
                    if (parts.length >= 3) {
                        coords[index][0] = Double.parseDouble(parts[1]);
                        coords[index][1] = Double.parseDouble(parts[2]);
                        index++;
                    }
                }
            }
            br.close();

            return coords;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
