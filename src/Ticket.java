import java.util.ArrayList;

public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;
    private String destination;
    public Ticket(int row, int seat, double price, Person person, String destination) {
        this.row = row + 1;
        this.seat = seat + 1;
        this.price = price;
        this.person = person;
        this.destination = destination;
    }
    public Ticket(ArrayList<Ticket> arraylist) {
    }
    // Getter methods
    public int getRow() {
        return row;
    }
    public int getSeat() {
        return seat;
    }
    public double getPrice() {
        return price;
    }
    public Person getPerson() {
        return person;
    }
    public String getDestination() {
        return destination;
    }
    // Method to print ticket information
    public String print() {
        System.out.println("Person name: " + person.getName());
        System.out.println("Person surname: " + person.getSurname());
        System.out.println("Person email: " + person.getEmail());
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: " + price);
        System.out.println("-------------------------------------------------------");
        return destination;
    }
    public String toString() {
        return print();
    }
    protected void setPrice(int temp) {
    }
}
