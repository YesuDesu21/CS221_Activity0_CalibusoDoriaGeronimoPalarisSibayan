import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        DataManager swimmingDataManager = new DataManager();
        swimmingDataManager.setReferenceListFromCSV("data.csv");
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome User to our Data Processing Program!");

        System.out.println("Choose your option.");
        while(true){
            try {

                System.out.println("""
                        Options:
                        1. Search by Athlete Name
                        2. Display list of swimmers
                        3. Filter by rank
                        4. Sort by Year
                        5. Top 5 fastest Results per Event
                        """);
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                System.out.println();
                if(choice < 0){
                    System.out.println("Input a positive number...");
                }
                switch(choice){
                    case 1:
                        swimmingDataManager.searchByName();
                        break;
                    case 2:
                        swimmingDataManager.displayListOfSwimmers();
                        break;
                    case 3:
                        swimmingDataManager.filterByRank();
                        break;
                    case 4:
                        //method 4
                        System.out.println("4");
                        break;
                    case 5:
                        swimmingDataManager.filterByTop5Events();
                        break;
                }
                System.out.println("Would you like to choose another option?[y/n]:");
                String res = sc.next();
                if(res.equalsIgnoreCase("n")){
                    System.out.println("Thank you for using the program. Goodbye!");
                    break;
                } else if (res.equalsIgnoreCase("y")) {
                    System.out.println("You can choose another option...");
                }
            }catch(NumberFormatException e){
                System.out.println("Your choice is invalid, please input a number...");
            }
        }//End of while loop
    }
}
