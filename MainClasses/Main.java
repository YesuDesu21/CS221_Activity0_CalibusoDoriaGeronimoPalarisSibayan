import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataManager swimmingDataManager = new DataManager();
        swimmingDataManager.setReferenceListFromCSV("data.csv");
        swimmingDataManager.filterByRank();
        swimmingDataManager.searchByName();
        swimmingDataManager.filterByTop5Events();
    }
}
