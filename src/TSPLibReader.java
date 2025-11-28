import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TSPLibReader {

    // Reads a TSPLIB file with NODE_COORD_SECTION format
    public static double[][] readCoordinates(String filename) {
        double[][] coords = new double[52][2];
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
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

        } catch (IOException e) {
            System.out.println("Error reading TSPLIB file: " + e.getMessage());
        }
        return coords;
    }
}
