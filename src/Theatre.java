import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
/*
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
*/


public class Theatre extends Ticket {
    public static ArrayList<Ticket> infoArrayList = new ArrayList<>();   // Arraylist where all the ticket information stored
    private static String destination;
    public Theatre(int row, int seat, double price, Person person, String destination) {
        super(row, seat, price, person, destination);
    }
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\nWelcome to the New Theatre!");
        Scanner scan1 = new Scanner(System.in);
        Ticket ticket1 = new Ticket(1, 1, 1, new Person("", "", ""),"");
        // Setting up 3 arrays for the 3 rows
        int[] seatsRow1 = new int[12];
        int[] seatsRow2 = new int[16];
        int[] seatsRow3 = new int[20];

        boolean exit1 = true;
        while (exit1) {
            System.out.println("\n-------------------------------------------------------");
            System.out.println("\nPlease select an option:");
            System.out.println("Press 1) to Buy a ticket");
            System.out.println("Press 2) to Print seating area");
            System.out.println("Press 3) to Cancel ticket");
            System.out.println("Press 4) to List available seats");
            System.out.println("Press 5) to Save to file");
            System.out.println("Press 6) to Load from file");
            System.out.println("Press 7) to Print ticket information and total price");
            System.out.println("Press 8) to Sort tickets by price");
            System.out.println("   Press 0) to Quit");
            System.out.println("\n-------------------------------------------------------");
            System.out.println("Enter option: ");
            Scanner input = new Scanner(System.in);
            //boolean exit2 = true;
            while (true) {
                if (input.hasNextInt()) {    // Checking whether the input is an integer
                    int input1 = input.nextInt();
                    if (0 <= input1 && input1 < 9) {  // Validating the input is within the range
                        switch (input1) {
                            case 0:
                                exit1 = false;
                                System.out.println("Thank you for using the theatre booking system!");
                                break;
                            case 1:
                                System.out.println("Option 1: Buy a ticket");
                                buyTicket(seatsRow1, seatsRow2, seatsRow3, scan1, ticket1);
                                break;
                            case 2:
                                System.out.println("Option 2: Print seating area");
                                printSeatingArea(seatsRow1, seatsRow2, seatsRow3);
                                break;
                            case 3:
                                System.out.println("Option 3: Cancel ticket");
                                cancelTicket(seatsRow1, seatsRow2, seatsRow3, infoArrayList);
                                break;
                            case 4:
                                System.out.println("Option 4: List available seat");
                                showAvailable(seatsRow1, seatsRow2, seatsRow3);
                                break;
                            case 5:
                                System.out.println("Option 5: Save to the file");
                                save(seatsRow1, seatsRow2, seatsRow3);
                                break;
                            case 6:
                                System.out.println("Option 6: Load from the file");
                                loadFromFile(seatsRow1, seatsRow2, seatsRow3);
                                break;
                            case 7:
                                System.out.println("Option 7: Print ticket information and total price");
                                showTicketsInfo(ticket1);
                                break;
                            case 8:
                                System.out.println("Option 8: Sort tickets by price");
                                sortTickets(infoArrayList, destination);
                                break;
                        }
                        break;
                    } else {
                        System.out.println("Enter a valid integer between 0 to 8");
                        break;
                    }
                } else {
                    System.out.println("Enter a valid input");
                    break;
                }
            }
        }
    }

    public static void buyTicket(int[] seatsRow1, int[] seatsRow2, int[] seatsRow3, Scanner scan1, Ticket ticket1) {  // Buy ticket method
        Scanner inputSeat = new Scanner(System.in);
        Scanner inputRow = new Scanner(System.in);
        int row, seat;
        boolean exit4 = true;
        while (true) {
            System.out.println("Please enter the row number (1-3):");
            if (inputRow.hasNextInt()) {
                row = inputRow.nextInt() - 1;
                if (row < 0 || row > 2) {
                    System.out.println("Out of range");
                    continue;
                }
                break;
            } else {
                System.out.println("Enter a valid integer input");
                inputRow.next();
            }
        }
        while (true) {
            System.out.println("Please enter the seat number:");
            if (inputSeat.hasNextInt()) {
                seat = inputSeat.nextInt() - 1;
                if (row == 0 && (seat < 0 || seat > 11)) {
                    System.out.println("Out of range");
                    continue;
                } else if (row == 1 && (seat < 0 || seat > 15)) {
                    System.out.println("Out of range");
                    continue;
                } else if (row == 2 && (seat < 0 || seat > 19)) {
                    System.out.println("Out of range");
                    continue;
                }
                break;
            } else {
                System.out.println("Enter a valid integer input");
                inputSeat.next();
            }
        }
        if (row == 0) {
            if (seatsRow1[seat] == 1) {
                System.out.println("Already reserved");
                exit4 = false;
            } else {
                seatsRow1[seat] = 1;
            }
        } else if (row == 1) {
            if (seatsRow2[seat] == 1) {
                System.out.println("Already reserved");
                exit4 = false;
            } else {
                seatsRow2[seat] = 1;
            }
        } else if (row == 2) {
            if (seatsRow3[seat] == 1) {
                System.out.println("Already reserved");
                exit4 = false;
            } else {
                seatsRow3[seat] = 1;
            }
        }
        //Getting user inputs for name, surname, email and price and validating them
        while (exit4) {
            System.out.print("Enter the name: ");
            String name = "";
            while (name.equals("")) {
                try {
                    name = scan1.next();
                } catch (Exception e) {
                    System.out.print("Enter a valid input: ");
                    scan1.nextLine();
                }
            }
            System.out.print("Enter the surname: ");
            String surname = "";
            while (surname.equals("")) {
                try {
                    surname = scan1.next();
                } catch (Exception e) {
                    System.out.print("Enter a valid input: ");
                    scan1.nextLine();
                }
            }
            System.out.print("Enter the price: ");
            double price = 0.0;
            while (price == 0.0) {
                try {
                    price = scan1.nextDouble();
                } catch (Exception e) {
                    System.out.print("Enter a valid input: ");
                    scan1.nextLine();
                }
            }
            System.out.print("Enter the email: ");
            String email = "";
            while (email.equals("")) {
                try {
                    email = scan1.next();
                } catch (Exception e) {
                    System.out.print("Enter a valid input: ");
                    scan1.nextLine();
                }
            }
            System.out.println("Seat reservation successful");
            //Saving user inputs for name, surname, email and price to the arraylist

            Person person = new Person(name, surname, email);
            String destination = "";
            Ticket ticket2 = new Ticket(row, seat, price, person, destination);
            infoArrayList.add(ticket2);
            break;
        }
    }

    public static void printSeatingArea(int[] seatsRow1, int[] seatsRow2, int[] seatsRow3) {  //  Print seating area method
        System.out.println("Seating area:");
        System.out.println("   ***************");
        System.out.println("   *    STAGE    *");
        System.out.println("   ***************");
        System.out.print("    ");
        for (int i = 0; i < 6; i++) {
            if (seatsRow1[i] == 1) {
                System.out.print("X"); // occupied seat
            } else {
                System.out.print("O"); // free seat
            }
        }
        System.out.print(" ");
        for (int i = 6; i < seatsRow1.length; i++) {
            if (seatsRow1[i] == 1) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }
        System.out.println(); // move to next row
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            if (seatsRow2[i] == 1) {
                System.out.print("X"); // occupied seat
            } else {
                System.out.print("O"); // free seat
            }
        }
        System.out.print(" ");
        for (int i = 8; i < seatsRow2.length; i++) {
            if (seatsRow2[i] == 1) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }
        System.out.println(); // move to next row
        for (int i = 0; i < 10; i++) {
            if (seatsRow3[i] == 1) {
                System.out.print("X"); // occupied seat
            } else {
                System.out.print("O"); // free seat
            }
        }
        System.out.print(" ");
        for (int i = 10; i < seatsRow3.length; i++) {
            if (seatsRow3[i] == 1) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }
    }

    public static void cancelTicket(int[] seatsRow1, int[] seatsRow2, int[] seatsRow3, ArrayList<Ticket> infoArrayList) {  // Cancel ticket method
        System.out.println("Please enter the row number (1-3):");
        Scanner inputRow = new Scanner(System.in);

        boolean exit3 = true;
        while (exit3) {
            if (inputRow.hasNextInt()) {
                int row = inputRow.nextInt() - 1; // subtract 1 to convert to 0-based indexing
                if (0 <= row && row <= 2) {
                    exit3 = false;
                    System.out.println("Please enter the seat number:");
                    Scanner inputSeat = new Scanner(System.in);
                    if (inputSeat.hasNextInt()) {
                        int seat = inputSeat.nextInt() - 1; // subtract 1 to convert to 0-based indexing
                        if (row == 0) {
                            if (seat < 0 || seat > 11) {
                                System.out.println("Seat number out of range. Please enter a valid seat number (1-12).");
                            } else if (seatsRow1[seat] == 0) {
                                System.out.println("Seat already available");
                            } else {
                                seatsRow1[seat] = 0;
                                System.out.println("Seat cancellation successful");
                                // removes the corresponding element from infoArrayList
                                for (int i = 0; i<infoArrayList.size(); i++){
                                    if ((row +1 == infoArrayList.get(i).getRow()) && seat +1 == infoArrayList.get(i).getSeat()){
                                        infoArrayList.remove(i);
                                    }
                                }
                            }
                        } else if (row == 1) {
                            if (seat < 0 || seat > 15) {
                                System.out.println("Seat number out of range. Please enter a valid seat number (1-16).");
                            } else if (seatsRow2[seat] == 0) {
                                System.out.println("Seat already available");
                            } else {
                                seatsRow2[seat] = 0;
                                System.out.println("Seat cancellation successful");
                                // removes the corresponding element from infoArrayList
                                for (int i = 0; i<infoArrayList.size(); i++){
                                    if ((row +1 == infoArrayList.get(i).getRow()) && seat +1 == infoArrayList.get(i).getSeat()){
                                        infoArrayList.remove(i);
                                    }
                                }
                            }
                        } else if (row == 2) {
                            if (seat < 0 || seat > 19) {
                                System.out.println("Seat number out of range. Please enter a valid seat number (1-20).");
                            } else if (seatsRow3[seat] == 0) {
                                System.out.println("Seat already available");
                            } else {
                                seatsRow3[seat] = 0;
                                System.out.println("Seat cancellation successful");
                                // removes the corresponding element from infoArrayList
                                for (int i = 0; i<infoArrayList.size(); i++){
                                    if ((row +1 == infoArrayList.get(i).getRow()) && seat +1 == infoArrayList.get(i).getSeat()){
                                        infoArrayList.remove(i);
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Please enter a valid seat number");
                    }
                } else {
                    System.out.println("Enter a valid row number between 1 to 3");
                }
            } else {
                System.out.println("Please enter a valid row number");
                inputRow.next();
            }
        }
    }

    public static void showAvailable(int[] seatsRow1, int[] seatsRow2, int[] seatsRow3) {  // Show available seats method
        System.out.print("Seats available in row " + (1) + ": ");
        boolean first = true;
        for (int i = 0; i < seatsRow1.length; i++) {
            if (seatsRow1[i] == 0) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(i + 1);
                first = false;
            }
        }
        System.out.println(".");
        System.out.print("Seats available in row " + (2) + ": ");
        boolean second = true;
        for (int i = 0; i < seatsRow2.length; i++) {
            if (seatsRow2[i] == 0) {
                if (!second) {
                    System.out.print(", ");
                }
                System.out.print(i + 1);
                second = false;
            }
        }
        System.out.println(".");
        System.out.print("Seats available in row " + (3) + ": ");
        boolean third = true;
        for (int i = 0; i < seatsRow3.length; i++) {
            if (seatsRow3[i] == 0) {
                if (!third) {
                    System.out.print(", ");
                }
                System.out.print(i + 1);
                third = false;
            }
        }
        System.out.println(".");
    }

    public static void save(int[] seatsRow1, int[] seatsRow2, int[] seatsRow3) {   // Saving to the text file method
        File file = new File("theatrebookings.txt");
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);  //Initializing the writer
            writer.write("Row 1 : " + Arrays.toString(seatsRow1));
            writer.write("\nRow 2 : " + Arrays.toString(seatsRow2));
            writer.write("\nRow 3 : " + Arrays.toString(seatsRow3));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully saved to the file theatrebookings.txt");
    }

    public static void loadFromFile(int[] seatsRow1, int[] seatsRow2, int[] seatsRow3) throws FileNotFoundException {   // load from the save file method
        String line1 = "", line2 = "", line3 = "";
        try (BufferedReader br = new BufferedReader(new FileReader("theatrebookings.txt"))) {
            line1 = br.readLine();
            line2 = br.readLine();
            line3 = br.readLine();
            int j = 0;
            for (int i = 9; i <= 43; i += 3) {
                seatsRow1[j] = Character.getNumericValue(line1.charAt(i));  // Reading the row number one of the save file
                j++;
            }
            j = 0;
            for (int i = 9; i <= 55; i += 3) { // The index from where to where the file reader interprets
                seatsRow2[j] = Character.getNumericValue(line2.charAt(i));
                j++;
            }
            j = 0;
            for (int i = 9; i <= 67; i += 3) {
                seatsRow3[j] = Character.getNumericValue(line3.charAt(i));
                j++;
            }
            System.out.println("Successfully retrieved data from the savefile 'theatrebookings.txt'");
        } catch (IOException e) {
            System.out.println("Error while reading the save file");
        }
    }

    public static void showTicketsInfo(Ticket ticket1) {  // Show tickets info method
        double totalPrice = 0;
        for (int i = 0; i < infoArrayList.size(); i++) {
            Ticket out = infoArrayList.get(i);
            out.print();
            totalPrice += out.getPrice(); // Add the current ticket price to total price
        }
        System.out.println("Total price: " + totalPrice);
    }

    public static void sortTickets(ArrayList<Ticket> infoArrayList, String destination) {   // Sort ticket method using selection sort method
        int minIndex;
        Ticket temp;
        ArrayList<Ticket> sorted_tickets = new ArrayList<Ticket>(infoArrayList);

        for (int start = 0; start < sorted_tickets.size() - 1; start++) {
            minIndex = start;

            for (int i = start + 1; i < sorted_tickets.size(); i++) {
                if (sorted_tickets.get(i).getPrice() < sorted_tickets.get(minIndex).getPrice()) {
                    minIndex = i;
                }
            }
            temp = sorted_tickets.get(start);
            sorted_tickets.set(start, sorted_tickets.get(minIndex));
            sorted_tickets.set(minIndex, temp);
        }
        System.out.println("Sorted tickets by price (cheapest first):");
        for (Ticket ticket : sorted_tickets) {
            System.out.println(ticket.toString());
        }
    }
}