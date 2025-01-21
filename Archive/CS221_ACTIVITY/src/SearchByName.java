import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchByName {
    public static void main(String[] args) {
        // List to store data from the CSV file
        List<DataInformation<String>> dataList = new ArrayList<>();
        String fileName = "data.csv";

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                // Split the row into individual values
                String[] values = line.split(",");
                if (values.length != 10) {
                    System.out.println("Skipping invalid row: " + line);
                    continue;
                }

                // Clean up values and create a new DataInformation object
                DataInformation<String> data = new DataInformation<>(values[0].trim());
                data.setLocation(values[0].trim());
                data.setYear(parseInt(values[1].trim()));
                data.setDistance(values[2].trim());
                data.setStroke(values[3].trim().isEmpty() ? "N/A" : values[3].trim());
                data.setRelay(values[4].trim().isEmpty() ? "N/A" : values[4].trim());
                data.setGender(values[5].trim());
                data.setTeam(values[6].trim());
                data.setAthleteName(values[7].trim());
                data.setResult(parseResult(values[8].trim()));
                data.setRank(parseInt(values[9].trim()));

                // Add the data to the list
                dataList.add(data);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the athlete's name: ");
        String athleteName = scanner.nextLine();

        // Find and display the athlete's data
        boolean athleteFound = false;
        for (DataInformation<String> data : dataList) {
            if (data.getAthleteName().equalsIgnoreCase(athleteName)) {
                System.out.println("Athlete found:");
                System.out.println(data);
                athleteFound = true;
                break;
            }
        }

        if (!athleteFound) {
            System.out.println("Athlete not found in the dataset.");
        }

        scanner.close();
    }

    // Convert a time string (e.g., "2:15") to seconds
    private static double parseResult(String result) {
        try {
            if (result.contains(":")) {
                String[] parts = result.split(":");
                double minutes = Double.parseDouble(parts[0]);
                double seconds = Double.parseDouble(parts[1]);
                return minutes * 60 + seconds;
            } else {
                return Double.parseDouble(result);
            }
        } catch (NumberFormatException e) {
            return 0.0; // Default to 0 if the result is invalid
        }
    }

    // Safely parse integers, using a default value if parsing fails
    private static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1; // Use -1 as a default for invalid numbers
        }
    }
}
