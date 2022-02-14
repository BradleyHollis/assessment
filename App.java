import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        welcome();
        menuOption();
    }

    // Declare a global variable 'available rooms'
    public static int availableRooms;

    public static void welcome(){
        // Prompt the user with a welcome message
        System.out.println("Welcome to the Capsule-Capsule");
        System.out.println(("=").repeat(30));

        // User utilizes a scanner to punch in of available rooms
        Scanner console = new Scanner(System.in);
        int capacity;

        do {
            System.out.printf("Enter the number of capsules: ");
            capacity = console.nextInt();

        } while(capacity < 0);

        availableRooms = capacity;
    }

    public static void menuOption(){

        Scanner console = new Scanner(System.in);

        System.out.println();
        System.out.println("Guest Menu");
        System.out.println("=".repeat(11));

        // Present the options to navigate the application
        System.out.println("1. Check In");
        System.out.println("2. Check out");
        System.out.println("3. View Guests");
        System.out.println("4. Exit");

        int selection;
        String message = "";

        // Do while loop validates the user input
        do {
            message = "Choose an option [1-4]";
            System.out.println(message);

            selection = console.nextInt();

        } while(selection < 1 || selection > 4 || String.format("%d", selection).length() == 0);

        router(selection);
    }


    public static void router(int menuChoice) {
        if (menuChoice == 1) {
            checkIn();
        } else if (menuChoice == 2){
            checkOut(capsuleHotel);
        } else if (menuChoice == 3) {
            viewGuests(capsuleHotel);
        } else if (menuChoice == 4) {
            exit();
        }
    }

    public static String[][] capsuleHotel = new String[0][availableRooms];

    public static String[][] buildCapsules(){

        String[][] newArray = new String[1][availableRooms];

        for(int i = 0; i < newArray.length; i++){
            for(int j = 0; j < newArray[i].length; j++){
                if(newArray[i][j] == null){
                    newArray[i][j] = "unoccupied";
                }
            }
        }

        return newArray;
    }

    public static String[][] updateArray(String name, int roomNumber){

        String[][] guestList = buildCapsules();

        for(int i = 0; i < guestList.length; i++){
            for(int j = 0; j < guestList[i].length; j++){
                if(guestList[i][j] == guestList[0][roomNumber -1]){
                    guestList[0][roomNumber -1] = name;
                }
            }
        }

        capsuleHotel = guestList;
        menuOption();
        return guestList;
    }

    public static void checkIn(){
        System.out.println("Guest Check In");
        System.out.println("=".repeat(12));

        Scanner console = new Scanner(System.in);

        // do while variables
        String name;

        do{
            System.out.printf("Guest Name: ");
            name = console.nextLine();


        } while(name.isBlank());

        int roomSelection;

        do{
            System.out.printf("Capsule #[1-%s]: ", availableRooms);
            roomSelection = console.nextInt();

        } while(roomSelection < 1 && roomSelection > availableRooms);

        updateArray(name, roomSelection);
    }

    public static void checkOut(String[][] guestList){
        System.out.println("Guest Check Out");
        System.out.println("=".repeat(15));
        System.out.printf("Capsule #[1-%s]: ", availableRooms);

        Scanner console = new Scanner(System.in);

        // do while variables
        int roomSelection;
        String vacancy = "unoccupied";
        boolean isOccupied = true;
        String name = "";
        String message = "";

        do{
            System.out.printf("Capsule # [1-%s]: ", availableRooms);
            roomSelection = console.nextInt();

            for(int i = 0; i <= guestList.length; i++){
                name = guestList[0][roomSelection - 1];
                if(name == "unoccupied" || name == null){
                    message = "This room is currently unoccupied";
                    isOccupied = false;
                } else if(name != "unoccupied"){
                    message = name + " has been checked out from capsule " + roomSelection;
                    isOccupied = true;
                }
            }

            System.out.println(message);

        } while(roomSelection < 1 || roomSelection > availableRooms || !isOccupied);

        // Buffer
        System.out.println();

        updateArray(vacancy, roomSelection);
    }

    public static void viewGuests(String[][] masterArr){
        System.out.println();

        System.out.println("View Guests");
        System.out.println("=".repeat(12));
        System.out.println();

        Scanner console = new Scanner(System.in);
        int roomNumber;
        do{
            System.out.println(String.format("Capsule #[1-%s]", availableRooms));
            roomNumber = console.nextInt();
        } while(roomNumber < 1 && roomNumber > availableRooms);

        String[][] hotel = masterArr;

        String unoccupied = "unoccupied";

        System.out.println("Capsule: Guest ");

        for(int i = 0; i < hotel.length; i++){
            for(int j = 0; j < hotel[i].length; j++){
                if(j >= (roomNumber - 5) && j <= (roomNumber + 5)){
                    System.out.println((j + 1) + ":" + " " + hotel[i][j]);
                }
            }
        }

        menuOption();
    }

    public static void exit(){
        System.out.println();

        System.out.println("Are you sure you want to exit? ");
        System.out.println("All data will be lost.");

        Scanner console = new Scanner(System.in);
        String decision;

        do {
            System.out.println("Exit [y/n]: ");

            decision = console.nextLine();
        } while(decision.isBlank() && !decision.equalsIgnoreCase("Y") && !decision.equalsIgnoreCase("N"));

        if(decision.equalsIgnoreCase("N")){
            menuOption();
        } else System.out.println("Good Bye!");
    }
}