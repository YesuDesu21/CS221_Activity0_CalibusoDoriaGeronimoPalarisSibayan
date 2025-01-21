import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class DataManager {

    //Scanner para di na tawagin paulit-ulit.
    private final Scanner scanner = new Scanner(System.in);

    //Eto yung referenceList na manggagaling sa csv file. pwede din siya palitan manually gamit yung setReferenceList().
    private List<DataInformation> referenceList = new ArrayList<>();

    /**
     * Converts a csv file into a List.
     * @param fileName specifies the name or path of the file to be converted.
     */

    public void setReferenceListFromCSV(String fileName) {
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
                DataInformation data = new DataInformation();
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
                referenceList.add(data);
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * Returns the reference list used for data processing
     * @return a list containing objects of type DataInformation
     */
    public List<DataInformation> getReferenceList() {
        return referenceList;
    }

    /**
     * Sets the reference list
     * @param referenceList specifies the List<DataInformation> to be set as the reference list.
     */
    public void setReferenceList(List<DataInformation> referenceList) {
        this.referenceList = referenceList;
    }

    /**
     * Searches an athlete by their name and prints out the result via the command line interface.
     */
    public void searchByName(){
        // Get user input
        System.out.print("Enter the athlete's name: ");
        String athleteName = scanner.nextLine();

        // Find and display the athlete's data
        boolean athleteFound = false;
        for (DataInformation data : referenceList) {
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

    }

    /**
     * Prompts the user to select a distance and display the 5 fastest times for that distance.
     */
    public void filterByTop5Events(){

        //HashSet cannot contain duplicates
        HashSet<String> distances = new HashSet<>();

        //Adds the distances to the HashSet, ignoring duplicates
        for (DataInformation data : referenceList) {
            distances.add(data.getDistance());
        }

        for (String distance : distances) {
            System.out.println("1. Distance: " + distance);
        }

        //Ask for the user's input
        String input;

        do {
            System.out.print("Enter your choice from the provided distances: ");
            input = scanner.nextLine();

            if(!distances.contains(input))
                System.out.println("Invalid input!");

        }while (!distances.contains(input));

        //Filter and sort little bro
        String finalInput = input;
        List<DataInformation> filteredDataList = new ArrayList<>(referenceList.stream().filter(i -> i.getDistance().equals(finalInput)).toList());
        filteredDataList.sort(Comparator.comparing(DataInformation::getDistance));

        //Print results
        for(int i = 0; i < filteredDataList.size(); i++){
            if(i == 5)
                break;
            System.out.println(filteredDataList.get(i).toString());
        }

    }

    /**
     * Filters the list by rank and outputs the filtered list
     */
    public void filterByRank() {
        // absolute path for the input file
        String inputFile = "data.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the rank you want to filter: ");
            int targetRank = scanner.nextInt();

            String header = br.readLine();
            if (header != null) {
                System.out.println(header);
            }

            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                String rankStr = columns[columns.length - 1];
                try {
                    int rank = Integer.parseInt(rankStr.trim());

                    if (rank == targetRank) {
                        System.out.println(line);
                        found = true;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid rank: " + rankStr);
                }
            }

            if (!found) {
                System.out.println("No rows found for rank " + targetRank);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Convert a time string (e.g., "2:15") to seconds
    private static double parseResult(String result) {
        try {
            if (result.contains(":")) {
                String[] parts = result.split(":");

                //Checks if the minutes half is 0 and ignores the parser
                if(parts[0].equals("0"))
                    //If the time is 0:03:65, this will just format it to 03.65
                    return Double.parseDouble(parts[1] + "." + parts[2]);

                double minutes = Double.parseDouble(parts[0]);
                double seconds = Double.parseDouble(parts[1]);
                return minutes * 60.00 + seconds;

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